package studio.beita.hdxg.beitasystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import studio.beita.hdxg.beitasystem.constant.ErrorCode;
import studio.beita.hdxg.beitasystem.exception.ExamManagement.ExamDoesNotExistException;
import studio.beita.hdxg.beitasystem.exception.ExamManagement.ExamIsClosedException;
import studio.beita.hdxg.beitasystem.exception.ExamManagement.ExamQueryCannotChangeException;
import studio.beita.hdxg.beitasystem.exception.LoginRegister.AccountIsUsedException;

import javax.servlet.http.HttpServletRequest;

import studio.beita.hdxg.beitasystem.exception.LoginRegister.LoginErrorException;
import studio.beita.hdxg.beitasystem.exception.LoginRegister.OldPasswordWrongException;
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

    /**
     * 修改密码验证账号失败
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(OldPasswordWrongException.class)
    public ResponseEntity<?> oldPasswordWrongExceptionHandler(HttpServletRequest request, OldPasswordWrongException e) {
        logError(request, e);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new Error()
                        .setCode(ErrorCode.OLD_PASSWORD_WRONG)
                        .setMessage(e.getMessage()));
    }

    /**
     * 登陆失败，请重试
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(LoginErrorException.class)
    public ResponseEntity<?> oldPasswordWrongExceptionHandler(HttpServletRequest request, LoginErrorException e) {
        logError(request, e);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new Error()
                        .setCode(ErrorCode.ASSERT_ACCOUNT_WRONG)
                        .setMessage(e.getMessage()));
    }

    /**
     * 考试已经关闭，无法修改，异常
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(ExamIsClosedException.class)
    public ResponseEntity<?> examIsClosedExceptionHandler(HttpServletRequest request, ExamIsClosedException e) {
        logError(request, e);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new Error()
                        .setCode(ErrorCode.EXAM_IS_CLOSED)
                        .setMessage(e.getMessage()));
    }

    /**
     * 考试成绩查询无法修改异常
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(ExamQueryCannotChangeException.class)
    public ResponseEntity<?> examQueryCannotChangeExceptionHandler(HttpServletRequest request, ExamQueryCannotChangeException e) {
        logError(request, e);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new Error()
                        .setCode(ErrorCode.EXAM_NOT_QUERY)
                        .setMessage(e.getMessage()));
    }

    /**
     * 考试不存在异常
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(ExamDoesNotExistException.class)
    public ResponseEntity<?> examDoesNotExistExceptionHandler(HttpServletRequest request, ExamDoesNotExistException e) {
        logError(request, e);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new Error()
                        .setCode(ErrorCode.EXAM_DOES_NOT_EXIST)
                        .setMessage(e.getMessage()));
    }

    /********************************** HELPER METHOD **********************************/
    private void logError(HttpServletRequest request, Exception e) {
        log.error("[URI: " + request.getRequestURI() + "]"
                + "[error: " + e.getMessage() + "]");
    }
}
