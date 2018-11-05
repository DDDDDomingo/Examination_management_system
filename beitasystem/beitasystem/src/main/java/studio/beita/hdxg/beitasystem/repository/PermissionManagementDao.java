package studio.beita.hdxg.beitasystem.repository;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import studio.beita.hdxg.beitasystem.model.domain.UserGroup;
import studio.beita.hdxg.beitasystem.model.domain.UserInfo;
import studio.beita.hdxg.beitasystem.repository.provider.PermissionManagementDaoProvider;

import java.util.List;

/**
 * @author ydq
 * @program: beitasystem
 * @Title: PermissionManagementDao
 * @package: studio.beita.hdxg.beitasystem.repository
 * @description: 权限管理模块Dao层接口
 **/
@Mapper
@Repository
public interface PermissionManagementDao {

    /**
     * 管理员添加管理员账号
     *
     * @param userId
     * @param account
     * @param password
     * @param email
     * @return
     */
    @InsertProvider(type = PermissionManagementDaoProvider.class, method = "insertUserByAdmin")
    Integer insertUserByAdmin(@Param("userId") String userId, @Param("account") String account, @Param("password") String password, @Param("email") String email);

    /**
     * 添加用户与用户组关系
     *
     * @param userId
     * @param groupId
     * @return
     */
    @Insert("INSERT INTO rel_ui_ug (userinfo_id, group_id) VALUES (#{userId},#{groupId})")
    Integer insertRelUiUg(String userId, Integer groupId);

    /**
     * 系统验证账号是否已被使用
     *
     * @param account
     * @return
     */
    @Select("SELECT userinfo_account FROM user_info WHERE userinfo_account = #{account}")
    String isAccountUsed(String account);

    /**
     * 通过用户组ID获取底下的用户
     *
     * @param groupId
     * @return
     */
    @Select("SELECT group_id, group_name FROM user_group WHERE group_id = #{groupId}")
    @Results(
            id = "groupDetails",
            value = {
                    @Result(id = true, property = "groupId", column = "group_id"),
                    @Result(property = "name", column = "group_name"),
                    @Result(property = "userInfoList", column = "group_id", many = @Many(select = "studio.beita.hdxg.beitasystem.repository.PermissionManagementDao.getUserByUserIdList"))
            }
    )
    UserGroup getUserByGroupId(Integer groupId);

    /**
     * 通过管理员ID列表获取管理员列表
     *
     * @param userInfoIdList
     * @return
     */
    @Select("SELECT ui.userinfo_id, ui.userinfo_account FROM user_info ui LEFT JOIN rel_ui_ug ruu ON ui.userinfo_id = ruu.userinfo_id WHERE group_id = #{groupId}")
    @Results(
            id = "userInfo",
            value = {
                    @Result(id = true, property = "userId", column = "userinfo_id"),
                    @Result(property = "account", column = "userinfo_account")
            }
    )
    List<UserInfo> getUserByUserIdList(List<Integer> userInfoIdList);

}
