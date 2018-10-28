package studio.beita.hdxg.beitasystem.repository.provider;

import org.apache.ibatis.jdbc.SQL;

/**
 * @author ydq
 * @program: beitasystem
 * @Title: ExamManagementDaoProvider
 * @package: studio.beita.hdxg.beitasystem.repository.provider
 * @description: 考试管理模块Dao层动态SQL
 **/

public class ExamManagementDaoProvider {

    /**
     * 管理员获取考试列表
     *
     * @return
     */
    public String getExamListByAdmin() {
        return new SQL() {
            {
                SELECT("exam_type_id", "exam_name", "exam_isclosed", "exam_issignup", "exam_isquery", "exam_audited_num", "exam_capacity", "exam_starttime", "exam_endtime");
                FROM("exam_type");
                GROUP_BY("exam_starttime");
            }
        }.toString();
    }

    /**
     * 管理员获取考试列表
     *
     * @return
     */
    public String getExamDetails(String examId) {
        return new SQL() {
            {
                SELECT("exam_type_id", "exam_name", "exam_isclosed", "exam_issignup", "exam_isquery", "exam_audited_num", "exam_capacity", "exam_starttime", "exam_endtime");
                FROM("exam_type");
                WHERE("exam_type_id = '" + examId + "'");
            }
        }.toString();
    }

    /**
     * 考生获取可报名列表
     *
     * @return
     */
    public String getSignUpExamList() {
        return new SQL() {
            {
                SELECT("exam_type_id", "exam_name", "exam_audited_num", "exam_capacity", "exam_starttime", "exam_endtime");
                FROM("exam_type");
                WHERE("exam_isclosed=" + "0");
                AND();
                WHERE("exam_issignup=" + "1");
                AND();
                WHERE("exam_isquery=" + "0");
                GROUP_BY("exam_starttime");
            }
        }.toString();
    }

}
