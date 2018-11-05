package studio.beita.hdxg.beitasystem.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zr
 * @program: beitasystem
 * @Title: ReviewCandidate
 * @package: studio.beita.hdxg.beitasystem.model.domain
 * @description: 审核考生实体类
 */
public class ReviewCandidate implements Serializable {

    private static final long serialVersionUID = -6602504091144328829L;

    private Integer signUpId;
    private String examTypeId;
    private String name;
    private String signUpPic;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date signUpTime;
    private String frontPhotoUrl;
    private String reversePhotoUrl;
    private boolean isConfirm;

    public ReviewCandidate() {}

    public ReviewCandidate(Integer signUpId, String examTypeId, String name, String signUpPic, Date signUpTime, String frontPhotoUrl, String reversePhotoUrl, boolean isConfirm) {
        this.signUpId = signUpId;
        this.examTypeId = examTypeId;
        this.name = name;
        this.signUpPic = signUpPic;
        this.signUpTime = signUpTime;
        this.frontPhotoUrl = frontPhotoUrl;
        this.reversePhotoUrl = reversePhotoUrl;
        this.isConfirm = isConfirm;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getSignUpId() {
        return signUpId;
    }

    public void setSignUpId(Integer signUpId) {
        this.signUpId = signUpId;
    }

    public String getExamTypeId() {
        return examTypeId;
    }

    public void setExamTypeId(String examTypeId) {
        this.examTypeId = examTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSignUpPic() {
        return signUpPic;
    }

    public void setSignUpPic(String signUpPic) {
        this.signUpPic = signUpPic;
    }

    public Date getSignUpTime() {
        return signUpTime;
    }

    public void setSignUpTime(Date signUpTime) {
        this.signUpTime = signUpTime;
    }

    public String getFrontPhotoUrl() {
        return frontPhotoUrl;
    }

    public void setFrontPhotoUrl(String frontPhotoUrl) {
        this.frontPhotoUrl = frontPhotoUrl;
    }

    public String getReversePhotoUrl() {
        return reversePhotoUrl;
    }

    public void setReversePhotoUrl(String reversePhotoUrl) {
        this.reversePhotoUrl = reversePhotoUrl;
    }

    public boolean isConfirm() {
        return isConfirm;
    }

    public void setConfirm(boolean confirm) {
        isConfirm = confirm;
    }

    @Override
    public String toString() {
        return "ReviewCandidate{" +
                "signUpId=" + signUpId +
                ", examTypeId='" + examTypeId + '\'' +
                ", name='" + name + '\'' +
                ", signUpPic='" + signUpPic + '\'' +
                ", signUpTime=" + signUpTime +
                ", frontPhotoUrl='" + frontPhotoUrl + '\'' +
                ", reversePhotoUrl='" + reversePhotoUrl + '\'' +
                ", isConfirm=" + isConfirm +
                '}';
    }
}
