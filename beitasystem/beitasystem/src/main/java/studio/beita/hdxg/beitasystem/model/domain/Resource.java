package studio.beita.hdxg.beitasystem.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * @author zr
 * @program: beitasystem
 * @Title: Resource
 * @package: studio.beita.hdxg.beitasystem.model.domain
 * @description:  新闻资源表实体类
 **/
public class Resource implements Serializable {
    private Integer resourceId;
    private Integer newsId;
    private String address;
    private String savePath;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String createTime;

    public Resource() {
    }

    public Resource(Integer resourceId, Integer newsId, String address, String savePath, String createTime) {
        this.resourceId = resourceId;
        this.newsId = newsId;
        this.address = address;
        this.savePath = savePath;
        this.createTime = createTime;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "resourceId=" + resourceId +
                ", newsId=" + newsId +
                ", address='" + address + '\'' +
                ", savePath='" + savePath + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
