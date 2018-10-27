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
// TODO: 2018/10/27 修改int ID为String ID

    /**
     * 系统添加用户个人信息
     *
     * @param avatar
     * @param phone
     * @param address
     * @param realName
     * @param idCard
     * @return
     */
    Integer insertUserDetailsByUser(Integer detailsId,String avatar, String phone, String address, String realName, String idCard);

    /**
     * 用户修改用户个人信息
     *
     * @param detailsId
     * @param avatar
     * @param phone
     * @param address
     * @param realName
     * @param idCard
     * @return
     */
    boolean changeUserDetails(Integer detailsId, String avatar, String phone, String address, String realName, String idCard);

    /**
     * 用户修改用户头像
     *
     * @param detailsId
     * @param avatar
     * @return
     */
    boolean changeUserAvatar(Integer detailsId, String avatar);

    /**
     * 用户修改用户真实姓名和身份证
     *
     * @param detailsId
     * @param realName
     * @param idCard
     * @return
     */
    boolean changeUserIdentity(Integer detailsId, String realName, String idCard);

    /**
     * 用户修改用户电话号码和地址
     *
     * @param detailsId
     * @param phone
     * @param address
     * @return
     */
    boolean changeUserPhoneAddress(Integer detailsId, String phone, String address);

    /**
     * 用户通过userId获取自己的个人信息
     *
     * @param userId
     * @return
     */
    Optional<UserDetails> getUserInfoById(Integer userId);

    /**
     * 管理员通过receiveId获取系统通知
     *
     * @param receiveId
     * @return
     */
    Optional<List<SystemNotice>> getSystemNoticeById(Integer receiveId);

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
    Optional<List<Permission>> getPermissionByUserId(Integer userId);
}
