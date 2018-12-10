package studio.beita.hdxg.beitasystem.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import studio.beita.hdxg.beitasystem.annotation.ControllerLog;
import studio.beita.hdxg.beitasystem.model.domain.ExamScore;
import studio.beita.hdxg.beitasystem.model.domain.ReturnScore;
import studio.beita.hdxg.beitasystem.service.ExamScoreManagementService;
import studio.beita.hdxg.beitasystem.utils.ExcelUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author zr
 * @program: beitasystem
 * @Title: ExamScoreManagementController
 * @package: studio.beita.hdxg.beitasystem.controller
 * @description: 考试成绩管理模块控制器
 **/
@Api(value = "ExamScoreManagementController", description = "ExamScoreManagementController")
@RestController
public class ExamScoreManagementController {

    /**
     * EXCEL存储位置
     */
    @Value("${USER_EXCEL_FILE_REPOSITORY}")
    private String USER_EXCEL_FILE_REPOSITORY;

    @Autowired
    private ExamScoreManagementService examScoreManagementService;

    // TODO: 2018/11/1   发成绩单邮件 调用系统通知接口发邮件给最高管理员

    @ApiOperation(value = "考试查询成绩", notes = "user get examScore")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "identifier", value = "准考证", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "name", value = "姓名", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping("/user/examScore/get")
    public ResponseEntity<?> getExamScoreByIdentifier(String identifier,String name){
        if(!examScoreManagementService.getIsQueryByIdentifier(identifier)){
            return ResponseEntity.ok("该考试成绩查询未开放");
        }
        Optional<String> optionalString = examScoreManagementService.checkUserInfo(identifier,name);
        if(!optionalString.isPresent()){
            return ResponseEntity.ok("准考证和姓名不符！");
        }else{
            return ResponseEntity.ok(examScoreManagementService.getExamScoreByIdentifier(identifier));
        }
    }

    @ApiOperation(value = "管理员excel导入成绩", notes = "admin examScore excel")
    @PutMapping("/admin/inputExamScoreByExcel/get")
    @ControllerLog(description = "管理员excel导入成绩")
    public ResponseEntity<?> getExamScoreListByExcelByAdmin(@RequestParam("file") MultipartFile file) throws IOException {
        List<ReturnScore> returnScoreList = examScoreManagementService.changeExamScoreByByExcel(file);
        if(examScoreManagementService.changeExamScoreByReturnScore(returnScoreList)){
            return ResponseEntity.ok("考生成绩Excel录入成功！");
        }else{
            return ResponseEntity.ok("服务器繁忙Excel考试成绩录入更改失败");
        }
    }

    // TODO: 2018/11/1 管理员excel导出 未测试
    @ApiOperation(value = "管理员excel导出成绩", notes = "admin examScore excel")
    @GetMapping("/admin/outputExamScoreByExcel/get")
    @ControllerLog(description = "管理员excel导出成绩")
    public ResponseEntity<?> outputExamScoreListByExcelByAdmin(Integer sessionId) throws IOException {
        //获取成绩列表
        Optional<List<ExamScore>> examScoreListOptional=examScoreManagementService.getExamScoreListBySession(sessionId);
        examScoreListOptional.get().get(0).toString();
        if(examScoreManagementService.outputExamScoreListByExcel(examScoreListOptional)){
            return ResponseEntity.ok("Excel导出成功");
        }
        return ResponseEntity.ok("Excel导出失败");
    }

    @ApiOperation(value = "管理员查询某场次的考试成绩表", notes = "user get examScore")
    @GetMapping("/admin/ExamScoreList/{sessionId}")
    @ControllerLog(description = "管理员查询某场次的考试成绩表")
    public ResponseEntity<?> getExamScoreListByAdmin(Integer sessionId){
        return ResponseEntity.ok(examScoreManagementService.getExamScoreListBySession(sessionId));
    }

    @ApiOperation(value = "管理员手动更改成绩", notes = "admin change examScore")
    @PutMapping("/admin/manualExamScore/change")
    @ControllerLog(description = "管理员手动更改成绩")
    public ResponseEntity<?> getExamScoreByIdentifierByAdmin(@RequestParam("ReturnScore[]") List<ReturnScore> returnScore){
        if(examScoreManagementService.changeExamScoreByReturnScore(returnScore)){
            return ResponseEntity.ok("考生成绩录入成功！");
        }else{
            return ResponseEntity.ok("服务器繁忙考试成绩更改失败");
        }
    }

    @ApiOperation(value = "管理员查询考试场次", notes = "user get examSession")
    @GetMapping("/admin/examSessionList/get")
    @ControllerLog(description = "管理员查询考试场次")
    public ResponseEntity<?> getExamScoreListByAdmin(){
        return ResponseEntity.ok(examScoreManagementService.getExamSessionList());
    }
}
