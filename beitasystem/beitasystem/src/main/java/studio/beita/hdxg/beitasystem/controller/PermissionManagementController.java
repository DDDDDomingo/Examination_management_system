package studio.beita.hdxg.beitasystem.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studio.beita.hdxg.beitasystem.annotation.ControllerLog;
import studio.beita.hdxg.beitasystem.constant.ResponseConstant;
import studio.beita.hdxg.beitasystem.exception.LoginRegister.AccountIsUsedException;
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
            @ApiImplicitParam(name = "groupId", value = "用户组ID", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "email", value = "邮箱", dataType = "String", paramType = "query")
    })
    @PostMapping("/admin/permission/addAdmin")
    @ControllerLog(description = "最高管理员添加下级审核员账号")
    public ResponseEntity<?> insertUserByAdmin(String account, String password, String email, Integer groupId) {
        //验证账号名是否已经被占用
        Optional<String> userInfo = permissionManagementService.isAccountUsed(account);
        userInfo.orElseThrow(
                () -> new AccountIsUsedException());
        //管理员注册账号
        permissionManagementService.insertUserByAdmin(account, password, email, groupId);
        return ResponseEntity.ok(ResponseConstant.ACCOUNT_REGISTER_SUCCESS);
    }

    @ApiOperation(value = "最高管理员获取用户组底下用户信息", notes = "Top administrator get adminList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupId", value = "用户组ID", dataType = "Integer", paramType = "query", required = true)
    })
    @GetMapping("/admin/group/get")
    @ControllerLog(description = "最高管理员获取用户组底下用户信息")
    public ResponseEntity<?> getUserByGroupIdByAdmin(Integer groupId) {
        //返回用户组底下用户列表
        return ResponseEntity
                .ok(permissionManagementService.getUserByGroupId(groupId));
    }

    @ApiOperation(value = "最高管理员从用户组中删除某管理员", notes = "Top administrator delete admin from group")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "管理员ID", dataType = "String", paramType = "query", required = true)
    })
    @DeleteMapping("/admin/group/delete")
    @ControllerLog(description = "最高管理员从用户组中删除某管理员")
    public ResponseEntity<?> deleteUserByAdmin(String userId){
        if(permissionManagementService.deleteUserByAdmin(userId)){
            return ResponseEntity
                    .ok("删除成功！");
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("服务器繁忙！删除失败！请稍后重试！");
        }
    }

    @ApiOperation(value = "管理员获取用户组权限", notes = "administrator get permission")
    @DeleteMapping("/admin/group/get")
    @ControllerLog(description = "管理员获取用户组权限")
    public ResponseEntity<?> getPermissionByAdmin(){
        if(permissionManagementService.getPermissionByAdmin()!=null){
            return ResponseEntity
                    .ok("权限查询成功！");
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("服务器繁忙！查询失败！请稍后重试！");
        }
    }

}
