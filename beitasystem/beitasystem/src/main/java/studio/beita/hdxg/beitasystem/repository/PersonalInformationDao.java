package studio.beita.hdxg.beitasystem.repository;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import studio.beita.hdxg.beitasystem.model.domain.Permission;
import studio.beita.hdxg.beitasystem.model.domain.SystemNotice;
import studio.beita.hdxg.beitasystem.model.domain.UserInfo;
import studio.beita.hdxg.beitasystem.repository.provider.PersonalInformationDaoProvider;

import java.util.List;

/**
 * @author zr
 * @program: beitasystem
 * @Title: PersonalInformationDao
 * @package: studio.beita.hdxg.beitasystem.service
 * @description: 个人信息模块Service层接口
 */
@Mapper
@Repository
public interface PersonalInformationDao {

    /**
     * 系统添加用户个人信息
     * @param detailsId
     * @param avatar
     * @param phone
     * @param address
     * @param realName
     * @param idCard
     * @return
     */
    @InsertProvider(type = PersonalInformationDaoProvider.class, method = "insertUserDetailsByUser")
    Integer insertUserDetailsByUser(@Param("detailsId")Integer detailsId,@Param("avatar") String avatar,@Param("phone") String phone, @Param("address")String address, @Param("realName")String realName,@Param("idCard") String idCard);

    /**
     * 用户修改用户个人信息
     * @param detailsId
     * @param avatar
     * @param phone
     * @param address
     * @param realName
     * @param idCard
     * @return
     */
    @UpdateProvider(type = PersonalInformationDaoProvider.class, method = "changeUserDetails")
    Integer changeUserDetails(Integer detailsId, String avatar, String phone, String address, String realName, String idCard);

    /**
     * 用户修改用户头像
     * @param detailsId
     * @param avatar
     * @return
     */
    @UpdateProvider(type = PersonalInformationDaoProvider.class, method = "changeUserAvatar")
    Integer changeUserAvatar(Integer detailsId, String avatar);

    /**
     * 用户修改用户真实姓名和身份证
     * @param detailsId
     * @param realName
     * @param idCard
     * @return
     */
    @UpdateProvider(type = PersonalInformationDaoProvider.class, method = "changeUserIdentity")
    Integer changeUserIdentity(Integer detailsId, String realName, String idCard);

    /**
     * 用户修改用户电话号码和地址
     * @param detailsId
     * @param phone
     * @param address
     * @return
     */
    @UpdateProvider(type = PersonalInformationDaoProvider.class, method = "changeUserPhoneAddress")
    Integer changeUserPhoneAddress(Integer detailsId, String phone, String address);

    /**
     * 用户通过userId获取自己的个人信息
     * @param userId
     * @return
     */
    @Select("SELECT details_id, details_avatar, details_phone, details_address, details_realname, details_idcard FROM user_details WHERE details_id = #{userId}")
    UserInfo getUserInfoById(Integer userId);

    /**
     * 管理员通过receiveId获取系统通知
     * @param receiveId
     * @return
     */
    @Select("SELECT notice_id, notice_sender_id, notice_receive_id, notice_content, notice_createtime, notice_isread FROM user_details WHERE notice_receive_id = #{receiveId} ORDER BY notice_createtime DESC")
    SystemNotice getSystemNoticeById(Integer receiveId);

    /**
     * 管理员通过ID获取自己的权限信息
     * @param userId
     * @return
     */
    List<Permission> getPermissionByUserId(Integer userId);

    /**
     * 用户通过ID获取用户组中对应角色ID
     * @param userId
     * @return
     */
    @Select("SELECT role_id FROM rel_ug_role WHERE userinfo_id = #{userinfoId}")
    @Results(
            id = "getroleId",
            value = {
                    @Result(property="roleId",column="role_id"),
            }
    )
    Integer getUserRoleById(Integer userinfoId);

    /**
     * 角色通过角色ID获取角色权限组中的权限信息ID
     * @param userRoleId
     * @return
     */
    @Select("SELECT permission_id FROM rel_role_pm WHERE role_id = #{userRoleId}")
    @Results(
            id = "getPermissionId",
            value = {
                    @Result(property="PermissionId",column="permission_id"),
            }
    )
    Integer getPermissionIdByUserRoleId(Integer userRoleId);

    /**
     * 通过权限ID访问权限信息
     * @param PermissionId
     * @return
     */
    @Select("SELECT permission_id, permission_type FROM permission WHERE permission_id = #{PermissionId}")
    @Results(
            id = "getPermission",
            value = {
                    @Result(id = true, property = "PermissionId", column = "permission_id"),
                    @Result(property="permissionType",column="permission_type"),
            }
    )
    List<Permission> getPermissionByPermissionId(Integer PermissionId);
    // TODO: 2018/10/24 确定权限信息的返回值
}
