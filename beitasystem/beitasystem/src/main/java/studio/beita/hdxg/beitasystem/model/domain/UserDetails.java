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

    private Integer detailsId;
    private String avatar;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String phone;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String address;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String realName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String idCard;

    public UserDetails(){}

    public UserDetails(Integer detailsId,String avatar,String phone,String address,String realName,String idCard){
        this.detailsId = detailsId;
        this.avatar = avatar;
        this.phone = phone;
        this.address = address;
        this.realName = realName;
        this.idCard = idCard;
    }

    public Integer getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(Integer detailsId) {
        this.detailsId = detailsId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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
                "detailsId=" + detailsId +
                ", avatar='" + avatar + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", realName='" + realName + '\'' +
                ", idCard='" + idCard + '\'' +
                '}';
    }
}
