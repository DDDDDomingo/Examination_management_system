package studio.beita.hdxg.beitasystem.repository;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import studio.beita.hdxg.beitasystem.repository.provider.PermissionManagementDaoProvider;

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
}
