package studio.beita.hdxg.beitasystem.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

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
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String time;
    private boolean isNew;
    private Integer visits;

    public ExamNews() {
    }
    public ExamNews(Integer newsId, Integer etypeId, String content, String time, boolean isNew, Integer visits) {
        this.newsId = newsId;
        this.etypeId = etypeId;
        this.content = content;
        this.time = time;
        this.isNew = isNew;
        this.visits = visits;
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

    @Override
    public String toString() {
        return "ExamNews{" +
                "newsId=" + newsId +
                ", etypeId=" + etypeId +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", isNew=" + isNew +
                ", visits=" + visits +
                '}';
    }
}
