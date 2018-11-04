package studio.beita.hdxg.beitasystem.repository.provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @author ydq
 * @program: beitasystem
 * @Title: PermissionManagementDaoProvider
 * @package: studio.beita.hdxg.beitasystem.repository.provider
 * @description: 权限管理模块Dao层接口provider
 **/

public class PermissionManagementDaoProvider {

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

}
