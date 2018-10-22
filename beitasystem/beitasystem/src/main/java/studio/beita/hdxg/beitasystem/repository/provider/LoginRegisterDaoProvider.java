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
     * @param lrMap
     * @return
     */
    public String insertUserByAdmin(Map<String, Object> lrMap) {
        Integer userId = (Integer) lrMap.get("userId");
        String account = (String) lrMap.get("account");
        String password = (String) lrMap.get("password");
        String email = (String) lrMap.get("email");
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

}
