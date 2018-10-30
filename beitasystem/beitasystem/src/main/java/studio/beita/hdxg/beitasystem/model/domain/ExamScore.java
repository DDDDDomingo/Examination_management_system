package studio.beita.hdxg.beitasystem.model.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @author zr
 * @program: beitasystem
 * @Title: ExamScore
 * @package: studio.beita.hdxg.beitasystem.model.domain
 * @description: 考试成绩表实体类
 **/
public class ExamScore implements Serializable {

    private static final long serialVersionUID = -3619763432120237824L;

    private Integer scoreId;
    private String examId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String examName;
    private String identifier;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String realName;
    private Integer scoreNum;

    public ExamScore(){}

    public ExamScore(Integer scoreId, String examId, String identifier, Integer scoreNum) {
        this.scoreId = scoreId;
        this.examId = examId;
        this.identifier = identifier;
        this.scoreNum = scoreNum;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getScoreId() {
        return scoreId;
    }

    public void setScoreId(Integer scoreId) {
        this.scoreId = scoreId;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getScoreNum() {
        return scoreNum;
    }

    public void setScoreNum(Integer scoreNum) {
        this.scoreNum = scoreNum;
    }

    @Override
    public String toString() {
        return "ExamScore{" +
                "scoreId=" + scoreId +
                ", examId='" + examId + '\'' +
                ", examName='" + examName + '\'' +
                ", identifier='" + identifier + '\'' +
                ", realName='" + realName + '\'' +
                ", scoreNum=" + scoreNum +
                '}';
    }
}
