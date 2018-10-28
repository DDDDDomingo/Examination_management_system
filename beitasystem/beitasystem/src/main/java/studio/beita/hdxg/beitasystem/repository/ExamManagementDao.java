package studio.beita.hdxg.beitasystem.repository;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import studio.beita.hdxg.beitasystem.model.domain.ExamInfo;
import studio.beita.hdxg.beitasystem.repository.provider.ExamManagementDaoProvider;
import studio.beita.hdxg.beitasystem.repository.provider.LoginRegisterDaoProvider;

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
}
