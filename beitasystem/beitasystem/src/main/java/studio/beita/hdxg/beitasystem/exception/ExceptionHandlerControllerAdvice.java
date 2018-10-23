package studio.beita.hdxg.beitasystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import studio.beita.hdxg.beitasystem.constant.ErrorCode;
import studio.beita.hdxg.beitasystem.exception.LoginRegister.AccountIsUsedException;

import javax.servlet.http.HttpServletRequest;
import studio.beita.hdxg.beitasystem.model.dto.Error;

/**
 * @author ydq
 * @program: beitaSystem
 * @Title: ExceptionHandlerControllerAdvice
 * @package: studio.beita.hdxg.beitaSystem.exception
 * @description: 统一异常处理类
 **/

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {
    private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(ExceptionHandlerControllerAdvice.class);

    /**
     * 用户账号被占用
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(AccountIsUsedException.class)
    public ResponseEntity<?> userAccountIsUsedExceptionHandler(HttpServletRequest request, AccountIsUsedException e) {
        logError(request, e);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new Error()
                        .setCode(ErrorCode.USER_ACCOUNT_ISUSED)
                        .setMessage(e.getMessage()));
    }

    /********************************** HELPER METHOD **********************************/
    private void logError(HttpServletRequest request, Exception e) {
        log.error("[URI: " + request.getRequestURI() + "]"
                + "[error: " + e.getMessage() + "]");
    }
}
