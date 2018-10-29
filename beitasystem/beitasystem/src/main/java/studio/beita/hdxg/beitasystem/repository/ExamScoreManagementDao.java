package studio.beita.hdxg.beitasystem.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import studio.beita.hdxg.beitasystem.model.domain.ExamScore;

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
// TODO: 2018/10/29 管理员录入考试成绩（更改考试成绩表【excel导入，批量修改】），考生查询成绩

    /**
     * 考生通过准考证ID查询成绩
     *
     * @param identifier
     * @return
     */
    @Select("SELECT score_id, exam_type_id, ticket_info_identifier, score_num FROM exam_score WHERE ticket_info_identifier = #{identifier}")
    ExamScore getExamScoreByIdentifier(String identifier);

    //Select验证考生准考证和姓名是否匹配

    //change考生们的成绩

}
