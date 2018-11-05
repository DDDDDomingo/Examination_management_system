package studio.beita.hdxg.beitasystem.repository;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import studio.beita.hdxg.beitasystem.model.domain.ExamInfo;
import studio.beita.hdxg.beitasystem.model.domain.ExamSignupList;
import studio.beita.hdxg.beitasystem.model.domain.ReviewPersonnel;
import studio.beita.hdxg.beitasystem.model.domain.UserDetails;
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
    // TODO: 2018/11/5  用户报名时，身份验证（真实姓名，身份证是否完善），取出可以报名的考试类别表清单，选取考试类别，考生上传转账截图，点击报名。
    // TODO: 2018/11/5  审核员审核，查询审核对应出生月份的考生（先查询考试报名审核员人数），将其报名表改为通过（一起取），对审核结果发送通知（审核完就发）是否通过都发，计算审核通过人数，超出则添加考场,结束考试直接
    // TODO: 2018/11/5  select添加升降序
    // TODO: 2018/11/5  限定考试审核员添加时间 
    //管理员增删改查考试新闻（富文本，html格式，新闻资源），新闻类别未确定，游客和用户获取考试新闻列表（随时间倒置排列），游客点击增加阅读量，可以下载新闻资源

    /**
     * 身份验证（是否有反面身份证照片）需要修改
     *
     * @param userId
     * @return
     */
    @Select("SELECT idcard_reverse_photo_url FROM user_details WHERE userinfo_id = #{userId}")
    String userAuthentication(Integer userId);

    /**
     * 取出可以报名的考试类别表清单
     *
     * @return
     */
    @Select("SELECT exam_type_id, exam_name, exam_isclosed, exam_issignup, exam_isquery, exam_audited_num, exam_capacity, " +
            "exam_starttime, exam_endtime FROM exam_type WHERE exam_issignup = 1 GROUP BY exam_type_id DESC")
    List<ExamInfo> getExamInfoList();

    /**
     * 用户添加用户报名表
     *
     * @param examTypeId
     * @param detailsId
     * @param signUpPic
     * @param signUpTime
     * @param isConfirm
     * @return
     */
    @InsertProvider(type = ExamSignUpDaoProvider.class, method = "insertExamSignupListByUser")
    ExamSignupList insertExamSignupListByUser(@Param("examTypeId")String examTypeId, @Param("detailsId")String detailsId, @Param("signUpPic")String signUpPic,
                                              @Param("signUpTime")Date signUpTime, @Param("isConfirm")boolean isConfirm,@Param("birthMonth")Integer birthMonth);

    /**
     * 验证是否为审核该考试的管理员
     *
     * @param typeId
     * @param userId
     * @return
     */
    @Select("SELECT enter_p_id FROM review_personnel WHERE userinfo_id = #{userId} AND exam_type_id = #{typeId}")
    String verifyAdministrator(String typeId,Integer userId);

    /**
     * 根据出生月份，选择对应未审核的考生
     *
     * @param typeId
     * @param month
     * @return
     */
    @Select("<script>" +
            "SELECT signup_id, exam_type_id, details_id, signup_pic, signup_time, signup_isconfirm, signup_birth_month " +
            " FROM exam_signup_list WHERE signup_birth_month =" +
            "<foreach item=\"id\" index=\"index\" collection=\"list\" open=\"(\" separator=\",\" close=\")\">" +
            "#{item1.month} " +
            "</foreach>" +
            "AND exam_type_id = #{item1.typeId} AND signup_isconfirm = 0" +
            "</script>")
    List<ExamSignupList> reviewCandidateInformation(String typeId,int[] month);

    /**
     * 查询对应考试的管理员列表（对应列表的）
     *
     * @param typeId
     * @return
     */
    @Select("SELECT enter_p_id, exam_type_id, userinfo_id, enter_is_check, start_review, end_review, enter_type FROM review_personnel WHERE exam_type_id = #{typeId} AND enter_type = 0")
    List<ReviewPersonnel> getExamAdminNumberByExamTypeId(String typeId);
}
