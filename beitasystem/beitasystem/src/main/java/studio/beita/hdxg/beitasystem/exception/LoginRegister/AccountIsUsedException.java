package studio.beita.hdxg.beitasystem.exception.LoginRegister;

/**
 * @author ydq
 * @program: beitasystem
 * @Title: AccountIsUsedException
 * @package: studio.beita.hdxg.beitasystem.exception.LoginRegister
 * @description: 用户名已被占用异常
 **/

import org.apache.commons.lang3.StringUtils;
import studio.beita.hdxg.beitasystem.constant.ResponseConstant;

public class AccountIsUsedException extends RuntimeException {

    private static final long serialVersionUID = 3601094078187290021L;

    public AccountIsUsedException() {
    }

    @Override
    public String getMessage() {
        return ResponseConstant.ACCOUNT_ISUSED;
    }
}
