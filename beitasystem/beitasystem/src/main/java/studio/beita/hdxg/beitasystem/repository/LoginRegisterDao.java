package studio.beita.hdxg.beitasystem.repository;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import studio.beita.hdxg.beitasystem.repository.provider.LoginRegisterDaoProvider;

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
     * 管理员添加管理员账号
     *
     * @param userId
     * @param account
     * @param password
     * @param email
     * @return
     */
    @InsertProvider(type = LoginRegisterDaoProvider.class, method = "insertUserByAdmin")
    //@Options(keyProperty = "id", keyColumn = "xpsp_id", useGeneratedKeys = true)
    Integer insertUserByAdmin(@Param("userId") Integer userId, @Param("account") String account, @Param("password") String password, @Param("email") String email);

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
    Integer register(Integer userId, String account, String password, String email);

    /**
     * 系统通过账号和旧密码验证账号
     *
     * @param userId
     * @param account
     * @param oldPwd
     * @return
     */
    Integer verifyAccount(Integer userId, String account, String oldPwd);

    /**
     * 管理员/用户修改账号密码
     *
     * @param userId
     * @param newPwd
     * @return
     */
    Integer changePassword(Integer userId, String newPwd);


}
