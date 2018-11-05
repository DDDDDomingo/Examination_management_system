package studio.beita.hdxg.beitasystem.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studio.beita.hdxg.beitasystem.exception.ExamManagement.ExamDoesNotExistException;
import studio.beita.hdxg.beitasystem.exception.ExamManagement.ExamIsClosedException;
import studio.beita.hdxg.beitasystem.exception.ExamManagement.ExamQueryCannotChangeException;
import studio.beita.hdxg.beitasystem.model.domain.ExamInfo;
import studio.beita.hdxg.beitasystem.service.ExamManagementService;

import java.util.Optional;

/**
 * @author ydq
 * @program: beitasystem
 * @Title: ExamManagementController
 * @package: studio.beita.hdxg.beitasystem.controller
 * @description: 考试管理模块控制器
 **/
@Api(value = "ExamManagementController", description = "ExamManagementController")
@RestController
public class ExamManagementController {

    @Autowired
    private ExamManagementService examManagementService;

    @ApiOperation(value = "最高管理员添加考试", notes = "admin adds exam")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "examName", value = "考试名称", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "examCapacity", value = "考试最大容纳数量", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "startTime", value = "考试开始时间", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "endTime", value = "考试结束时间", dataType = "String", paramType = "query", required = true)
    })
    @PostMapping("/admin/exam/add")
    public ResponseEntity<?> addExam(String examName, Integer examCapacity, String startTime, String endTime) {
        if (examManagementService.addExam(examName, examCapacity, startTime, endTime)) {
            return ResponseEntity
                    .ok("添加考试成功！");

        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("服务器繁忙！添加考试失败！请稍后重试！");
        }
    }

    @ApiOperation(value = "管理员修改：考试是否结束", notes = "exam is closed?")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "examId", value = "考试类型ID", dataType = "String", paramType = "query", required = true)
    })
    @PutMapping("/admin/exam/cgClosed")
    public ResponseEntity<?> changeExamClosed(String examId) {
        if (examManagementService.changeExamClosed(examId)) {
            return ResponseEntity
                    .ok("修改成功！考试已结束！");
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("服务器繁忙！修改考试失败！请稍后重试！");
        }
    }

    @ApiOperation(value = "管理员修改：考试是否可以报名", notes = "exam is sign up?")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "examId", value = "考试类型ID", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "isSignUp", value = "考试是否可以报名", dataType = "boolean", paramType = "query", required = true)
    })
    @PutMapping("/admin/exam/cgSignUp")
    public ResponseEntity<?> changeExamSignUp(String examId, boolean isSignUp) {
        //验证考试是否已经关闭
        assertExamIsClosed(examId);
        //修改考试为可报名
        examManagementService.changeExamSignUp(examId, isSignUp);

        return (isSignUp == true) ? ResponseEntity.ok("修改成功！考试可以报名了！") : ResponseEntity.ok("修改成功！考试目前无法报名！");

    }

    @ApiOperation(value = "管理员修改：考试是否可以查询", notes = "exam is query?")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "examId", value = "考试类型ID", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "isQuery", value = "考试是否可以查询成绩", dataType = "boolean", paramType = "query", required = true)
    })
    @PutMapping("/admin/exam/cgQuery")
    public ResponseEntity<?> changeExamQuery(String examId, boolean isQuery) {
        //修改考试成绩为可查询时，保证考试未结束，考试报名已结束
        assertExamIsClosedSignUp(examId);

        examManagementService.changeExamQuery(examId, isQuery);

        return (isQuery == true) ? ResponseEntity.ok("修改成功！考试成绩可以查询！") : ResponseEntity.ok("修改成功！考试成绩不能查询！");

    }

    // TODO: 2018/10/28 条件查询
    @ApiOperation(value = "管理员获取所有考试列表", notes = "admin get exam list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNumber", value = "页数", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页可容纳数量", dataType = "int", paramType = "query", required = true)
    })
    @GetMapping("/admin/exam/list")
    public ResponseEntity<?> getExamList(Integer pageNumber, Integer pageSize) {
        PageInfo<ExamInfo> examInfoList = new PageInfo<>(examManagementService.getExamList(pageNumber, pageSize));

        return ResponseEntity
                .ok(examInfoList);
    }

    @ApiOperation(value = "考生/管理员通过考试ID获取考试信息", notes = "get exam details by id")
    @GetMapping("/exam/{examId}")
    public ResponseEntity<?> getExamDetails(@PathVariable("examId") String examId) {
        Optional<ExamInfo> examInfo = examManagementService.getExamDetails(examId);

        examInfo.orElseThrow(
                () -> new ExamDoesNotExistException());

        return ResponseEntity
                .ok(examInfo.get());
    }

    // TODO: 2018/10/28 移除考试管理模块 到 考试报名模块
    @ApiOperation(value = "考生获取可查询考试列表", notes = "admin get sign up exam list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNumber", value = "页数", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页可容纳数量", dataType = "int", paramType = "query", required = true)
    })
    @GetMapping("/exam/list")
    public ResponseEntity<?> getSignUpExamList(Integer pageNumber, Integer pageSize) {
        PageInfo<ExamInfo> signUpExamInfoList = new PageInfo<>(examManagementService.getSignUpExamList(pageNumber, pageSize));

        return ResponseEntity
                .ok(signUpExamInfoList);
    }

    // TODO: 2018/10/28 考试场次部分 管理员分配考场1
    @ApiOperation(value = "管理员添加考试场次", notes = "admin add session")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sessionPlace", value = "考试场次地点", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "sessionCapacity", value = "考场容量", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "sessionTime", value = "考试时间", dataType = "String", paramType = "query", required = true)
    })
    @PostMapping("/admin/exam/session/add")
    public ResponseEntity<?> addExamSession(String sessionPlace, Integer sessionCapacity, String sessionTime){
        if(examManagementService.addExamSession(sessionPlace, sessionCapacity, sessionTime)){
            return ResponseEntity
                    .ok("添加考场成功！");
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("服务器繁忙！添加考场失败！请稍后重试！");
        }
    }

    @ApiOperation(value = "管理员删除考试场次", notes = "admin delete session")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "examId", value = "考试类型ID", dataType = "String", paramType = "query", required = true)
    })
    @DeleteMapping("/admin/exam/session/delete")
    public ResponseEntity<?> deleteExamSession(String examId){
        if(examManagementService.deleteExamSession(examId)){
            return ResponseEntity
                    .ok("删除考试成功！");
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("服务器繁忙！删除考试失败！请稍后重试！");
        }
    }

    @ApiOperation(value = "考生/管理员通过考试ID获取考试信息", notes = "get exam details by id")
    @GetMapping("/admin/exam/{examId}")
    public ResponseEntity<?> adminGetExamDetails(@PathVariable("examId") String examId){
        Optional<ExamInfo> examInfoAdmin = examManagementService.adminGetExamDetails(examId);

        examInfoAdmin.orElseThrow(
                () -> new ExamDoesNotExistException());

        return ResponseEntity
                .ok(examInfoAdmin.get());
    }

    /********************************** HELPER METHOD **********************************/

    /**
     * 修改考试是否可以报名时，保证考试未结束
     *
     * @param examId
     */
    private void assertExamIsClosed(String examId) {
        examManagementService
                .assertExamIsClosed(examId)
                .orElseThrow(
                        () -> new ExamIsClosedException());
    }

    /**
     * 修改考试成绩为可查询时，保证考试未结束，考试报名已结束
     *
     * @param examId
     */
    private void assertExamIsClosedSignUp(String examId) {
        examManagementService
                .assertExamIsClosedSignUp(examId)
                .orElseThrow(
                        () -> new ExamQueryCannotChangeException());
    }

}
