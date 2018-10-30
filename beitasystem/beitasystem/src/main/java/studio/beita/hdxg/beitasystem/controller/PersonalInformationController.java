package studio.beita.hdxg.beitasystem.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import studio.beita.hdxg.beitasystem.constant.ResponseConstant;
import studio.beita.hdxg.beitasystem.service.PersonalInformationService;

/**
 * @author zr
 * @program: beitasystem
 * @Title: PersonalInformationController
 * @package: studio.beita.hdxg.beitasystem.controller
 * @description: 个人信息管理模块控制器
 **/
@Api(value = "PersonalInformationController", description = "PersonalInformationController")
@RestController
public class PersonalInformationController {

    @Autowired
    private PersonalInformationService personalInformationService;

    @ApiOperation(value = "用户修改个人信息", notes = "user change personalInformation")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "detailsId", value = "用户ID", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "avatar", value = "头像", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "phone", value = "电话", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "address", value = "地址", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "realName", value = "真实姓名", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "idCard", value = "身份证", dataType = "String", paramType = "query", required = true)
    })
    @PutMapping("/user/changePI")
    public ResponseEntity<?> changeUserDetails(String detailsId, String avatar, String phone, String address, String realName, String idCard) {
        if(personalInformationService.changeUserDetails(detailsId, avatar, phone, address, realName, idCard)){
            return ResponseEntity.ok(ResponseConstant.CHANGE_USERDETAILS_SUCCESS);
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("服务器繁忙！个人信息更新失败");
        }
    }

    @ApiOperation(value = "用户修改头像", notes = "user change avatar")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "detailsId", value = "用户ID", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "avatar", value = "头像", dataType = "String", paramType = "query", required = true)
    })
    @PutMapping("/user/changeAvatar")
    public ResponseEntity<?> changeUserAvatar(String detailsId, String avatar) {
        if(personalInformationService.changeUserAvatar(detailsId, avatar)){
            return ResponseEntity.ok("修改头像成功！");
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("服务器繁忙！头像更新失败");
        }
    }

    @ApiOperation(value = "用户修改身份信息", notes = "user change identity")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "detailsId", value = "用户ID", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "realName", value = "真实姓名", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "idCard", value = "身份证", dataType = "String", paramType = "query", required = true)
    })
    @PutMapping("/user/changeIdentity")
    public ResponseEntity<?> changeUserIdentity(String detailsId, String realName, String idCard) {
        if(personalInformationService.changeUserIdentity(detailsId, realName, idCard)){
            return ResponseEntity.ok("身份信息成功！");
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("服务器繁忙！个人信息更新失败");
        }
    }

    @ApiOperation(value = "用户获取个人信息", notes = "user get userInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping("/user/getUserInfo")
    public ResponseEntity<?> getUserInfoById(String userId) {
        return ResponseEntity
                .ok(personalInformationService.getUserInfoById(userId));
    }

    @ApiOperation(value = "用户获取系统通知", notes = "user get systemNotice")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "receiveId", value = "发送者ID", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping("/user/getSystemNotice")
    public ResponseEntity<?> getSystemNoticeById(String receiveId) {
        return ResponseEntity
                .ok(personalInformationService.getSystemNoticeById(receiveId));
    }

    @ApiOperation(value = "获取用户组", notes = "user get getUserGroupList")
    @GetMapping("/user/getUserGroupList")
    public ResponseEntity<?> getUserGroupList() {
        return ResponseEntity
                .ok(personalInformationService.getUserGroupList());
    }

    @ApiOperation(value = "管理员获取权限信息", notes = "user get permission")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "发送者ID", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping("/user/getPermission")
    public ResponseEntity<?> getPermissionByUserId(String userId) {
        return ResponseEntity
                .ok(personalInformationService.getPermissionByUserId(userId));
    }
}
