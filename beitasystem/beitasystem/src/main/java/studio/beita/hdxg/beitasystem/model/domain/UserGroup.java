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
    private List<UserInfo> UserInfoList;
    private List<UserRole> UserRoleList;

    public UserGroup(){}
    public UserGroup(Integer groupId, String name){
        this.groupId = groupId;
        this.name = name;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public UserGroup setGroupId(Integer groupId) {
        this.groupId = groupId;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserGroup setName(String name) {
        this.name = name;
        return this;
    }

    public List<UserInfo> getUserInfoList() {
        return UserInfoList;
    }

    public UserGroup setUserInfoList(List<UserInfo> userInfoList) {
        UserInfoList = userInfoList;
        return this;
    }

    public List<UserRole> getUserRoleList() {
        return UserRoleList;
    }

    public UserGroup setUserRoleList(List<UserRole> userRoleList) {
        UserRoleList = userRoleList;
        return this;
    }

    @Override
    public String toString() {
        return "UserGroup{" +
                "groupId=" + groupId +
                ", name='" + name + '\'' +
                '}';
    }

}
