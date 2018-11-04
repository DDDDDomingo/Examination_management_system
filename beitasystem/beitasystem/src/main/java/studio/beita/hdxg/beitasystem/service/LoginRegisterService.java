package studio.beita.hdxg.beitasystem.service;

import studio.beita.hdxg.beitasystem.model.domain.UserInfo;

import java.util.Optional;

/**
 * @author ydq
 * @program: beitasystem
 * @Title: LoginRegisterService
 * @package: studio.beita.hdxg.beitasystem.service
 * @description: 登录注册模块表Service层接口
 **/

public interface LoginRegisterService {

    /**
     * 用户注册账号
     *
     * @param account
     * @param password
     * @param email
     * @return
     */
    boolean register(String account, String password, String email);

    /**
     * 用户通过账号登陆或者通过邮箱登陆
     *
     * @param account
     * @param email
     * @param password
     * @return
     */
    Optional<String> assertLogin(String account, String email, String password);

    /**
     * 系统通过账号和旧密码验证账号
     *
     * @param userId
     * @param account
     * @param oldPwd
     * @return Optional用于检查
     */
    Optional<UserInfo> assertOldPwd(String userId, String account, String oldPwd);

    /**
     * 系统验证账号名是否已被使用
     *
     * @param account
     * @return
     */
    Optional<String> isAccountUsed(String account);

    /**
     * 用户通过邮箱验证账号
     *
     * @param userId
     * @param email
     * @return
     */
    Integer assertAccountByEmail(String userId, String email);

    /**
     * 修改用户的密码
     *
     * @param userId
     * @param newPwd
     * @return
     */
    boolean changePassword(String userId, String newPwd);

}
