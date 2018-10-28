package studio.beita.hdxg.beitasystem.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author ydq
 * @program: beitasystem
 * @Title: ExamManagementDao
 * @package: studio.beita.hdxg.beitasystem.repository
 * @description: 考试管理模块Dao层接口
 **/

public interface ExamManagementDao {
    /**
     * 管理员新增考试
     *
     * @param examId
     * @param examName
     * @param examCapacity
     * @return
     */
    @Insert("INSERT INTO exam_type (exam_type_id, exam_name, exam_capacity) VALUES (#{examId},#{examName},#{examCapacity})")
    Integer addExam(@Param("examId") String examId, @Param("examName") String examName, @Param("examCapacity") Integer examCapacity);

    /**
     * 管理员修改：考试是否已经结束
     *
     * @param examId
     * @param isClosed
     * @return
     */
    @Update("UPDATE exam_type SET exam_isclosed=#{isClosed} WHERE exam_type_id=#{examId}")
    Integer changeExamClosed(@Param("examId") String examId, @Param("isClosed") boolean isClosed);

    /**
     * 管理员修改：考试是否可以报名
     *
     * @param examId
     * @param isSignUp
     * @return
     */
    @Update("UPDATE exam_type SET exam_issignUp=#{isSignUp} WHERE exam_type_id=#{examId}")
    Integer changeExamSignUp(@Param("examId") String examId, @Param("isSignUp") boolean isSignUp);


    /**
     * 管理员修改：考试成绩是否可以查询
     *
     * @param examId
     * @param isQuery
     * @return
     */
    @Update("UPDATE exam_type SET exam_isquery=#{isQuery} WHERE exam_type_id=#{examId}")
    Integer changeExamQuery(@Param("examId") String examId, @Param("isQuery") boolean isQuery);
}
