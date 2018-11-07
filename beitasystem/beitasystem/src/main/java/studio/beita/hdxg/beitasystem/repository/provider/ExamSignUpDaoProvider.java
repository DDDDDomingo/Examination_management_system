package studio.beita.hdxg.beitasystem.repository.provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @author zr
 * @program: beitasystem
 * @Title: ExamSignUpDaoProvider
 * @package: studio.beita.hdxg.beitasystem.repository.provider
 * @description: 考试报名模块Dao层动态SQL语句
 **/
public class ExamSignUpDaoProvider {
    /**
     * 添加用户报名表
     * @param iesMap
     * @return
     */
    public String insertExamSignupListByUser(Map<String, Object> iesMap) {
        String examTypeId = (String) iesMap.get("examTypeId");
        String detailsId = (String) iesMap.get("detailsId");
        String signUpPic = (String) iesMap.get("signUpPic");
        String signUpTime = (String) iesMap.get("signUpTime");
        String isConfirm = (String) iesMap.get("isConfirm");
        String birthMonth = (String) iesMap.get("birthMonth");

        return new SQL() {
            {
                INSERT_INTO("user_details");
                VALUES("exam_type_id", "#{examTypeId}");
                VALUES("details_id", "#{detailsId}");
                VALUES("signup_pic", "#{signUpPic}");
                VALUES("signup_time", "#{signUpTime}");
                VALUES("signup_isconfirm", "#{isConfirm}");
                VALUES("signup_birth_month", "#{birthMonth}");
            }
        }.toString();
    }

    /**
     * 审核通过者，发邮件告知，更改审核状态为1
     * @param esMap
     * @return
     */
    public String changeExamSignupList(Map<String, Object> esMap) {
        String userId = (String) esMap.get("userId");
        String typeId = (String) esMap.get("typeId");
        return new SQL() {
            {
                UPDATE("exam_signup_list");
                SET("signup_isconfirm=1");
                WHERE("details_id =" + userId+"AND exam_type_id =" + typeId);
            }
        }.toString();
    }

    /**
     * 审核通过,更改考试类别表的审核人数
     *
     * @param cnMap
     * @return
     */
    public String changeCandidateNum(Map<String, Object> cnMap) {
        String typeId = (String) cnMap.get("typeId");
        return new SQL() {
            {
                UPDATE("exam_signup_list");
                SET("exam_audited_num = exam_audited_num + 1");
                WHERE("exam_type_id =" + typeId);
            }
        }.toString();
    }
}
