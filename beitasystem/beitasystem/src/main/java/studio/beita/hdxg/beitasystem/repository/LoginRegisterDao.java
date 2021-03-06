package studio.beita.hdxg.beitasystem.repository;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import studio.beita.hdxg.beitasystem.model.domain.UserInfo;
import studio.beita.hdxg.beitasystem.repository.provider.LoginRegisterDaoProvider;
import studio.beita.hdxg.beitasystem.repository.provider.PersonalInformationDaoProvider;

/**
 * @author ydq
 * @program: beitasystem
 * @Title: LoginRegisterDao
 * @package: studio.beita.hdxg.beitasystem.repository
 * @description: 登录注册模块接口
 **/
@Mapper
@Repository
public interface LoginRegisterDao {

    /**
     * 添加用户与用户组关系
     *
     * @param userId
     * @param groupId
     * @return
     */
    @Insert("INSERT INTO rel_ui_ug (userinfo_id, group_id) VALUES (#{userId},#{groupId})")
    Integer insertRelUiUg(@Param("userId")String userId, @Param("groupId") Integer groupId);

    /**
     * 游客注册用户
     *
     * @param userId
     * @param account
     * @param password
     * @param email
     * @return
     */
    @Insert("INSERT INTO user_info (userinfo_id,userinfo_account,userinfo_password,userinfo_email) VALUES (#{userId},#{account},#{password},#{email})")
    Integer register(@Param("userId") String userId, @Param("account") String account, @Param("password") String password, @Param("email") String email);

    /**
     * 系统添加用户个人信息
     *
     * @param userId
     * @return
     */
    @InsertProvider(type = LoginRegisterDaoProvider.class, method = "insertUserDetailsByUser")
    Integer insertUserDetailsByUser(@Param("detailsId") String userId);

    /**
     * 验证用户登陆信息是否正确
     *
     * @param account
     * @param email
     * @param password
     * @return
     */
    @SelectProvider(type = LoginRegisterDaoProvider.class, method = "assertLogin")
    String assertLogin(@Param("account") String account, @Param("email") String email, @Param("password") String password);

    /**
     * 系统通过账号和旧密码验证账号
     *
     * @param userId
     * @param account
     * @param oldPwd
     * @return
     */
    @Select("SELECT userinfo_id FROM user_info WHERE userinfo_id = #{userId} AND userinfo_account = #{account} AND userinfo_password=#{oldPwd}")
    @Results(
            id = "userVerify",
            value = {
                    @Result(id = true, property = "userId", column = "userinfo_id")
            }
    )
    UserInfo assertOldPwd(@Param("userId") String userId,@Param("account") String account,@Param("oldPwd") String oldPwd);

    /**
     * 系统验证账号是否已被使用
     *
     * @param account
     * @return
     */
    @Select("SELECT userinfo_account FROM user_info WHERE userinfo_account = #{account}")
    String isAccountUsed(String account);

    /**
     * 管理员/用户修改账号密码
     *
     * @param userId
     * @param newPwd
     * @return
     */
    //@Update("UPDATE user_info SET userinfo_password = #{newPwd} WHERE userinfo_id=#{userId} AND userinfo_password=#{newPwd}")
    @Update("UPDATE user_info SET userinfo_password = #{newPwd} WHERE userinfo_id=#{userId}")
    Integer changePassword(@Param("userId") String userId, @Param("newPwd") String newPwd);


}
