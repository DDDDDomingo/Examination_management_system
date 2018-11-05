package studio.beita.hdxg.beitasystem.model.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author zr
 * @program: beitasystem
 * @Title: UserGroup
 * @package: studio.beita.hdxg.beitasystem.model.domain
 * @description: 用户组表实体类
 **/

public class UserGroup implements Serializable {

    private static final long serialVersionUID = 2095293797407348884L;

    private Integer groupId;
    private String name;
    private List<UserInfo> userInfoList;
    private List<UserRole> userRoleList;

    public UserGroup() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserInfo> getUserInfoList() {
        return userInfoList;
    }

    public void setUserInfoList(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
    }

    public List<UserRole> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<UserRole> userRoleList) {
        this.userRoleList = userRoleList;
    }

    @Override
    public String toString() {
        return "UserGroup{" +
                "groupId=" + groupId +
                ", name='" + name + '\'' +
                ", userInfoList=" + userInfoList +
                ", userRoleList=" + userRoleList +
                '}';
    }
}
