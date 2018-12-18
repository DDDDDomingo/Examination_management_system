package studio.beita.hdxg.beitasystem.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ydq
 * @program: beitasystem
 * @Title: ExamSession
 * @package: studio.beita.hdxg.beitasystem.model.domain
 * @description: 考试场地表实体类
 **/

public class ExamSession implements Serializable{
    private static final long serialVersionUID = -7120869049562583734L;

    private Integer sessionId;
    private String examTypeId;
    private String sessionPlace;
    private Integer sessionCapacity;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date sessionTime;

    public ExamSession() {
    }

    public String getExamTypeId() {
        return examTypeId;
    }

    public void setExamTypeId(String examTypeId) {
        this.examTypeId = examTypeId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionPlace() {
        return sessionPlace;
    }

    public void setSessionPlace(String sessionPlace) {
        this.sessionPlace = sessionPlace;
    }

    public Integer getSessionCapacity() {
        return sessionCapacity;
    }

    public void setSessionCapacity(Integer sessionCapacity) {
        this.sessionCapacity = sessionCapacity;
    }

    public Date getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(Date sessionTime) {
        this.sessionTime = sessionTime;
    }

    @Override
    public String toString() {
        return "ExamSession{" +
                "sessionId=" + sessionId +
                ", sessionPlace='" + sessionPlace + '\'' +
                ", sessionCapacity=" + sessionCapacity +
                ", sessionTime=" + sessionTime +
                '}';
    }
}
