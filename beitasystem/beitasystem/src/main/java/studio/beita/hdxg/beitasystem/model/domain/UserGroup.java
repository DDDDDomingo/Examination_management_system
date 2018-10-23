package studio.beita.hdxg.beitasystem.model.domain;

import java.io.Serializable;
/**
 * @author zr
 * @program: UserGroup
 * @Title: UserInfo
 * @package: studio.beita.hdxg.beitasystem.model.domain
 * @description: 用户组表实体类
 **/

public class UserGroup implements Serializable {

    private static final long serialVersionUID = 2095293797407348884L;

    private Integer groupId;
    private String name;

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

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserGroup{" +
                "groupId=" + groupId +
                ", name='" + name + '\'' +
                '}';
    }

}
