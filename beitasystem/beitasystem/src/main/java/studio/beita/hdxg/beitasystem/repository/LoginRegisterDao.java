package studio.beita.hdxg.beitasystem.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import studio.beita.hdxg.beitasystem.model.domain.UserInfo;

/**
 * @author ydq
 * @program: beitasystem
 * @Title: LoginRegisterDao
 * @package: studio.beita.hdxg.beitasystem.repository
 * @description: 登录注册模块接口
 **/
@Mapper
@Repository
public interface LoginRegisterDao {
    /*Dao顺序操作未增删改查*/

    /**
     * 管理员添加管理员账号
     * @param userId
     * @param account
     * @param password
     * @param email
     * @return
     */
    Integer insertUserByAdmin(Integer userId, String account, String password, String email);

    /**
     * 游客注册用户
     * @param userId
     * @param account
     * @param password
     * @param email
     * @return
     */
    Integer register(Integer userId, String account, String password, String email);

    /**
     * 系统通过账号和旧密码验证账号
     * @param userId
     * @param account
     * @param oldPwd
     * @return
     */
    Integer verifyAccount(Integer userId, String account, String oldPwd);

    /**
     * 管理员/用户修改账号密码
     * @param userId
     * @param newPwd
     * @return
     */
    Integer changePassword(Integer userId, String newPwd);

    /**
     * 用户通过userId获取自己的个人信息
     * @param userId
     * @return
     */
    UserInfo getUserInfoById(Integer userId);

    // TODO: 2018/10/21 管理员通过自己的ID获取自己的信息（这里的信息包括权限信息，任务信息）待定 
}
