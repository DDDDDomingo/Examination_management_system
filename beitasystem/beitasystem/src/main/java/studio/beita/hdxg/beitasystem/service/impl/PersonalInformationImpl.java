package studio.beita.hdxg.beitasystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.beita.hdxg.beitasystem.model.domain.Permission;
import studio.beita.hdxg.beitasystem.model.domain.SystemNotice;
import studio.beita.hdxg.beitasystem.model.domain.UserDetails;
import studio.beita.hdxg.beitasystem.model.domain.UserGroup;
import studio.beita.hdxg.beitasystem.repository.PersonalInformationDao;
import studio.beita.hdxg.beitasystem.service.PersonalInformationService;

import java.util.List;
import java.util.Optional;

/**
 * @author zr
 * @program: beitasystem
 * @Title: PersonalInformationImpl
 * @package: studio.beita.hdxg.beitasystem.service.impl
 * @description:
 **/
@Service
public class PersonalInformationImpl implements PersonalInformationService {

    @Autowired
    private PersonalInformationDao personalInformationDao;

    @Override
    public boolean insertUserDetailsByUser(String detailsId, String avatar, String phone, String address, String realName, String idCard) {
        return personalInformationDao.insertUserDetailsByUser(detailsId, avatar, phone, address, realName, idCard) > 0;
    }

    @Override
    public boolean changeUserDetails(String detailsId, String avatar, String phone, String address, String realName, String idCard) {
        return personalInformationDao.changeUserDetails(detailsId, avatar, phone, address, realName, idCard) > 0;
    }

    @Override
    public boolean changeUserAvatar(String detailsId, String avatar) {
        return personalInformationDao.changeUserAvatar(detailsId, avatar) > 0;
    }

    @Override
    public boolean changeUserIdentity(String detailsId, String realName, String idCard) {
        return personalInformationDao.changeUserIdentity(detailsId, realName, idCard) > 0;
    }

    @Override
    public boolean changeUserPhoneAddress(String detailsId, String phone, String address) {
        return personalInformationDao.changeUserPhoneAddress(detailsId,phone, address) > 0;
    }

    @Override
    public Optional<UserDetails> getUserInfoById(String userId) {
        return Optional.empty();
    }

    @Override
    public Optional<List<SystemNotice>> getSystemNoticeById(String receiveId) {
        return Optional.ofNullable(personalInformationDao.getSystemNoticeById(receiveId));
    }

    @Override
    public Optional<List<UserGroup>> getUserGroupList() {
        return Optional.ofNullable(personalInformationDao.getUserGroupList());
    }

    @Override
    public Optional<List<Permission>> getPermissionByUserId(String userId) {
        return Optional.ofNullable(personalInformationDao.getPermissionByUserId(userId));
    }
}
