package studio.beita.hdxg.beitasystem.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * @author zr
 * @program: beitasystem
 * @Title: AdmissionTicketInfo
 * @package: studio.beita.hdxg.beitasystem.model.domain
 * @description: 考生准考证信息表实体类
 **/
public class AdmissionTicketInfo implements Serializable {

    private static final long serialVersionUID = 9164570156351362488L;

    private String ticketId;
    private String userId;
    private String name;
    private String identifier;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String time;
    private Integer duration;
    private Integer seatNum;
    private String school;

    public AdmissionTicketInfo(){}
    public AdmissionTicketInfo(String ticketId, String userId, String name, String identifier, String time, Integer duration, Integer seatNum, String school) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.name = name;
        this.identifier = identifier;
        this.time = time;
        this.duration = duration;
        this.seatNum = seatNum;
        this.school = school;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(Integer seatNum) {
        this.seatNum = seatNum;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "AdmissionTicketInfo{" +
                "ticketId='" + ticketId + '\'' +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", identifier='" + identifier + '\'' +
                ", time='" + time + '\'' +
                ", duration=" + duration +
                ", seatNum=" + seatNum +
                ", school='" + school + '\'' +
                '}';
    }
}
