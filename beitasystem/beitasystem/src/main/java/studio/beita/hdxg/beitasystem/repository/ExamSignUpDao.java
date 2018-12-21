package studio.beita.hdxg.beitasystem.repository;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import studio.beita.hdxg.beitasystem.model.domain.*;
import studio.beita.hdxg.beitasystem.repository.provider.ExamSignUpDaoProvider;

import java.util.Date;
import java.util.List;

/**
 * @author zr
 * @program: beitasystem
 * @Title: ExamSignUpDao
 * @package: studio.beita.hdxg.beitasystem.service
 * @description: 考试报名模块Service层接口
 */
@Mapper
@Repository
public interface ExamSignUpDao {
    // TODO: 2018/11/5  限定考试审核员添加时间

    /**
     * 身份验证（是否有反面身份证照片）
     *
     * @param userId
     * @return
     */
    @Select("SELECT idcard_reverse_photo_url FROM idcard_photo WHERE userinfo_id = #{userId}")
    String userAuthentication(@Param("userId")String userId);

    /**
     * 取出可以报名的考试类别表清单
     *
     * @return
     */
    @Select("SELECT exam_type_id, exam_name, exam_isclosed, exam_issignup, exam_isquery, exam_audited_num, exam_capacity, " +
            "exam_starttime, exam_endtime FROM exam_type WHERE exam_issignup = 1 GROUP BY exam_type_id DESC")
    @Results(
            id = "getExamInfoList",
            value = {
                    @Result(id = true, property = "examId", column = "exam_type_id"),
                    @Result(property = "examName", column = "exam_name"),
                    @Result(property = "isClosed", column = "exam_isclosed"),
                    @Result(property = "isSignUp", column = "exam_issignup"),
                    @Result(property = "isQuery", column = "exam_isquery"),
                    @Result(property = "auditedNum", column = "exam_audited_num"),
                    @Result(property = "capacity", column = "exam_capacity"),
                    @Result(property = "startTime", column = "exam_starttime"),
                    @Result(property = "endTime", column = "exam_endtime"),

            }
    )
    List<ExamInfo> getExamInfoList();

    /**
     * 用户添加用户报名表
     *
     * @param examTypeId
     * @param detailsId
     * @param signUpPic
     * @param signUpTime
     * @param isConfirm
     * @param birthMonth
     * @return
     */
    @InsertProvider(type = ExamSignUpDaoProvider.class, method = "insertExamSignupListByUser")
    Integer insertExamSignupListByUser(@Param("examTypeId")String examTypeId, @Param("detailsId")String detailsId, @Param("signUpPic")String signUpPic,
                                              @Param("signUpTime")Date signUpTime, @Param("isConfirm")boolean isConfirm,@Param("birthMonth")Integer birthMonth);

    /**
     * 验证是否为审核该考试的管理员
     *
     * @param typeId
     * @param userId
     * @return
     */
    @Select("SELECT enter_p_id FROM review_personnel WHERE enter_type = 0 AND userinfo_id = #{userId} AND exam_type_id = #{typeId}")
    String verifyAdministrator(@Param("typeId")String typeId,@Param("userId")String userId);

    /**
     * 根据出生月份，选择对应未审核的考生
     *
     * @param typeId
     * @param month
     * @return
     */
    @Select(
            "SELECT signup_id, exam_type_id, details_id, signup_pic, signup_time, signup_isconfirm, signup_birth_month " +
            " FROM exam_signup_list WHERE signup_birth_month = #{month} AND exam_type_id = #{typeId} AND signup_isconfirm = 0 ORDER BY details_id ASC")
    @Results(
            id = "reviewCandidateInformation",
            value = {
                    @Result(id = true, property = "signUpId", column = "signup_id"),
                    @Result(property = "examTypeId", column = "exam_type_id"),
                    @Result(property = "name", column = "details_id",one = @One(select = "studio.beita.hdxg.beitasystem.repository.ExamSignUpDao.getUserNameByUserId")),
                    @Result(property = "signUpPic", column = "signup_pic"),
                    @Result(property = "signUpTime", column = "signup_time"),
                    @Result(property = "frontPhotoUrl", column = "details_id",one = @One(select = "studio.beita.hdxg.beitasystem.repository.ExamSignUpDao.getFrontPhotoUrlByUserId")),
                    @Result(property = "reversePhotoUrl", column = "details_id",one = @One(select = "studio.beita.hdxg.beitasystem.repository.ExamSignUpDao.getReversePhotoUrlByUserId")),
                    @Result(property = "isConfirm", column = "signup_isconfirm")
            }
    )
    List<ReviewCandidate> reviewCandidateInformation(@Param("typeId")String typeId,@Param("month") int month);

    /**
     * 通过userId获取用户名
     *
     * @param userId
     * @return
     */
    @Select("SELECT details_realname FROM user_details WHERE details_id = #{userId} ")
    String getUserNameByUserId(@Param("userId") String userId);

    /**
     * 通过userId获取身份证正面
     *
     * @param userId
     * @return
     */
    @Select("SELECT idcard_front_photo_url FROM idcard_photo WHERE userinfo_id = #{userId} ")
    String getFrontPhotoUrlByUserId(@Param("userId") String userId);

    /**
     * 通过userId获取身份证反面
     *
     * @param userId
     * @return
     */
    @Select("SELECT idcard_reverse_photo_url FROM idcard_photo WHERE userinfo_id = #{userId} ")
    String getReversePhotoUrlByUserId(@Param("userId") String userId);

    // TODO: 2018/11/6 @Results 如果取出为空的话
    /**
     * 查询对应考试的管理员列表（对应列表的）
     *
     * @param typeId
     * @return
     */
    @Select("SELECT enter_p_id, exam_type_id, userinfo_id, enter_is_check, start_review, end_review, enter_type FROM review_personnel WHERE exam_type_id = #{typeId} AND enter_type = 0 ORDER BY userinfo_id ASC")
    List<ReviewPersonnel> getExamAdminNumberByExamTypeId(@Param("typeId") String typeId);

    /**
     * 未通过者，发邮件告知，删除其报名表
     *
     * @param userId
     * @return
     */
    @Delete("DELETE FROM exam_signup_list WHERE userinfo_id = #{userId} AND exam_type_id = #{typeId}")
    Integer deleteCandidateByUserId( @Param("userId")String userId,@Param("typeId") String typeId);

    /**
     * 审核通过者，发邮件告知，更改审核状态为1
     *
     * @param userId
     * @return
     */
    @UpdateProvider(type = ExamSignUpDaoProvider.class, method = "changeExamSignupList")
    Integer changeExamSignupList(@Param("userId") String userId,@Param("typeId") String typeId);

    /**
     * 审核通过,更改考试类别表的审核人数
     *
     * @param typeId
     * @return
     */
    @UpdateProvider(type = ExamSignUpDaoProvider.class, method = "changeCandidateNum")
    Integer changeCandidateNum(@Param("typeId") String typeId);

    /**
     * 计算该考试的过审人数
     *
     * @param typeId
     * @return
     */
    @Select("SELECT count(signup_id) FROM exam_signup_list WHERE exam_type_id = #{typeId}")
    Integer getPassNumByAdmin(@Param("typeId") String typeId);

    @Select("SELECT ticket_info_name FROM admission_ticket_info WHERE userinfo_id = #{userId} AND ticket_info_place=#{place}")
    String assertTicket(@Param("userId") String userId,@Param("place") String place);

    /**
     * 获取考试报名列表
     * @param examId
     * @return
     */
    @Select("SELECT esl.signup_id, esl.exam_type_id, esl.details_id, ud.details_realname, ud.details_idcard, ud.details_savepath FROM exam_signup_list esl LEFT JOIN user_details ud ON esl.details_id = ud.details_id " +
            "WHERE esl.exam_type_id=#{examId} AND esl.signup_isconfirm = 1 limit #{startNum},#{num}")
    @Results(
            id = "generateAdmissionTicket",
            value = {
                    @Result(id = true, property = "signUpId", column = "signup_id"),
                    @Result(property = "examTypeId", column = "exam_type_id"),
                    @Result(property = "detailsId", column = "details_id"),
                    @Result(property = "realName", column = "details_realname"),
                    @Result(property = "idCard", column = "details_idcard"),
                    @Result(property = "photoPath", column = "details_savepath")
            }
    )
    List<ExamSignupList> getExamSignupListByExamId(@Param("examId") String examId,@Param("startNum") int startNum,@Param("num") int num);

    /**
     * 获取考试场次
     * @param examId
     * @return
     */
    @Select("SELECT session_id, exam_type_id, session_place, session_time, session_capacity FROM exam_session WHERE exam_type_id = #{examId}")
    @Results(
            id = "generateAdmissionTicketSession",
            value = {
                    @Result(id = true, property = "sessionId", column = "session_id"),
                    @Result(property = "examTypeId", column = "exam_type_id"),
                    @Result(property = "detailsId", column = "details_id"),
                    @Result(property = "realName", column = "details_realname"),
                    @Result(property = "idCard", column = "details_idcard"),
                    @Result(property = "photoPath", column = "details_savepath")
            }
    )
    List<ExamSession> getExamSessionByTypeId(@Param("examId")String examId);

    /**
     * 生成的准考证信息插入数据库
     * @param userId
     * @param realName
     * @param identifier
     * @param time
     * @param duration
     * @param place
     * @param seatnum
     * @param school
     * @return
     */
    @InsertProvider(type = ExamSignUpDaoProvider.class, method = "insertAdmissionTicketInfo")
    Integer insertAdmissionTicketInfo(@Param("userId") String userId,@Param("realName") String realName,@Param("identifier") String identifier,@Param("time") Date time,@Param("duration") int duration,@Param("place") String place,@Param("seatnum") int seatnum,@Param("school") String school);
}
