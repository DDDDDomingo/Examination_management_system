package studio.beita.hdxg.beitasystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.beita.hdxg.beitasystem.model.domain.UserInfo;
import studio.beita.hdxg.beitasystem.repository.LoginRegisterDao;
import studio.beita.hdxg.beitasystem.service.LoginRegisterService;
import studio.beita.hdxg.beitasystem.utils.GetUidUtils;

import java.util.Optional;

/**
 * @author ydq
 * @program: beitasystem
 * @Title: LoginRegisterServiceImpl
 * @package: studio.beita.hdxg.beitasystem.service.impl
 * @description:
 **/
@Service
public class LoginRegisterServiceImpl implements LoginRegisterService {

    @Autowired
    private LoginRegisterDao loginRegisterDao;

    @Override
    public boolean insertUserByAdmin(String account, String password, String email) {
        return loginRegisterDao.insertUserByAdmin(GetUidUtils.getNewUserId(), account, password, email) > 0;
    }

    @Override
    public boolean register(String account, String password, String email) {
        return loginRegisterDao.register(GetUidUtils.getNewUserId(), account, password, email) > 0;
    }

    @Override
    public Optional<String> assertLogin(String account, String email, String password) {
        // TODO: 2018/10/24 JWT 缓存
        return Optional.ofNullable(loginRegisterDao.assertLogin(account, email, password));
    }

    @Override
    public Optional<UserInfo> assertOldPwd(String userId, String account, String oldPwd) {
        return Optional.ofNullable(loginRegisterDao.assertOldPwd(userId, account, oldPwd));
    }

    @Override
    public Optional<String> isAccountUsed(String account) {
        return Optional.ofNullable(loginRegisterDao.isAccountUsed(account));
    }

    @Override
    public Integer assertAccountByEmail(String userId, String email) {
        // TODO: 2018/10/22  用户通过邮箱验证账号，Redis保存验证码，邮箱发送验证码
        return null;
    }

    @Override
    public boolean changePassword(String userId, String newPwd) {
        return loginRegisterDao.changePassword(userId, newPwd) > 0;
    }
}
