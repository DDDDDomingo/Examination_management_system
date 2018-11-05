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
     * 用户添加用户报名表
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
                VALUES("examTypeId", "#{examTypeId}");
                VALUES("detailsId", "#{detailsId}");
                VALUES("signUpPic", "#{signUpPic}");
                VALUES("signUpTime", "#{signUpTime}");
                VALUES("isConfirm", "#{isConfirm}");
                VALUES("birthMonth", "#{birthMonth}");
            }
        }.toString();
    }
}
