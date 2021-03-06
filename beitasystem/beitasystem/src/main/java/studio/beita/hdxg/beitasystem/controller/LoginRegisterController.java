package studio.beita.hdxg.beitasystem.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studio.beita.hdxg.beitasystem.annotation.ControllerLog;
import studio.beita.hdxg.beitasystem.constant.ResourceNameConstant;
import studio.beita.hdxg.beitasystem.constant.ResponseConstant;
import studio.beita.hdxg.beitasystem.exception.LoginRegister.AccountIsUsedException;
import studio.beita.hdxg.beitasystem.exception.LoginRegister.LoginErrorException;
import studio.beita.hdxg.beitasystem.exception.LoginRegister.OldPasswordWrongException;
import studio.beita.hdxg.beitasystem.model.domain.UserInfo;
import studio.beita.hdxg.beitasystem.service.LoginRegisterService;
import studio.beita.hdxg.beitasystem.utils.JwtUtil;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Optional;

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

    @ApiOperation(value = "用户注册账号", notes = "user register")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "用户名", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "email", value = "邮箱", dataType = "String", paramType = "query", required = true)
    })
    @PostMapping("/user/register")
    public ResponseEntity<?> register(String account, String password, String email) {
        // TODO: 2018/11/4 添加用户与用户组关系记录
        //验证账号名是否已经被占用
        Optional<String> userInfo = loginRegisterService.isAccountUsed(account);
        if (userInfo.isPresent()) {
            return ResponseEntity.ok("用户名已存在！请重新输入！");
        }
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
        String id = assertLoginAccount(account, email, password);

        String jwt = JwtUtil.generateToken(account);

        HashMap<String, Object> results = new HashMap<>();
        results.put("token", jwt);
        results.put("id", id);
        // TODO: 2018/10/27 添加JWT到Redis
        results.put("message", ResponseConstant.ASSERT_LOGIN_SUCCESS);

        //登陆成功
        return ResponseEntity.ok(results);
    }

    @ApiOperation(value = "管理员登陆验证", notes = "admin login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "用户名", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "email", value = "邮箱", dataType = "String", paramType = "query")
    })
    @GetMapping("/admin/login")
    @ControllerLog(description = "管理员登陆验证")
    public ResponseEntity<?> adminAssertLogin(String account, String email, String password) {
        //验证登陆信息
        String id = assertLoginAccount(account, email, password);
        //返回信息
        HashMap<String, Object> results = new HashMap<>();
        results.put("id", id);
        results.put("account", account);
        // TODO: 2018/10/27 添加JWT
        //登陆成功
        return ResponseEntity.ok(results);
    }

    @ApiOperation(value = "用户修改密码", notes = "user change password")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", dataType = "Integer", paramType = "query", required = true),
            @ApiImplicitParam(name = "account", value = "用户名", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "oldPwd", value = "旧密码", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "newPwd", value = "新密码", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping("/user/password/change")
    public ResponseEntity<?> changeUserPwd(@RequestParam("userId")String userId,@RequestParam("account") String account,@RequestParam("oldPwd") String oldPwd,@RequestParam("newPwd") String newPwd) {
        // TODO: 2018/10/28 待测试
        //验证账号信息是否正确
        assertOldPwd(userId, account, oldPwd);
        //修改密码成功
        loginRegisterService.changePassword(userId, newPwd);
        return ResponseEntity.ok(ResponseConstant.CHANGE_PASSWORD_SUCCESS);
    }

    /********************************** HELPER METHOD **********************************/

    /**
     * 修改密码验证账号是否正确
     *
     * @param userId
     * @param account
     * @param oldPwd
     */
    private void assertOldPwd(String userId, String account, String oldPwd) {
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
    private String assertLoginAccount(String account, String email, String password) {
        return loginRegisterService
                .assertLogin(account, email, password)
                .orElseThrow(
                        () -> new LoginErrorException());
    }
}
