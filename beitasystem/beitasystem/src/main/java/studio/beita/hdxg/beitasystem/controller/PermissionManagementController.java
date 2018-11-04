package studio.beita.hdxg.beitasystem.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import studio.beita.hdxg.beitasystem.constant.ResponseConstant;
import studio.beita.hdxg.beitasystem.exception.LoginRegister.AccountIsUsedException;
import studio.beita.hdxg.beitasystem.service.LoginRegisterService;
import studio.beita.hdxg.beitasystem.service.PermissionManagementService;

import java.util.Optional;

/**
 * @author ydq
 * @program: beitasystem
 * @Title: PermissionManagementController
 * @package: studio.beita.hdxg.beitasystem.controller
 * @description: 权限管理模块Controller层
 **/
@Api(value = "PermissionManagementController", description = "PermissionManagementController")
@RestController
public class PermissionManagementController {

    @Autowired
    private PermissionManagementService permissionManagementService;

    @ApiOperation(value = "最高管理员添加下级审核员账号", notes = "add admin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "用户名", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "groupId", value = "用户组ID", dataType = "Integer", paramType = "query", required = true),
            @ApiImplicitParam(name = "email", value = "邮箱", dataType = "String", paramType = "query")
    })
    @PostMapping("/admin/permission/addAdmin")
    public ResponseEntity<?> insertUserByAdmin(String account, String password, String email, Integer groupId) {
        //验证账号名是否已经被占用
        Optional<String> userInfo = permissionManagementService.isAccountUsed(account);
        userInfo.orElseThrow(
                () -> new AccountIsUsedException());
        //管理员注册账号
        permissionManagementService.insertUserByAdmin(account, password, email, groupId);
        return ResponseEntity.ok(ResponseConstant.ACCOUNT_REGISTER_SUCCESS);
    }




}
