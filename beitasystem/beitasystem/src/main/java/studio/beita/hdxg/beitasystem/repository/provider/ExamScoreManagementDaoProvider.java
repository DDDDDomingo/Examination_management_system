package studio.beita.hdxg.beitasystem.repository.provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @author zr
 * @program: beitasystem
 * @Title: ExamScoreManagementDaoProvider
 * @package: studio.beita.hdxg.beitasystem.repository.provider
 * @description: 考试成绩管理模块Dao层动态SQL语句
 **/

public class ExamScoreManagementDaoProvider {
    /**
     * 验证考生准考证和姓名是否匹配
     *
     * @param esmMap
     * @return
     */
    public String checkUserInfo(Map<String, Object> esmMap) {
        String identifier = (String) esmMap.get("identifier");
        String name = (String) esmMap.get("name");
        return new SQL() {
            {
                SELECT("ticket_info_identifier");
                FROM("admission_ticket_info");
                WHERE("ticket_info_identifier='" + identifier + "'");
                if (name != null && !name.equals("")) {
                    AND();
                    WHERE("ticket_info_name='" + name + "'");
                }
            }
        }.toString();
    }
}
