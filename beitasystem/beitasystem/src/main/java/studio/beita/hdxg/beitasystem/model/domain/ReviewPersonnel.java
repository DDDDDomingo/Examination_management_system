package studio.beita.hdxg.beitasystem.model.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @author zr
 * @program: beitasystem
 * @Title: ReviewPersonnel
 * @package: studio.beita.hdxg.beitasystem.model.domain
 * @description: 考试管理人员表实体类
 **/
public class ReviewPersonnel implements Serializable {

    private static final long serialVersionUID = -2870587651074509431L;

    private Integer enterPId;
    private String typeId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String examName;
    private String userId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String account;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private boolean isCheck;
    private String startReview;
    private String endReview;
    private boolean enterType;

    public ReviewPersonnel() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getEnterPId() {
        return enterPId;
    }

    public void setEnterPId(Integer enterPId) {
        this.enterPId = enterPId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getStartReview() {
        return startReview;
    }

    public void setStartReview(String startReview) {
        this.startReview = startReview;
    }

    public String getEndReview() {
        return endReview;
    }

    public void setEndReview(String endReview) {
        this.endReview = endReview;
    }

    public boolean isEnterType() {
        return enterType;
    }

    public void setEnterType(boolean enterType) {
        this.enterType = enterType;
    }

    @Override
    public String toString() {
        return "ReviewPersonnel{" +
                "enterPId=" + enterPId +
                ", typeId='" + typeId + '\'' +
                ", examName='" + examName + '\'' +
                ", userId='" + userId + '\'' +
                ", account='" + account + '\'' +
                ", isCheck=" + isCheck +
                ", startReview='" + startReview + '\'' +
                ", endReview='" + endReview + '\'' +
                ", enterType=" + enterType +
                '}';
    }
}
