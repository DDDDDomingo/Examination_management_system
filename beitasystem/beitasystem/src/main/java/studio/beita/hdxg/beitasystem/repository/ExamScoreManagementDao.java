package studio.beita.hdxg.beitasystem.repository;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import studio.beita.hdxg.beitasystem.model.domain.ExamScore;
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

    // TODO: 2018/10/30 查询成绩返回4个属性（考试名称，准考证号，学生姓名，成绩）通过准考证查询考试名称
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
                    @Result(property = "examName",column = "score_id",one = @One(select = "studio.beita.hdxg.beitasystem.repository.ExamScoreManagementDao.getRealNameByIdentifier")),
                    @Result(property = "realName",column = "exam_type_id",one = @One(select = "studio.beita.hdxg.beitasystem.repository.ExamScoreManagementDao.getRealNameByIdentifier")),
                    @Result(property = "identifier", column = "ticket_info_identifier"),
                    @Result(property = "scoreNum", column = "score_num"),
            }
    )
    ExamScore getExamScoreByIdentifier(String identifier);

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
     * 录入管理员查看录入成绩表
     *
     * @return
     */
    @Select("SELECT score_id, exam_type_id, ticket_info_identifier, score_num FROM exam_score ORDER BY score_id ASC")
    @Results(
            id = "examScore",
            value = {
                    @Result(property = "scoreId", column = "score_id"),
                    @Result(property = "examId", column = "exam_type_id"),
                    @Result(property = "realName",column = "ticket_info_identifier",one = @One(select = "studio.beita.hdxg.beitasystem.repository.ExamScoreManagementDao.getRealNameByIdentifier")),
                    @Result(property = "scoreNum", column = "score_num"),
            }
    )
    List<ExamScore> getExamScoreList();

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

}
