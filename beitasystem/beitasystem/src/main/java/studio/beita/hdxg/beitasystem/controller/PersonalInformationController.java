package studio.beita.hdxg.beitasystem.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import studio.beita.hdxg.beitasystem.service.PersonalInformationService;

/**
 * @author ydq
 * @program: beitasystem
 * @Title: LoginRegisterController
 * @package: studio.beita.hdxg.beitasystem.controller
 * @description: 登陆/注册模块控制器
 **/
@Api(value = "PersonalInformationController", description = "PersonalInformationController")
@RestController
public class PersonalInformationController {

    @Autowired
    private PersonalInformationService personalInformationService;
}
