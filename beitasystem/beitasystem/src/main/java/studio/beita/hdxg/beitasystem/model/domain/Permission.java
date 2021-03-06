package studio.beita.hdxg.beitasystem.model.domain;

import java.io.Serializable;
/**
 * @author zr
 * @program: beitasystem
 * @Title: Permission
 * @package: studio.beita.hdxg.beitasystem.model.domain
 * @description: 权限表实体类
 **/
public class Permission implements Serializable {

    private static final long serialVersionUID = -5380735667376589966L;

    private Integer permissionId;
    private String type;

    public Permission(){}
    public Permission(Integer permissionId, String type){
        this.permissionId = permissionId;
        this.type = type;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "permissionId=" + permissionId +
                ", type='" + type + '\'' +
                '}';
    }
}
