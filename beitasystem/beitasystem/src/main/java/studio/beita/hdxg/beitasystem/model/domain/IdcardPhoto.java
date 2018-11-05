package studio.beita.hdxg.beitasystem.model.domain;

import java.io.Serializable;

/**
 * @author zr
 * @program: beitasystem
 * @Title: IdcardPhoto
 * @package: studio.beita.hdxg.beitasystem.model.domain
 * @description: 身份证照片表实体类
 */
public class IdcardPhoto implements Serializable {

    private static final long serialVersionUID = -40150416296668880L;

    private Integer idcardId;
    private String userInfoId;
    private String frontPhotoUrl;
    private String frontPhotoSavepath;
    private String reversePhotoUrl;
    private String reversePhotoSavepath;

    public IdcardPhoto(){}
    public IdcardPhoto(Integer idcardId, String userInfoId, String frontPhotoUrl, String frontPhotoSavepath, String reversePhotoUrl, String reversePhotoSavepath) {
        this.idcardId = idcardId;
        this.userInfoId = userInfoId;
        this.frontPhotoUrl = frontPhotoUrl;
        this.frontPhotoSavepath = frontPhotoSavepath;
        this.reversePhotoUrl = reversePhotoUrl;
        this.reversePhotoSavepath = reversePhotoSavepath;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getIdcardId() {
        return idcardId;
    }

    public void setIdcardId(Integer idcardId) {
        this.idcardId = idcardId;
    }

    public String getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getFrontPhotoUrl() {
        return frontPhotoUrl;
    }

    public void setFrontPhotoUrl(String frontPhotoUrl) {
        this.frontPhotoUrl = frontPhotoUrl;
    }

    public String getFrontPhotoSavepath() {
        return frontPhotoSavepath;
    }

    public void setFrontPhotoSavepath(String frontPhotoSavepath) {
        this.frontPhotoSavepath = frontPhotoSavepath;
    }

    public String getReversePhotoUrl() {
        return reversePhotoUrl;
    }

    public void setReversePhotoUrl(String reversePhotoUrl) {
        this.reversePhotoUrl = reversePhotoUrl;
    }

    public String getReversePhotoSavepath() {
        return reversePhotoSavepath;
    }

    public void setReversePhotoSavepath(String reversePhotoSavepath) {
        this.reversePhotoSavepath = reversePhotoSavepath;
    }

    @Override
    public String toString() {
        return "IdcardPhoto{" +
                "idcardId=" + idcardId +
                ", userInfoId='" + userInfoId + '\'' +
                ", frontPhotoUrl='" + frontPhotoUrl + '\'' +
                ", frontPhotoSavepath='" + frontPhotoSavepath + '\'' +
                ", reversePhotoUrl='" + reversePhotoUrl + '\'' +
                ", reversePhotoSavepath='" + reversePhotoSavepath + '\'' +
                '}';
    }
}
