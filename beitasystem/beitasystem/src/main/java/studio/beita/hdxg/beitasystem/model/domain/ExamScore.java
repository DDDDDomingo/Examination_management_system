package studio.beita.hdxg.beitasystem.model.domain;

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
    private String Identifier;
    private Integer scoreNum;

    public ExamScore(){}
    public ExamScore(Integer scoreId, String examId, String ticketInfoIdentifier, Integer scoreNum) {
        this.scoreId = scoreId;
        this.examId = examId;
        this.Identifier = ticketInfoIdentifier;
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

    public String getIdentifier() {
        return Identifier;
    }

    public void setIdentifier(String Identifier) {
        this.Identifier = Identifier;
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
                ", Identifier='" + Identifier + '\'' +
                ", scoreNum=" + scoreNum +
                '}';
    }
}
