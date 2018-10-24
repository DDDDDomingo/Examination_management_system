package studio.beita.hdxg.beitasystem.constant;

/**
 * @author ydq
 * @program: beitaSystem
 * @Title: ResponseConstant
 * @package: studio.beita.hdxg.beitaSystem.constant
 * @description: 响应消息常量
 **/

public class ResponseConstant {

    /**
     * loginRegisterController 200
     */

    public static final String ACCOUNT_REGISTER_SUCCESS = "注册账号成功！";
    public static final String CHANGE_PASSWORD_SUCCESS = "修改密码成功！";
    public static final String ASSERT_LOGIN_SUCCESS = "登陆成功！";

    /**
     * loginRegisterController 400
     */
    public static final String ACCOUNT_ISUSED = "该用户名已被占用，请重新选择用户名！";
    public static final String OLD_PASSWORD_WRONG = "验证失败！请重新输入！";
    public static final String ACCOUNT_OR_PWD_WRONG = "登陆失败！请重试！";

    /**
     * 公有构造函数
     */
    public ResponseConstant() {

    }
}
