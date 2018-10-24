package studio.beita.hdxg.beitasystem.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import studio.beita.hdxg.beitasystem.constant.ResourceNameConstant;
import studio.beita.hdxg.beitasystem.constant.ResponseConstant;
import studio.beita.hdxg.beitasystem.exception.LoginRegister.AccountIsUsedException;
import studio.beita.hdxg.beitasystem.exception.LoginRegister.LoginErrorException;
import studio.beita.hdxg.beitasystem.exception.LoginRegister.OldPasswordWrongException;
import studio.beita.hdxg.beitasystem.service.LoginRegisterService;

import javax.annotation.Resource;

/**
 * @author ydq
 * @program: beitasystem
 * @Title: LoginRegisterController
 * @package: studio.beita.hdxg.beitasystem.controller
 * @description: 登陆/注册模块控制器
 **/
@Api(value = "LoginRegisterController", description = "LoginRegisterController")
@RestController
public class LoginRegisterController {

    // TODO: 2018/10/24 页面跳转 

    @Autowired
    private LoginRegisterService loginRegisterService;

    @ApiOperation(value = "最高管理员添加下级审核员账号", notes = "add admin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "用户名", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "email", value = "邮箱", dataType = "String", paramType = "query")
    })
    @PostMapping("/admin/add")
    public ResponseEntity<?> insertUserByAdmin(String account, String password, String email) {
        //验证账号是否已经被占用
        assertAccountIsUsed(account);
        //注册账号
        loginRegisterService.insertUserByAdmin(account, password, email);
        return ResponseEntity.ok(ResponseConstant.ACCOUNT_REGISTER_SUCCESS);

    }

    @ApiOperation(value = "用户注册账号", notes = "user register")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "用户名", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "email", value = "邮箱", dataType = "String", paramType = "query", required = true)
    })
    @PostMapping("/user/register")
    public ResponseEntity<?> register(String account, String password, String email) {
        //验证账号是否已经被占用
        assertAccountIsUsed(account);
        //注册账号
        loginRegisterService.register(account, password, email);
        return ResponseEntity.ok(ResponseConstant.ACCOUNT_REGISTER_SUCCESS);
    }

    @ApiOperation(value = "用户登陆账号", notes = "user login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "用户名", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "email", value = "邮箱", dataType = "String", paramType = "query")
    })
    @GetMapping("/user/login")
    public ResponseEntity<?> assertLogin(String account, String email, String password) {
        //验证登陆信息
        assertLoginAccount(account, email, password);
        //登陆成功
        return ResponseEntity.ok(ResponseConstant.ASSERT_LOGIN_SUCCESS);
    }

    @ApiOperation(value = "用户修改密码", notes = "user change password")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", dataType = "Integer", paramType = "query", required = true),
            @ApiImplicitParam(name = "account", value = "用户名", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "oldPwd", value = "旧密码", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "newPwd", value = "新密码", dataType = "String", paramType = "query", required = true)
    })
    @PutMapping("/user/changePwd")
    public ResponseEntity<?> changeUserPwd(Integer userId, String account, String oldPwd, String newPwd) {
        //验证账号信息是否正确
        assertOldPwd(userId, account, oldPwd);
        //修改密码成功
        loginRegisterService.changePassword(userId, newPwd);
        return ResponseEntity.ok(ResponseConstant.CHANGE_PASSWORD_SUCCESS);
    }

    /********************************** HELPER METHOD **********************************/

    /**
     * 验证账号名是否已经被占用
     *
     * @param account
     */
    private void assertAccountIsUsed(String account) {
        loginRegisterService
                .isAccountUsed(account)
                .orElseThrow(
                        () -> new AccountIsUsedException());
    }

    /**
     * 修改密码验证账号是否正确
     *
     * @param userId
     * @param account
     * @param oldPwd
     */
    private void assertOldPwd(Integer userId, String account, String oldPwd) {
        loginRegisterService
                .assertOldPwd(userId, account, oldPwd)
                .orElseThrow(
                        () -> new OldPasswordWrongException());
    }

    /**
     * 验证登陆信息
     *
     * @param account
     * @param email
     * @param password
     */
    private void assertLoginAccount(String account, String email, String password) {
        loginRegisterService
                .assertLogin(account, email, password)
                .orElseThrow(
                        () -> new LoginErrorException());
    }
}
