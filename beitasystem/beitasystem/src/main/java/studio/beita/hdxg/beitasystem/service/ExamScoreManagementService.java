package studio.beita.hdxg.beitasystem.service;

import studio.beita.hdxg.beitasystem.model.domain.ExamScore;
import studio.beita.hdxg.beitasystem.model.domain.ReturnScore;
import studio.beita.hdxg.beitasystem.model.domain.ReviewPersonnel;

import java.util.List;
import java.util.Optional;

/**
 * @author zr
 * @program: beitasystem
 * @Title: ExamScoreManagementService
 * @package: studio.beita.hdxg.beitasystem.service
 * @description: 考试成绩管理模块Service层接口
 **/
public interface ExamScoreManagementService {
    /**
     * 考生通过准考证ID查询成绩
     *
     * @param identifier
     * @return
     */
    Optional<ExamScore> getExamScoreByIdentifier(String identifier);

    /**
     * 验证考生准考证和姓名是否匹配
     *
     * @param identifier
     * @param name
     * @return
     */
    Optional<String> checkUserInfo(String identifier, String name);

    /**
     * 通过前台返回的准考证和成绩更改考试成绩
     *
     * @param returnScore
     * @return
     */
    boolean changeExamScoreByReturnScore(List<ReturnScore> returnScore);

    /**
     * 录入管理员查看录入成绩表
     * @return
     */
    Optional<List<ExamScore>> getExamScoreList();

    /**
     * 验证录入管理员是否能录入
     *
     * @param userId
     * @return
     */
    Optional<ReviewPersonnel> verifyTimeByUserId(Integer userId);

    /**
     * 更改录入人员审核状态
     * @param userId
     * @return
     */
    boolean changeIsCheck(Integer userId);
}
