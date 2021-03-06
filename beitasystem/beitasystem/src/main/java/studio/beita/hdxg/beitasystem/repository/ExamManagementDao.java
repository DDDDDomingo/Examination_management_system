package studio.beita.hdxg.beitasystem.repository;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import studio.beita.hdxg.beitasystem.model.domain.ExamInfo;
import studio.beita.hdxg.beitasystem.model.domain.ReviewPersonnel;
import studio.beita.hdxg.beitasystem.repository.provider.ExamManagementDaoProvider;

import javax.websocket.Session;
import java.util.List;

/**
 * @author ydq
 * @program: beitasystem
 * @Title: ExamManagementDao
 * @package: studio.beita.hdxg.beitasystem.repository
 * @description: 考试管理模块Dao层接口
 **/
@Mapper
@Repository
public interface ExamManagementDao {

    /**
     * 管理员新增考试
     *
     * @param examId
     * @param examName
     * @param examCapacity
     * @param startTime
     * @param endTime
     * @return
     */
    @Insert("INSERT INTO exam_type (exam_type_id, exam_name, exam_capacity,exam_starttime,exam_endtime) VALUES (#{examId},#{examName},#{examCapacity},#{startTime},#{endTime})")
    Integer addExam(@Param("examId") String examId, @Param("examName") String examName, @Param("examCapacity") Integer examCapacity, @Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 管理员修改：结束考试
     *
     * @param examId
     * @return
     */
    @Update("UPDATE exam_type SET exam_isclosed=1,exam_issignup=0,exam_isquery=0 WHERE exam_type_id=#{examId}")
    Integer changeExamClosed(@Param("examId") String examId);

    /**
     * 修改考试是否可以报名时，保证考试未结束
     *
     * @param examId
     * @return
     */
    @Select("SELECT exam_type_id FROM exam_type WHERE exam_type_id=#{examId} AND exam_isclosed=0")
    @ResultType(String.class)
    String assertExamIsClosed(String examId);

    /**
     * 管理员修改：考试是否可以报名
     *
     * @param examId
     * @param isSignUp
     * @return
     */
    @Update("UPDATE exam_type SET exam_issignUp=#{isSignUp} WHERE exam_type_id=#{examId} AND exam_isclosed = 0")
    Integer changeExamSignUp(@Param("examId") String examId, @Param("isSignUp") boolean isSignUp);

    /**
     * 修改考试成绩为可查询时，保证考试未结束，考试报名已结束
     *
     * @param examId
     * @return
     */
    @Select("SELECT exam_type_id FROM exam_type WHERE exam_type_id=#{examId} AND exam_isclosed=0 AND exam_issignup=0")
    String assertExamIsClosedSignUp(String examId);

    /**
     * 管理员修改：考试成绩是否可以查询
     *
     * @param examId
     * @param isQuery
     * @return
     */
    @Update("UPDATE exam_type SET exam_isquery=#{isQuery} WHERE exam_type_id=#{examId} AND exam_isclosed = 0")
    Integer changeExamQuery(@Param("examId") String examId, @Param("isQuery") boolean isQuery);

    // TODO: 2018/10/28 条件筛选

    /**
     * 管理员获取考试列表
     *
     * @return
     */
    @SelectProvider(type = ExamManagementDaoProvider.class, method = "getExamListByAdmin")
    @Results(
            id = "examDetails",
            value = {
                    @Result(id = true, property = "examId", column = "exam_type_id"),
                    @Result(property = "examName", column = "exam_name"),
                    @Result(property = "isClosed", column = "exam_isclosed"),
                    @Result(property = "isSignUp", column = "exam_issignup"),
                    @Result(property = "isQuery", column = "exam_isquery"),
                    @Result(property = "auditedNum", column = "exam_audited_num"),
                    @Result(property = "capacity", column = "exam_capacity"),
                    @Result(property = "startTime", column = "exam_starttime"),
                    @Result(property = "endTime", column = "exam_endtime")
            }
    )
    List<ExamInfo> getExamListByAdmin();

    /**
     * 考试管理员/考生获取考试信息
     *
     * @param examId
     * @return
     */
    @SelectProvider(type = ExamManagementDaoProvider.class, method = "getExamDetails")
    @ResultMap("examDetails")
    ExamInfo getExamDetails(String examId);

    /**
     * 考生获取可报名考试列表
     *
     * @return
     */
    @SelectProvider(type = ExamManagementDaoProvider.class, method = "getSignUpExamList")
    @Results(
            id = "signUpExamDetails",
            value = {
                    @Result(id = true, property = "examId", column = "exam_type_id"),
                    @Result(property = "examName", column = "exam_name"),
                    @Result(property = "auditedNum", column = "exam_audited_num"),
                    @Result(property = "capacity", column = "exam_capacity"),
                    @Result(property = "startTime", column = "exam_starttime"),
                    @Result(property = "endTime", column = "exam_endtime")
            }
    )
    List<ExamInfo> getSignUpExamList();

    // TODO: 2018/10/28 考试场次部分 管理员分配考场

    /**
     * 管理员添加考试场次
     *
     * @param sessionPlace
     * @param sessionCapacity
     * @param sessionTime
     * @return
     */
    @Insert("INSERT INTO exam_session (session_place, session_time, session_capacity) VALUES (#{sessionPlace},#{sessionTime},#{sessionCapacity})")
    Integer addExamSession(@Param("sessionPlace") String sessionPlace, @Param("sessionCapacity") Integer sessionCapacity, @Param("sessionTime") String sessionTime);

    /**
     * 管理员根据考试场次ID删除考场
     *
     * @param examId
     * @return
     */
    @Delete("DELETE FROM exam_session WHERE session_id = #{examId}")
    Integer deleteExamSession(String examId);

    /**
     * 管理员获取考场列表
     *
     * @param examId
     * @return
     */
    @SelectProvider(type = ExamManagementDaoProvider.class, method = "getExamDetails")
    @Results(
            id = "examAdminDetails",
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
                    @Result(property = "sessionList", column = "examId", many = @Many(select = "studio.beita.hdxg.beitasystem.repository.ExamManagementDao.getExamSessionByExamTypeId"))
            }
    )
    ExamInfo adminGetExamDetails(String examId);

    /**
     * 通过开始ID获取考场列表
     *
     * @param examId
     * @return
     */
    @Select("SELECT session_id, session_place, session_time, session_capacity FROM exam_session WHERE exam_type_id = #{examId}")
    @Results(
            id = "sessionDetails",
            value = {
                    @Result(id = true, property = "sessionId", column = "session_id"),
                    @Result(property = "sessionPlace", column = "session_place"),
                    @Result(property = "sessionCapacity", column = "session_time"),
                    @Result(property = "sessionTime", column = "session_capacity")
            }
    )
    List<Session> getExamSessionByExamTypeId(String examId);

    /**
     * 获取该考试的管理员列表
     *
     * @param typeId
     * @param enterType
     * @return
     */
    @Select("SELECT enter_p_id, exam_type_id, userinfo_id FROM review_personnel WHERE exam_type_id = #{typeId} AND enter_type = #{enterType} ORDER BY enter_p_id ASC")
    @Results(
            id = "reviewList",
            value = {
                    @Result(id = true, property = "enterPId", column = "enter_p_id"),
                    @Result(property = "typeId", column = "exam_type_id"),
                    @Result(property = "examName", column = "exam_type_id", one = @One(select = "studio.beita.hdxg.beitasystem.repository.ExamManagementDao.getExamName")),
                    @Result(property = "userId", column = "userinfo_id"),
                    @Result(property = "account", column = "userinfo_id", one = @One(select = "studio.beita.hdxg.beitasystem.repository.ExamManagementDao.getAccount"))
            }
    )
    List<ReviewPersonnel> getExamAdminNumberByExamTypeId(String typeId, Integer enterType);

    /**
     * 通过ID获取考试名称
     *
     * @param typeId
     * @return
     */
    @Select("SELECT exam_name FROM exam_type WHERE exam_type_id=#{typeId}")
    String getExamName(String typeId);

    /**
     * 通过用户ID获取用户账号
     *
     * @param userId
     * @return
     */
    @Select("SELECT userinfo_account FROM user_info WHERE userinfo_id=#{userId}")
    String getAccount(String userId);

    /**
     * 给考试添加审核员、录入员
     *
     * @param typeId
     * @param userId
     * @param enterType
     * @return
     */
    @Insert("INSERT INTO review_personnel(exam_type_id, userinfo_id, start_review, enter_type) VALUES (#{typeId},#{userId},now(),#{enterType})")
    Integer addReviewPerson(@Param("typeId") String typeId, @Param("userId") String userId, @Param("enterType") Integer enterType);

    /**
     * 删除考试审核员
     *
     * @param enterPId
     * @return
     */
    @Delete("DELETE FROM review_personnel WHERE enter_p_id = #{enterPId}")
    Integer deleteReviewPerson(Integer enterPId);
}
