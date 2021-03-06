package studio.beita.hdxg.beitasystem.model.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author zr
 * @program: beitasystem
 * @Title: ExamNewsType
 * @package: studio.beita.hdxg.beitasystem.model.domain
 * @description: 考试新闻类别表实体类
 **/
public class ExamNewsType implements Serializable {

    private Integer etypeId;
    private String typeName;
    private List<ExamNews> examNewsList;

    public ExamNewsType() {
    }

    public ExamNewsType(Integer etypeId, String typeName, List<ExamNews> examNewsList) {
        this.etypeId = etypeId;
        this.typeName = typeName;
        this.examNewsList = examNewsList;
    }

    public Integer getEtypeId() {
        return etypeId;
    }

    public void setEtypeId(Integer etypeId) {
        this.etypeId = etypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<ExamNews> getExamNewsList() {
        return examNewsList;
    }

    public void setExamNewsList(List<ExamNews> examNewsList) {
        this.examNewsList = examNewsList;
    }

    @Override
    public String toString() {
        return "ExamNewsType{" +
                "etypeId=" + etypeId +
                ", typeName='" + typeName + '\'' +
                ", examNewsList=" + examNewsList +
                '}';
    }
}
