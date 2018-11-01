package studio.beita.hdxg.beitasystem.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import studio.beita.hdxg.beitasystem.service.ExamScoreManagementService;

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
    @Autowired
    private ExamScoreManagementService examScoreManagementService;

    // TODO: 2018/10/30 考生根据准考证和姓名查询成绩，成绩开放时间

    @ApiOperation(value = "考试查询成绩", notes = "user get examScore")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "identifier", value = "准考证", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "name", value = "姓名", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping("/user/getExamScore")
    public ResponseEntity<?> getExamScoreByIdentifier(String identifier,String name){
        //判断考试开放未完成
        Optional<String> optionalString = examScoreManagementService.checkUserInfo(identifier,name);
        if(!optionalString.isPresent()){
            return ResponseEntity.ok("准考证和姓名不符！");
        }else{
            return ResponseEntity.ok(examScoreManagementService.getExamScoreByIdentifier(identifier));
        }
    }
    // TODO: 2018/11/1 管理员excel上传 自己上传 

    @ApiOperation(value = "管理员查询考试成绩表", notes = "user get examScore")
    @GetMapping("/user/getExamScoreList")
    public ResponseEntity<?> getExamScoreList(){
        // TODO: 2018/11/1 管理员 
        return ResponseEntity.ok(examScoreManagementService.getExamScoreList());
    }

}
