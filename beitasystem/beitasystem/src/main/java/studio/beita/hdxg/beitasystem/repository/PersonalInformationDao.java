package studio.beita.hdxg.beitasystem.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import studio.beita.hdxg.beitasystem.model.domain.SystemNotice;
import studio.beita.hdxg.beitasystem.model.domain.UserInfo;

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
    Integer insertUserDetailsByUser(Integer detailsId, String avatar, String phone, String address, String realName, String idCard);

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
    Integer changeUserDetails(Integer detailsId, String avatar, String phone, String address, String realName, String idCard);

    /**
     * 管理员通过receiveId获取系统通知
     * @param receiveId
     * @return
     */
    SystemNotice getSystemNoticeById(Integer receiveId);

    /**
     * 用户通过userId获取自己的个人信息
     * @param userId
     * @return
     */
    UserInfo getUserInfoById(Integer userId);

    // TODO: 2018/10/21 管理员通过自己的ID获取自己的信息（这里的信息包括权限信息，任务信息）待定
}
