package studio.beita.hdxg.beitasystem.service;

import studio.beita.hdxg.beitasystem.model.domain.UserGroup;

import java.util.Optional;

/**
 * @author ydq
 * @program: beitasystem
 * @Title: PermissionManagementService
 * @package: studio.beita.hdxg.beitasystem.service
 * @description: 权限管理模块Service层接口
 **/

public interface PermissionManagementService {
    /**
     * 系统最高管理员添加管理员账号
     *
     * @param account
     * @param password
     * @param email
     * @param groupId
     * @return
     */
    boolean insertUserByAdmin(String account, String password, String email, Integer groupId);

    /**
     * 系统验证账号名是否已被使用
     *
     * @param account
     * @return
     */
    Optional<String> isAccountUsed(String account);

    /**
     * 通过用户组ID获取底下的用户List
     * @param groupId
     * @return
     */
    UserGroup getUserByGroupId(Integer groupId);
}