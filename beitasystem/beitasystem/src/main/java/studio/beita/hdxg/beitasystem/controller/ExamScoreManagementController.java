package studio.beita.hdxg.beitasystem.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import studio.beita.hdxg.beitasystem.service.ExamScoreManagementService;

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
}
