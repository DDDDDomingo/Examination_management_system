package studio.beita.hdxg.beitasystem.repository;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import studio.beita.hdxg.beitasystem.model.domain.ExamScore;
import studio.beita.hdxg.beitasystem.model.domain.ExamSession;
import studio.beita.hdxg.beitasystem.model.domain.ReturnScore;
import studio.beita.hdxg.beitasystem.model.domain.ReviewPersonnel;
import studio.beita.hdxg.beitasystem.repository.provider.ExamScoreManagementDaoProvider;

import java.util.List;

/**
 * @author zr
 * @program: beitasystem
 * @Title: ExamScoreManagementDao
 * @package: studio.beita.hdxg.beitasystem.service
 * @description: 考试成绩管理模块Dao层接口
 */
@Mapper
@Repository
public interface ExamScoreManagementDao {

    /**
     * 考生通过准考证查询成绩
     *
     * @param identifier
     * @return
     */
    @Select("SELECT score_id, exam_type_id, ticket_info_identifier, score_num FROM exam_score WHERE ticket_info_identifier = #{identifier}")
    @Results(
            id = "getExam",
            value = {
                    @Result(property = "scoreId", column = "score_id"),
                    @Result(property = "examName",column = "exam_type_id",one = @One(select = "studio.beita.hdxg.beitasystem.repository.ExamScoreManagementDao.getExamNameByIdentifier")),
                    @Result(property = "realName",column = "ticket_info_identifier",one = @One(select = "studio.beita.hdxg.beitasystem.repository.ExamScoreManagementDao.getRealNameByIdentifier")),
                    @Result(property = "identifier", column = "ticket_info_identifier"),
                    @Result(property = "scoreNum", column = "score_num"),
            }
    )
    ExamScore getExamScoreByIdentifier(String identifier);

    /**
     * 通过准考证获取考生姓名
     * @param identifier
     * @return
     */
    @Select("SELECT et.exam_name FROM exam_score es, exam_type et WHERE es.ticket_info_identifier= #{identifier} AND es.exam_type_id = et.exam_type_id")
    String getExamNameByIdentifier(String identifier);

    /**
     * 验证考生准考证和姓名是否匹配
     *
     * @param identifier
     * @param name
     * @return
     */
    @SelectProvider(type = ExamScoreManagementDaoProvider.class, method = "checkUserInfo")
    String checkUserInfo(@Param("identifier") String identifier, @Param("name") String name);

    /**
     * 通过前台返回的准考证和成绩更改考试成绩
     *
     * @param returnScore
     * @return
     */
    @Update("<script>UPDATE exam_score SET score_num = #{ScoreNum} WHERE ticket_info_identifier in" +
            "<foreach item=\"id\" index=\"index\" collection=\"returnScore\" open=\"(\" separator=\",\" close=\")\">" +
            "#{identifier}" +
            "</foreach>" +
            "</script>")
    Integer changeExamScoreByReturnScore(List<ReturnScore> returnScore);

    /**
     * 录入管理员查看某场次录入成绩表
     *
     * @param sessionId
     * @return
     */
    @Select("SELECT score_id, exam_type_id, ticket_info_identifier, score_num FROM exam_score WHERE session_id= #{sessionId} ORDER BY score_id ASC")
    @Results(
            id = "examScore",
            value = {
                    @Result(property = "scoreId", column = "score_id"),
                    @Result(property = "examId", column = "exam_type_id"),
                    @Result(property = "realName",column = "ticket_info_identifier",one = @One(select = "studio.beita.hdxg.beitasystem.repository.ExamScoreManagementDao.getRealNameByIdentifier")),
                    @Result(property = "scoreNum", column = "score_num"),
            }
    )
    List<ExamScore> getExamScoreListBySession(Integer sessionId);

    /**
     * 通过准考证获取考生姓名
     * @param identifier
     * @return
     */
    @Select("SELECT ticket_info_name FROM admission_ticket_info WHERE ticket_info_identifier= #{identifier}")
    String getRealNameByIdentifier(String identifier);
    
    /**
     * 验证录入管理员是否能录入
     *
     * @param userId
     * @return
     */
    @Select("SELECT enter_p_id, exam_type_id,userinfo_id, enter_is_check, start_review, end_review, enter_type FROM review_personnel WHERE Enter_type = 0 AND userinfo_id= #{userId}")
    ReviewPersonnel verifyTimeByUserId(Integer userId);

    /**
     * 更改录入人员审核状态
     * @param userId
     * @return
     */
    @Update("UPDATE review_personnel SET enter_is_check = 1 WHERE userinfo_id= #{userId}")
    Integer changeIsCheck(Integer userId);

    /**
     * 验证准考证对应的考试成绩查询是否开放
     *
     * @param identifier
     * @return
     */
    @Select("SELECT et.exam_isquery FROM exam_score es, exam_type et WHERE es.exam_type_id = et.exam_type_id AND es.ticket_info_identifier= #{identifier}")
    boolean getIsQueryByIdentifier(String identifier);

    /**
     * 管理员获取考试场次列表
     *
     * @return
     */
    @Select("SELECT session_id, exam_type_id, session_place, session_time, session_capacity FROM exam_session")
    List<ExamSession> getExamSessionList();

}
