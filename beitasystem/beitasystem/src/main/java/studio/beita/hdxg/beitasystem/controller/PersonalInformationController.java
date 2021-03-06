package studio.beita.hdxg.beitasystem.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import studio.beita.hdxg.beitasystem.annotation.ControllerLog;
import studio.beita.hdxg.beitasystem.constant.ResponseConstant;
import studio.beita.hdxg.beitasystem.model.domain.Permission;
import studio.beita.hdxg.beitasystem.service.PersonalInformationService;
import studio.beita.hdxg.beitasystem.utils.UploadUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    // TODO: 2018/10/30 图片存储功能待更改 头像更改（除了初始头像，其他直接删除）修改信息除了包含图片的，其他都测试过了
    // TODO: 2018/11/1 loginService 更改密码

    @Value("${WEBSITE_ADDRESS}")
    private String WEBSITE_ADDRESS;
    /**
     * 产品图片映射位置
     */
    @Value("${USER_AVATAR_PATH_PATTERN}")
    private String USER_AVATAR_PATH_PATTERN;
    /**
     * 产品图片存储位置
     */
    @Value("${USER_AVATAR_FILE_REPOSITORY}")
    private String USER_AVATAR_FILE_REPOSITORY;

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
    @PutMapping("/user/information/update")
    @ControllerLog(description = "用户修改个人信息")
    public ResponseEntity<?> changeUserDetails(@RequestParam("detailsId")String detailsId, @RequestParam("avatar") MultipartFile file, @RequestParam("phone")String phone,
                                               @RequestParam("address")String address, @RequestParam("realName")String realName, @RequestParam("idCard")String idCard) {
        String fileName = UploadUtils.uploadPhoto(file, USER_AVATAR_FILE_REPOSITORY);
        if (fileName != null) {
            if (personalInformationService.changeUserDetails(detailsId, USER_AVATAR_FILE_REPOSITORY + "/" + fileName, WEBSITE_ADDRESS + USER_AVATAR_PATH_PATTERN + "/" + fileName, phone, address, realName, idCard)) {
                return ResponseEntity
                        .ok(ResponseConstant.CHANGE_USERDETAILS_SUCCESS);
            } else {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("服务器繁忙！个人信息更新失败");
            }
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("上传图片失败！");
        }
    }

    @ApiOperation(value = "用户修改头像", notes = "user change avatar")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "detailsId", value = "用户ID", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "avatar", value = "头像", dataType = "String", paramType = "query", required = true)
    })
    @PutMapping("/user/avatar/update")
    @ControllerLog(description = "用户修改头像")
    public ResponseEntity<?> changeUserAvatar(@RequestParam("detailsId")String detailsId, @RequestParam("avatar") MultipartFile file) {
        String fileName = UploadUtils.uploadPhoto(file, USER_AVATAR_FILE_REPOSITORY);
        if(fileName != null){
            personalInformationService.changeUserAvatar(detailsId, USER_AVATAR_FILE_REPOSITORY + "/" + fileName, WEBSITE_ADDRESS + USER_AVATAR_PATH_PATTERN + "/" + fileName);
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
    @PutMapping("/user/identity/update")
    @ControllerLog(description = "用户修改身份信息")
    public ResponseEntity<?> changeUserIdentity(@RequestParam("detailsId")String detailsId, @RequestParam("realName")String realName, @RequestParam("idCard")String idCard) {
        if(personalInformationService.changeUserIdentity(detailsId, realName, idCard)){
            return ResponseEntity.ok("身份信息更改成功！");
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("服务器繁忙！个人信息更新失败");
        }
    }

    @ApiOperation(value = "用户修改电话地址", notes = "user change phoneAddress")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "detailsId", value = "用户ID", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "phone", value = "电话", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "address", value = "地址", dataType = "String", paramType = "query", required = true)
    })
    @PutMapping("/user/phoneAddress/update")
    @ControllerLog(description = "用户修改电话地址")
    public ResponseEntity<?> changeUserPhoneAddress(@RequestParam("detailsId")String detailsId, @RequestParam("phone")String phone, @RequestParam("address")String address) {
        if(personalInformationService.changeUserPhoneAddress(detailsId, phone, address)){
            return ResponseEntity.ok("身份信息更改成功！");
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("服务器繁忙！个人信息更新失败");
        }
    }
    
    @ApiOperation(value = "用户获取个人信息", notes = "user get UserDetails")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping("/user/userDetails/get")
    @ControllerLog(description = "用户获取个人信息")
    public ResponseEntity<?> getUserDetailsById(@RequestParam("userId")String userId) {
        return ResponseEntity
                .ok(personalInformationService.getUserDetailsById(userId));
    }

    @ApiOperation(value = "管理员获取系统通知", notes = "user get systemNotice")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "receiveId", value = "接受者ID", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping("/admin/SystemNotice/get")
    @ControllerLog(description = "管理员获取系统通知")
    public ResponseEntity<?> getSystemNoticeByIdByAdmin(String receiveId) {
        return ResponseEntity
                .ok(personalInformationService.getSystemNoticeById(receiveId));
    }

    @ApiOperation(value = "获取用户组List", notes = "user get getUserGroupList")
    @GetMapping("/admin/UserGroupList/get")
    @ControllerLog(description = "管理员获取用户组List")
    public ResponseEntity<?> getUserGroupListByAdmin() {
        return ResponseEntity
                .ok(personalInformationService.getUserGroupList());
    }

    @ApiOperation(value = "管理员获取权限信息", notes = "user get permission")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "发送者ID", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping("/admin/Permission/get")
    @ControllerLog(description = "管理员获取权限信息")
    public ResponseEntity<?> getPermissionByUserIdByAdmin(String userId) {
        return ResponseEntity
               .ok(personalInformationService.getPermissionByUserId(userId));
    }

}
