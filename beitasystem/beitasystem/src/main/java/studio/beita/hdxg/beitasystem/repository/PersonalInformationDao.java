package studio.beita.hdxg.beitasystem.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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
     * 管理员查看自己权限/系统消息未写
     */

    /**
     * 管理员添加用户个人信息
     * @param detailsId
     * @param avatar
     * @param phone
     * @param address
     * @param realname
     * @param idcard
     * @return
     */
    Integer insertUserDetailsByUser(Integer detailsId, String avatar, String phone, String address, String realname, String idcard);

    /**
     * 用户修改用户个人信息
     * @param detailsId
     * @param avatar
     * @param phone
     * @param address
     * @param realname
     * @param idcard
     * @return
     */
    Integer changeUserDetails(Integer detailsId, String avatar, String phone, String address, String realname, String idcard);
}
