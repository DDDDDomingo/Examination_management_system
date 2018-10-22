package studio.beita.hdxg.beitasystem.model.domain;

import java.io.Serializable;

/**
 * @author zr
 * @program: beitasystem
 * @Title: UserRole
 * @package: studio.beita.hdxg.beitasystem.model.domain
 * @description: 角色表实体类
 */
public class UserRole implements Serializable {

    private static final long serialVersionUID = 2348358571657631182L;

    private Integer roleId;
    private String name;

    public UserRole(){}

    public UserRole(Integer roleId,String name){
        this.roleId = roleId;
        this.name = name;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "roleId=" + roleId +
                ", name='" + name + '\'' +
                '}';
    }
}
