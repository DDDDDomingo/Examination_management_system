package studio.beita.hdxg.beitasystem.model.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @author zr
 * @program: beitasystem
 * @Title: UserDetails
 * @package: studio.beita.hdxg.beitasystem.model.domain
 * @description: 用户个人信息实体类
 */
public class UserDetails implements Serializable {

    private static final long serialVersionUID = 1845910154152320171L;

    private String detailsId;
    private String detailsAvatar;
    private String detailsSavepath;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String phone;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String address;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String realName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String idCard;

    public UserDetails(){}

    public UserDetails(String detailsId, String detailsAvatar, String detailsSavepath, String phone, String address, String realName, String idCard) {
        this.detailsId = detailsId;
        this.detailsAvatar = detailsAvatar;
        this.detailsSavepath = detailsSavepath;
        this.phone = phone;
        this.address = address;
        this.realName = realName;
        this.idCard = idCard;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(String detailsId) {
        this.detailsId = detailsId;
    }

    public String getDetailsAvatar() {
        return detailsAvatar;
    }

    public void setDetailsAvatar(String detailsAvatar) {
        this.detailsAvatar = detailsAvatar;
    }

    public String getDetailsSavepath() {
        return detailsSavepath;
    }

    public void setDetailsSavepath(String detailsSavepath) {
        this.detailsSavepath = detailsSavepath;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "detailsId='" + detailsId + '\'' +
                ", detailsAvatar='" + detailsAvatar + '\'' +
                ", detailsSavepath='" + detailsSavepath + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", realName='" + realName + '\'' +
                ", idCard='" + idCard + '\'' +
                '}';
    }
}
