package studio.beita.hdxg.beitasystem.model.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @author ydq
 * @program: beitasystem
 * @Title: UserInfo
 * @package: studio.beita.hdxg.beitasystem.model.domain
 * @description: 用户账号密码表实体类
 **/

public class UserInfo implements Serializable{

    private static final long serialVersionUID = -8410359363484960640L;

    private Integer userId;
    private String account;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
    private String eamil;

    public UserInfo() {
    }

    public UserInfo(Integer userId, String account, String password, String eamil) {
        this.userId = userId;
        this.account = account;
        this.password = password;
        this.eamil = eamil;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getUserId() {
        return userId;
    }

    public UserInfo setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getAccount() {
        return account;
    }

    public UserInfo setAccount(String account) {
        this.account = account;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserInfo setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEamil() {
        return eamil;
    }

    public UserInfo setEamil(String eamil) {
        this.eamil = eamil;
        return this;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", eamil='" + eamil + '\'' +
                '}';
    }
}
