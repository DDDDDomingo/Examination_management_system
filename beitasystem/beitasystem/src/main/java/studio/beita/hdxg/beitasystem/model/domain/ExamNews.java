package studio.beita.hdxg.beitasystem.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.List;

/**
 * @author zr
 * @program: beitasystem
 * @Title: ExamNews
 * @package: studio.beita.hdxg.beitasystem.model.domain
 * @description: 考试新闻表实体类
 **/
public class ExamNews implements Serializable {

    private Integer newsId;
    private Integer etypeId;
    private String title;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String time;
    private boolean isNew;
    private Integer visits;
    private List<Resource> resourceList;

    public ExamNews() {
    }

    public ExamNews(Integer newsId, Integer etypeId, String title, String content, String time, boolean isNew, Integer visits, List<Resource> resourceList) {
        this.newsId = newsId;
        this.etypeId = etypeId;
        this.title = title;
        this.content = content;
        this.time = time;
        this.isNew = isNew;
        this.visits = visits;
        this.resourceList = resourceList;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public Integer getEtypeId() {
        return etypeId;
    }

    public void setEtypeId(Integer etypeId) {
        this.etypeId = etypeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public Integer getVisits() {
        return visits;
    }

    public void setVisits(Integer visits) {
        this.visits = visits;
    }

    public List<Resource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<Resource> resourceList) {
        this.resourceList = resourceList;
    }

    @Override
    public String toString() {
        return "ExamNews{" +
                "newsId=" + newsId +
                ", etypeId=" + etypeId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", isNew=" + isNew +
                ", visits=" + visits +
                ", resourceList=" + resourceList +
                '}';
    }
}
