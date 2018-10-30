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
    // TODO: 2018/10/30 通过考试ID获取考试名称
    private String Identifier;
    // TODO: 2018/10/30 通过准考证添加学生姓名
    private String realName;
    private Integer scoreNum;

    // TODO: 2018/10/30 get set tosTRING 构造方法
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
