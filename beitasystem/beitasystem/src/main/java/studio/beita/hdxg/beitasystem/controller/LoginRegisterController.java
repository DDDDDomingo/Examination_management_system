package studio.beita.hdxg.beitasystem.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import studio.beita.hdxg.beitasystem.constant.ResponseConstant;
import studio.beita.hdxg.beitasystem.exception.LoginRegister.AccountIsUsedException;
import studio.beita.hdxg.beitasystem.service.LoginRegisterService;

/**
 * @author ydq
 * @program: beitasystem
 * @Title: LoginRegisterController
 * @package: studio.beita.hdxg.beitasystem.controller
 * @description: 登陆/注册模块控制器
 **/
@Api(value = "LoginRegisterController", description = "LoginRegisterController")
@RestController
public class LoginRegisterController {

    @Autowired
    private LoginRegisterService loginRegisterService;

    @PostMapping("/admin/add")
    public ResponseEntity<?> insertUserByAdmin(String account, String password, String eamil) {

        assertAccountIsUsed(account);

        loginRegisterService.insertUserByAdmin(account, password, eamil);
        return ResponseEntity.ok(ResponseConstant.ACCOUNT_REGISTER_SUCCESS);

    }

    /********************************** HELPER METHOD **********************************/

    private void assertAccountIsUsed(String account) {
        loginRegisterService
                .isAccountUsed(account)
                .orElseThrow(
                        () -> new AccountIsUsedException()
                                .setResourceName(account));
    }
}
