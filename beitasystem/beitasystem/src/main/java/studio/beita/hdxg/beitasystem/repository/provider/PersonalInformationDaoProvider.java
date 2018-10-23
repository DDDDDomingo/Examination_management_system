package studio.beita.hdxg.beitasystem.repository.provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @author zr
 * @program: beitasystem
 * @Title: PersonalInformationDaoProvider
 * @package: studio.beita.hdxg.beitasystem.repository.provider
 * @description: 个人信息模块Dao层动态SQL语句
 **/
public class PersonalInformationDaoProvider {
    /**
     * 系统添加用户个人信息
     * @param piMap
     * @return
     */
    public String insertUserDetailsByUser(Map<String, Object> piMap) {
        Integer detailsId = (Integer) piMap.get("detailsId");
        String avatar = (String) piMap.get("avatar");
        String phone = (String) piMap.get("phone");
        String address = (String) piMap.get("address");
        String realName = (String) piMap.get("realName");
        String idCard = (String) piMap.get("idCard");
        return new SQL() {
            {
                INSERT_INTO("user_details");
                VALUES("details_id", "#{detailsId}");
                VALUES("details_avatar", "#{avatar}");
                if (phone != null && !phone.equals("")) {
                    VALUES("details_phone", "#{phone}");
                }
                if (address != null && !address.equals("")) {
                VALUES("details_address", "#{address}");
                }
                if (realName != null && !realName.equals("")) {
                    VALUES("details_realname", "#{realName}");
                }
                if (idCard != null && !idCard.equals("")) {
                    VALUES("details_idcard", "#{idCard}");
                }
            }
        }.toString();
    }

    public String changeUserDetails(Map<String, Object> piMap) {
        Integer detailsId = (Integer) piMap.get("detailsId");
        String avatar = (String) piMap.get("avatar");
        String phone = (String) piMap.get("phone");
        String address = (String) piMap.get("address");
        String realName = (String) piMap.get("realName");
        String idCard = (String) piMap.get("idCard");
        return new SQL() {
            {
                UPDATE("user_details");
                SET("details_avatar='" + avatar + "'");
                SET("details_phone ='" + phone + "'");
                SET("details_address ='" + address + "'");
                SET("details_realname ='" + realName + "'");
                SET("details_idcard ='" + idCard + "'");
                WHERE("details_id =" + detailsId);
            }
        }.toString();
    }
}
