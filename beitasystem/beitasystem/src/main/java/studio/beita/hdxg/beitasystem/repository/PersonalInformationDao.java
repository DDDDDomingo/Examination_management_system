package studio.beita.hdxg.beitasystem.repository;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import studio.beita.hdxg.beitasystem.model.domain.*;
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
     *
     * @param detailsId
     * @param avatar
     * @param phone
     * @param address
     * @param realName
     * @param idCard
     * @return
     */
    @InsertProvider(type = PersonalInformationDaoProvider.class, method = "insertUserDetailsByUser")
    Integer insertUserDetailsByUser(@Param("detailsId") Integer detailsId, @Param("avatar") String avatar, @Param("phone") String phone, @Param("address") String address, @Param("realName") String realName, @Param("idCard") String idCard);

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
    @UpdateProvider(type = PersonalInformationDaoProvider.class, method = "changeUserDetails")
    Integer changeUserDetails(Integer detailsId, String avatar, String phone, String address, String realName, String idCard);

    /**
     * 用户修改用户头像
     *
     * @param detailsId
     * @param avatar
     * @return
     */
    @UpdateProvider(type = PersonalInformationDaoProvider.class, method = "changeUserAvatar")
    Integer changeUserAvatar(Integer detailsId, String avatar);

    /**
     * 用户修改用户真实姓名和身份证
     *
     * @param detailsId
     * @param realName
     * @param idCard
     * @return
     */
    @UpdateProvider(type = PersonalInformationDaoProvider.class, method = "changeUserIdentity")
    Integer changeUserIdentity(Integer detailsId, String realName, String idCard);

    /**
     * 用户修改用户电话号码和地址
     *
     * @param detailsId
     * @param phone
     * @param address
     * @return
     */
    @UpdateProvider(type = PersonalInformationDaoProvider.class, method = "changeUserPhoneAddress")
    Integer changeUserPhoneAddress(Integer detailsId, String phone, String address);

    /**
     * 用户通过userId获取自己的个人信息
     *
     * @param userId
     * @return
     */
    @Select("SELECT details_id, details_avatar, details_phone, details_address, details_realname, details_idcard FROM user_details WHERE details_id = #{userId}")
    UserInfo getUserInfoById(Integer userId);

    /**
     * 管理员通过receiveId获取系统通知
     *
     * @param receiveId
     * @return
     */
    @Select("SELECT notice_id, notice_sender_id, notice_receive_id, notice_content, notice_createtime, notice_isread FROM user_details WHERE notice_receive_id = #{receiveId} ORDER BY notice_createtime DESC")
    SystemNotice getSystemNoticeById(Integer receiveId);


    @Select("SELECT group_id, group_name FROM user_group GROUP BY group_id DESC")
    @Results(
            id = "userGroupList",
            value = {
                    @Result(id = true, property = "groupId", column = "group_id"),
                    @Result(property = "name", column = "group_name"),
                    @Result(property = "userInfoIdList"),
                    @Result(property = "userInfoList", column = "xmlu_avatar"),
                    @Result(property = "userRoleIdList"),
                    @Result(property = "userRoleList", column = "xmlu_avatar")
            }
    )
    List<UserGroup> getUserGroupList();

    // TODO: 2018/10/24 确定权限信息的返回值
}
