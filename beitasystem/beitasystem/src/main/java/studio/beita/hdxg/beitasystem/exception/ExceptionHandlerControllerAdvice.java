package studio.beita.hdxg.beitasystem.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.http.HttpServletRequest;

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

    /********************************** HELPER METHOD **********************************/
    private void logError(HttpServletRequest request, Exception e) {
        log.error("[URI: " + request.getRequestURI() + "]"
                + "[error: " + e.getMessage() + "]");
    }
}
