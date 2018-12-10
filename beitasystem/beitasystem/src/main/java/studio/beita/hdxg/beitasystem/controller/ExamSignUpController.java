package studio.beita.hdxg.beitasystem.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import studio.beita.hdxg.beitasystem.model.domain.ReviewPersonnel;
import studio.beita.hdxg.beitasystem.service.ExamManagementService;
import studio.beita.hdxg.beitasystem.service.ExamSignUpService;
import studio.beita.hdxg.beitasystem.service.PersonalInformationService;
import studio.beita.hdxg.beitasystem.utils.UploadUtils;

import java.util.Date;
import java.util.List;

import static studio.beita.hdxg.beitasystem.utils.ExamSignUpUtils.getBirthMonth;
import static studio.beita.hdxg.beitasystem.utils.ExamSignUpUtils.getmonth;

/**
 * @author zr
 * @program: beitasystem
 * @Title: WebPortalsController
 * @package: studio.beita.hdxg.beitasystem.controller
 * @description: 考试报名模块控制器
 **/
@Api(value = "ExamSignUpController", description = "ExamSignUpController")
@RestController
public class ExamSignUpController {

    @Value("${WEBSITE_ADDRESS}")
    private String WEBSITE_ADDRESS;
    /**
     * 支付凭证映射位置
     */
    @Value("${USER_SIGNUPPIC_PATTERN}")
    private String USER_SIGNUPPIC_PATTERN;
    /**
     * 支付凭证存储位置
     */
    @Value("${USER_SIGNUPPIC_FILE_REPOSITORY}")
    private String USER_SIGNUPPIC_FILE_REPOSITORY;

    /**
     * 准考证存储地址
     */
    @Value("${USER_TICKET_FILE_REPOSITORY}")
    private String USER_TICKET_FILE_REPOSITORY;

    @Autowired
    private ExamSignUpService examSignUpService;

    @Autowired
    private PersonalInformationService personalInformationService;

    @Autowired
    private ExamManagementService examManagementService;

    @ApiOperation(value = "考生身份信息是否完善验证", notes = "user check authentication")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping("/authentication/get")
    public ResponseEntity<?> userAuthentication(@RequestParam("userId")String userId){
        return ResponseEntity
                .ok(examSignUpService.userAuthentication(userId));
    }

    @ApiOperation(value = "考生取出可以报名的考试类别表清单", notes = "user get ExamInfoList")
    @GetMapping("/examInfoList/get")
    public ResponseEntity<?> getExamInfoList(){
        if(examSignUpService.getExamInfoList()!=null) {
            return ResponseEntity
                    .ok(examSignUpService.getExamInfoList());
        }else{
            return ResponseEntity.ok("暂时没有可以报名的考试");
        }
    }

    @ApiOperation(value = "考生报名考试", notes = "admin insert examNews")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "examTypeId", value = "考试类别表id", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "detailsId", value = "用户个人信息表id", dataType = "String", paramType = "query", required = true)
    })
    @PostMapping("/examSignup/add")
    public ResponseEntity<?> insertExamSignupListByUser(@RequestParam("examTypeId")String examTypeId, @RequestParam("detailsId")String detailsId, @RequestParam("signUpPic") MultipartFile file) {
        String idCard = personalInformationService.getUserDetailsById(detailsId).get().getIdCard();
        String fileName = UploadUtils.uploadPhoto(file, USER_SIGNUPPIC_FILE_REPOSITORY);
        if(examSignUpService.insertExamSignupListByUser(examTypeId, detailsId, WEBSITE_ADDRESS + USER_SIGNUPPIC_PATTERN + "/" +fileName, new Date(), false, getBirthMonth(idCard))){
            return ResponseEntity.ok("报名成功！");
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("服务器繁忙！报名失败");
        }
    }

    @ApiOperation(value = "验证是否为审核该考试的管理员", notes = "user get ExamInfoList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "examTypeId", value = "考试类别表id", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "detailsId", value = "用户个人信息表id", dataType = "Integer", paramType = "query", required = true),

    })
    @GetMapping("/administrator/get")
    public ResponseEntity<?> verifyAdministratorByAdmin(@RequestParam("typeId")String typeId,@RequestParam("userId")String userId){
        if(examSignUpService.verifyAdministrator(typeId, userId)!=null) {
            return ResponseEntity
                    .ok("审核通过");
        }else{
            return ResponseEntity.ok("不是该考试的审核员");
        }
    }

    @ApiOperation(value = "审核管理员审核考生", notes = "admin check user")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "typeId", value = "考试类别表id", dataType = "Integer", paramType = "query", required = true),
            @ApiImplicitParam(name = "userId", value = "用户个人信息表id", dataType = "String", paramType = "query", required = true)
    })
    @PostMapping("/authentication/get")
    public ResponseEntity<?> checkAuthenticationByAdmin(@RequestParam("typeId")String typeId,@RequestParam("userId")String userId) {
        List<ReviewPersonnel> reviewPersonnelList = examSignUpService.getExamAdminNumberByExamTypeId(typeId).get();
        int examAdminNumber = reviewPersonnelList.size();
        return ResponseEntity
                .ok(examSignUpService.reviewCandidateInformation(typeId,getmonth(examAdminNumber,userId,reviewPersonnelList)));
    }

    @ApiOperation(value = "管理员点击通过审核", notes = "admin pass authentication")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "typeId", value = "考试类别id", dataType = "String", paramType = "query", required = true)
    })
    @PutMapping("/admin/authentication/updata")
    public ResponseEntity<?> changeExamSignupListByAdmin(@RequestParam("userId")String userId,@RequestParam("typeId")String typeId){
        if(examSignUpService.changeExamSignupList(userId,typeId)){
            examSignUpService.changeCandidateNum(typeId);
            // TODO: 2018/11/7 发送邮件
            //考生人数到达上限
            if (examSignUpService.getPassNumByAdmin(typeId).equals(examManagementService.getExamDetails(typeId).get().getCapacity())){
                examManagementService.changeExamSignUp(typeId, false);
            }
            return ResponseEntity
                    .ok("审核通过！");
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("服务器繁忙！审核失败");
        }
    }

    @ApiOperation(value = "管理员点击不通过审核", notes = "admin delete authentication")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "typeId", value = "考试类别id", dataType = "String", paramType = "query", required = true)
    })
    @DeleteMapping("/admin/authentication/delete")
    public ResponseEntity<?> deleteCandidateByUserIdByAdmin(@RequestParam("userId")String userId,@RequestParam("typeId")String typeId){
        if(examSignUpService.deleteCandidateByUserId(userId,typeId)){
            // TODO: 2018/11/7 发送邮件
            return ResponseEntity.ok("删除考生成功！");
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("服务器繁忙！删除考生失败");
        }
    }

    @ApiOperation(value = "管理员生成准考证", notes = "admin generate ticket")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "examId", value = "考试类别ID", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "schoolName", value = "学校名称", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping("/admin/ticket/generate")
    public ResponseEntity<?> generateAdmissionTicketByAdmin(String examId, String schoolName){
        return ResponseEntity.ok("");
    }
}
