package studio.beita.hdxg.beitasystem.exception.LoginRegister;

import org.apache.commons.lang3.StringUtils;
import studio.beita.hdxg.beitasystem.constant.ResponseConstant;

/**
 * @author ydq
 * @program: beitasystem
 * @Title: OldPasswordWrongException
 * @package: studio.beita.hdxg.beitasystem.exception.LoginRegister
 * @description: 旧密码错误异常
 **/

public class OldPasswordWrongException extends RuntimeException{
    private static final long serialVersionUID = -2230871167065274595L;


    public OldPasswordWrongException() {
    }


    @Override
    public String getMessage(){
        return ResponseConstant.OLD_PASSWORD_WRONG;
    }
}
