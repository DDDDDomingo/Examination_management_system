package studio.beita.hdxg.beitasystem.aspect;

import eu.bitwalker.useragentutils.UserAgent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import studio.beita.hdxg.beitasystem.annotation.ControllerLog;
import studio.beita.hdxg.beitasystem.annotation.ServiceLog;
import studio.beita.hdxg.beitasystem.utils.IpUtils;
import studio.beita.hdxg.beitasystem.utils.StringUtils;
import studio.beita.hdxg.beitasystem.utils.UserAgentUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ydq
 * @program: beitasystem
 * @Title: WebLogAspect
 * @package: studio.beita.hdxg.beitasystem.aspect
 * @description: 贝塔考试管理系统日志管理
 **/
@Aspect
@Component
@Order(2)
public class WebLogAspect {

    private final static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    /**
     * Controller层切点
     */
    @Pointcut("execution(public * studio.beita.hdxg.beitasystem.controller.*Controller.*ByAdmin(..))")
    private void log() {

    }

    /**
     * Service层切点
     */
    @Pointcut("execution(public * studio.beita.hdxg.beitasystem.service.*.*(..))")
    public void serviceAspect() {

    }

    @Before("log()")
    public void beforeLog(JoinPoint joinPoint) {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            //类名
            String className = joinPoint.getTarget().getClass().getName();
            //请求方法
            String method = joinPoint.getSignature().getName() + "()";
            //方法参数
            String methodParam = Arrays.toString(joinPoint.getArgs());
            Map<String, String[]> params = request.getParameterMap();
            String decode = "";
            //针对get请求
            if (request.getQueryString() != null) {
                try {
                    decode = URLDecoder.decode(request.getQueryString(), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else {
                //针对post请求
                for (String key : params.keySet()) {
                    String[] values = params.get(key);
                    for (int i = 0; i < values.length; i++) {
                        String value = values[i];
                        decode += key + "=" + value + "&";
                    }
                }
            }
            //将String根据&转成Map
            Map<String, Object> methodParamMap = transStringToMap(decode, "&", "=");
            //设置日期格式
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //方法描述
            String methodDescription = getControllerMethodDescription(joinPoint);
            StringBuilder sb = new StringBuilder(1000);
            sb.append("\n");
            sb.append("*********************************Request请求***************************************");
            sb.append("\n");
            sb.append("ClassName     :  ").append(className).append("\n");
            sb.append("RequestMethod :  ").append(method).append("\n");
            sb.append("ContentType   :  ").append(("".equals(request.getContentType()) || request.getContentType() == null) ? "FROM" : request.getContentType()).append("\n");
            sb.append("RequestParams :  ").append(("".equals(decode) || decode == null) ? methodParam : methodParamMap).append("\n");
            sb.append("RequestType   :  ").append(request.getMethod()).append("\n");
            sb.append("Description   :  ").append(methodDescription).append("\n");
            sb.append("ServerAddr    :  ").append(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()).append("\n");
            sb.append("RemoteAddr    :  ").append(IpUtils.getRemoteAddr(request)).append("\n");
            UserAgent userAgent = UserAgentUtils.getUserAgent(request);
            sb.append("DeviceName    :  ").append(userAgent.getOperatingSystem().getName()).append("\n");
            sb.append("BrowserName   :  ").append(userAgent.getBrowser().getName()).append("\n");
            sb.append("UserAgent     :  ").append(request.getHeader("User-Agent")).append("\n");
            sb.append("RequestUri    :  ").append(StringUtils.abbr(request.getRequestURI(), 255)).append("\n");
            sb.append("**************************");
            sb.append(df.format(new Date()));
            sb.append("***********************************");
            sb.append("\n");
            logger.info(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 异常通知 用于拦截service层记录异常日志
     *
     * @param joinPoint
     * @param exception
     */
   /* @AfterThrowing(pointcut = "serviceAspect()", throwing = "exception")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            //类名
            String className = joinPoint.getTarget().getClass().getName();
            //请求方法
            String method = (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()");
            //方法参数
            String methodParam = Arrays.toString(joinPoint.getArgs());
            //方法描述
            String methodDescription = getServiceMthodDescription(joinPoint);
            //获取用户请求方法的参数并序列化为JSON格式字符串
            String params = Arrays.toString(joinPoint.getArgs());
            StringBuilder sb = new StringBuilder(1000);
            sb.append("\n");
            sb.append("*********************************Service异常***************************************");
            sb.append("\n");
            sb.append("ClassName        :  ").append(className).append("\n");
            sb.append("Method           :  ").append(method).append("\n");
            sb.append("Params           :  ").append("[" + params + "]").append("\n");
            sb.append("Description      :  ").append(methodDescription).append("\n");
            sb.append("ExceptionName    :  ").append(exception.getClass().getName()).append("\n");
            sb.append("ExceptionMessage :  ").append(exception.getMessage()).append("\n");
            logger.info(sb.toString());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }*/

    /**
     * 获取注解中对方法的描述信息 用于service层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    /*public static String getServiceMthodDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(ServiceLog.class).description();
                    break;
                }
            }
        }
        return description;
    }*/

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(ControllerLog.class).description();
                    break;
                }
            }
        }
        return description;
    }

    /**
     * String 转Map
     *
     * @param mapString     待转的String
     * @param separator     分割符
     * @param pairSeparator 分离器
     * @return
     */
    public static Map<String, Object> transStringToMap(String mapString, String separator, String pairSeparator) {
        Map<String, Object> map = new HashMap<String, Object>();
        String[] fSplit = mapString.split(separator);
        for (int i = 0; i < fSplit.length; i++) {
            if (fSplit[i] == null || fSplit[i].length() == 0) {
                continue;
            }
            String[] sSplit = fSplit[i].split(pairSeparator);
            String value = fSplit[i].substring(fSplit[i].indexOf('=') + 1, fSplit[i].length());
            map.put(sSplit[0], value);
        }
        return map;
    }


}
