package studio.beita.hdxg.beitasystem.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zr
 * @program: beitasystem
 * @Title: ExamSignupList
 * @package: studio.beita.hdxg.beitasystem.model.domain
 * @description: 考试报名表实体类
 **/

public class ExamSignupList implements Serializable {

    private static final long serialVersionUID = -845427993101371563L;

    private Integer signUpId;
    private String examTypeId;
    private String detailsId;
    private String signUpPic;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date signUpTime;
    private boolean isConfirm;
    private Integer birthMonth;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String realName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String idCard;
    /**
     * 用户头像存储地址
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String photoPath;

    public ExamSignupList(){}

    public ExamSignupList(Integer signUpId, String examTypeId, String detailsId, String signUpPic, Date signUpTime, boolean isConfirm, Integer birthMonth) {
        this.signUpId = signUpId;
        this.examTypeId = examTypeId;
        this.detailsId = detailsId;
        this.signUpPic = signUpPic;
        this.signUpTime = signUpTime;
        this.isConfirm = isConfirm;
        this.birthMonth = birthMonth;
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

    public String getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(String detailsId) {
        this.detailsId = detailsId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
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

    public boolean isConfirm() {
        return isConfirm;
    }

    public void setConfirm(boolean confirm) {
        isConfirm = confirm;
    }

    public Integer getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(Integer birthMonth) {
        this.birthMonth = birthMonth;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    @Override
    public String toString() {
        return "ExamSignupList{" +
                "signUpId=" + signUpId +
                ", examTypeId='" + examTypeId + '\'' +
                ", detailsId='" + detailsId + '\'' +
                ", signUpPic='" + signUpPic + '\'' +
                ", signUpTime=" + signUpTime +
                ", isConfirm=" + isConfirm +
                ", birthMonth=" + birthMonth +
                '}';
    }
}
