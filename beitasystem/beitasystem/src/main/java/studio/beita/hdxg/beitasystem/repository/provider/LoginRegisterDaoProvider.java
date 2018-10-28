package studio.beita.hdxg.beitasystem.repository.provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @author ydq
 * @program: beitasystem
 * @Title: LoginRegisterDaoProvider
 * @package: studio.beita.hdxg.beitasystem.repository.provider
 * @description: 登陆注册模块Dao层动态SQL语句
 **/

public class LoginRegisterDaoProvider {

    /**
     * 管理员添加管理员账号
     *
     * @param uiMap
     * @return
     */
    public String insertUserByAdmin(Map<String, Object> uiMap) {
        String userId = (String) uiMap.get("userId");
        String account = (String) uiMap.get("account");
        String password = (String) uiMap.get("password");
        String email = (String) uiMap.get("email");
        return new SQL() {
            {
                INSERT_INTO("user_info");
                VALUES("userinfo_id", "#{userId}");
                VALUES("userinfo_account", "#{account}");
                VALUES("userinfo_password", "#{password}");
                if (email != null && !email.equals("")) {
                    VALUES("userinfo_email", "#{email}");
                }
            }
        }.toString();
    }

    /**
     * 用户登陆验证
     *
     * @param uiMap
     * @return
     */
    public String assertLogin(Map<String, Object> uiMap) {
        String account = (String) uiMap.get("account");
        String email = (String) uiMap.get("email");
        String password = (String) uiMap.get("password");
        return new SQL() {
            {
                SELECT("userinfo_account");
                FROM("user_info");
                WHERE("userinfo_password='" + password + "'");
                if (account != null && !account.equals("")) {
                    AND();
                    WHERE("userinfo_account='" + account + "'");
                }
                if (email != null && !email.equals("")) {
                    AND();
                    WHERE("userinfo_email='" + email + "'");
                }
            }
        }.toString();
    }

    /**
     * 系统添加用户个人信息
     * @param piMap
     * @return
     */
    public String insertUserDetailsByUser(Map<String, Object> piMap) {
        String detailsId = (String) piMap.get("detailsId");
        return new SQL() {
            {
                INSERT_INTO("user_details");
                VALUES("details_id", "#{detailsId}");
            }
        }.toString();
    }

}
