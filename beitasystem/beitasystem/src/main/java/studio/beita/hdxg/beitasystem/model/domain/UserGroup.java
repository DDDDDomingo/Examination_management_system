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
        return userInfoList;
    }

    public UserGroup setUserInfoList(List<UserInfo> userInfoList) {
        userInfoList = userInfoList;
        return this;
    }

    public List<UserRole> getUserRoleList() {
        return userRoleList;
    }

    public UserGroup setUserRoleList(List<UserRole> userRoleList) {
        userRoleList = userRoleList;
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
