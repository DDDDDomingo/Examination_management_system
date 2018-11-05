package studio.beita.hdxg.beitasystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studio.beita.hdxg.beitasystem.model.domain.UserGroup;
import studio.beita.hdxg.beitasystem.repository.PermissionManagementDao;
import studio.beita.hdxg.beitasystem.service.PermissionManagementService;
import studio.beita.hdxg.beitasystem.utils.GetUidUtils;

import java.util.Optional;

/**
 * @author ydq
 * @program: beitasystem
 * @Title: PermissionManagementServiceImpl
 * @package: studio.beita.hdxg.beitasystem.service.impl
 * @description:
 **/
@Service
public class PermissionManagementServiceImpl implements PermissionManagementService {

    @Autowired
    private PermissionManagementDao permissionManagementDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertUserByAdmin(String account, String password, String email, Integer groupId) {
        String userId = GetUidUtils.getNewUserId();
        // TODO: 2018/10/29 密码MD5加密
        if (permissionManagementDao.insertUserByAdmin(userId, account, password, email) > 0) {
            return permissionManagementDao.insertRelUiUg(userId, groupId) > 0;
        }
        return false;
    }

    @Override
    public Optional<String> isAccountUsed(String account) {
        return Optional.ofNullable(permissionManagementDao.isAccountUsed(account));
    }

    @Override
    public UserGroup getUserByGroupId(Integer groupId) {
        return permissionManagementDao.getUserByGroupId(groupId);
    }
}
