package studio.beita.hdxg.beitasystem.exception.LoginRegister;

import org.apache.commons.lang3.StringUtils;
import studio.beita.hdxg.beitasystem.constant.ResponseConstant;

/**
 * @author ydq
 * @program: beitasystem
 * @Title: LoginErrorException
 * @package: studio.beita.hdxg.beitasystem.exception.LoginRegister
 * @description: 登陆异常，账号或者密码错误
 **/

public class LoginErrorException extends RuntimeException {
    private static final long serialVersionUID = -7614677024243374444L;

    public LoginErrorException() {
    }

    @Override
    public String getMessage() {
        return ResponseConstant.ACCOUNT_OR_PWD_WRONG;
    }
}
