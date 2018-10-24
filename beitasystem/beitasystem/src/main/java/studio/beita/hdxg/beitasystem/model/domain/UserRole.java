package studio.beita.hdxg.beitasystem.model.domain;

import java.io.Serializable;
import java.util.List;


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
    private List<Permission> permissionList;

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

    public UserRole setRoleId(Integer roleId) {
        this.roleId = roleId;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserRole setName(String name) {
        this.name = name;
        return this;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public UserRole setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
        return this;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "roleId=" + roleId +
                ", name='" + name + '\'' +
                '}';
    }
}
