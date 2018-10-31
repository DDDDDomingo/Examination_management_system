package studio.beita.hdxg.beitasystem.service;

import studio.beita.hdxg.beitasystem.model.domain.Permission;
import studio.beita.hdxg.beitasystem.model.domain.SystemNotice;
import studio.beita.hdxg.beitasystem.model.domain.UserDetails;
import studio.beita.hdxg.beitasystem.model.domain.UserGroup;

import java.util.List;
import java.util.Optional;

/**
 * @author zr
 * @program: beitasystem
 * @Title: PersonalInformationService
 * @package: studio.beita.hdxg.beitasystem.service
 * @description: 个人信息模块Service层接口
 **/

public interface PersonalInformationService {


    /**
     * 系统添加用户个人信息
     *
     * @param detailsAvatar
     * @param detailsSavepath
     * @param phone
     * @param address
     * @param realName
     * @param idCard
     * @return
     */
    boolean insertUserDetailsByUser(String detailsId,String detailsAvatar, String detailsSavepath, String phone, String address, String realName, String idCard);

    /**
     * 用户修改用户个人信息
     *
     * @param detailsId
     * @param detailsAvatar
     * @param detailsSavepath
     * @param phone
     * @param address
     * @param realName
     * @param idCard
     * @return
     */
    boolean changeUserDetails(String detailsId, String detailsAvatar, String detailsSavepath, String phone, String address, String realName, String idCard);

    /**
     * 用户修改用户头像
     *
     * @param detailsId
     * @param detailsAvatar
     * @param detailsSavepath
     * @return
     */
    boolean changeUserAvatar(String detailsId, String detailsAvatar, String detailsSavepath);

    /**
     * 用户修改用户真实姓名和身份证
     *
     * @param detailsId
     * @param realName
     * @param idCard
     * @return
     */
    boolean changeUserIdentity(String detailsId, String realName, String idCard);

    /**
     * 用户修改用户电话号码和地址
     *
     * @param detailsId
     * @param phone
     * @param address
     * @return
     */
    boolean changeUserPhoneAddress(String detailsId, String phone, String address);

    /**
     * 用户通过userId获取自己的个人信息
     *
     * @param userId
     * @return
     */
    Optional<UserDetails> getUserInfoById(String userId);

    /**
     * 管理员通过receiveId获取系统通知
     *
     * @param receiveId
     * @return
     */
    Optional<List<SystemNotice>> getSystemNoticeById(String receiveId);

    /**
     * 获取用户组列表
     * @return
     */
    Optional<List<UserGroup>> getUserGroupList();

    /**
     * 根据用户ID获取权限信息
     * @param userId
     * @return
     */
    Optional<List<Permission>> getPermissionByUserId(String userId);
}
