package studio.beita.hdxg.beitasystem.model.domain;

import java.io.Serializable;

/**
 * @author zr
 * @program: beitasystem
 * @Title: SystemNotice
 * @package: studio.beita.hdxg.beitasystem.model.domain
 * @description: 系统消息实体类
 */
public class SystemNotice implements Serializable {

    private static final long serialVersionUID = 2230196895574832768L;

    private Integer noticeId;
    private Integer senderId;
    private Integer receiveId;
    private String content;
    private String createTime;
    private boolean isRead;

    public SystemNotice(){}

    public SystemNotice(Integer noticeId,Integer senderId,Integer receiveId,String content,String createTime,boolean isRead){
        this.noticeId = noticeId;
        this.senderId = senderId;
        this.receiveId = receiveId;
        this.content = content;
        this.createTime = createTime;
        this.isRead = isRead;
    }

    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(Integer receiveId) {
        this.receiveId = receiveId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getcreateTime() {
        return createTime;
    }

    public void setcreateTime(String createTime) {
        this.createTime = createTime;
    }

    public boolean isIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    @Override
    public String toString() {
        return "SystemNotice{" +
                "noticeId=" + noticeId +
                ", senderId='" + senderId + '\'' +
                ", receiveId='" + receiveId + '\'' +
                ", content='" + content + '\'' +
                ", createTime='" + createTime + '\'' +
                ", isRead='" + isRead + '\'' +
                '}';
    }
}
