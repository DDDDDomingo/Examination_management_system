package studio.beita.hdxg.beitasystem.model.domain;

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
    private String userId;
    private boolean isCheck;
    private String startReview;
    private String endReview;
    private boolean enterType;

    public ReviewPersonnel() {}
    public ReviewPersonnel(Integer enterPId, String typeId, String userId, boolean isCheck, String startReview, String endReview, boolean enterType) {
        this.enterPId = enterPId;
        this.typeId = typeId;
        this.userId = userId;
        this.isCheck = isCheck;
        this.startReview = startReview;
        this.endReview = endReview;
        this.enterType = enterType;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
                ", userId='" + userId + '\'' +
                ", isCheck=" + isCheck +
                ", startReview='" + startReview + '\'' +
                ", endReview='" + endReview + '\'' +
                ", enterType=" + enterType +
                '}';
    }
}
