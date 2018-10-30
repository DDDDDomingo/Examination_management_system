package studio.beita.hdxg.beitasystem.model.domain;

import java.io.Serializable;

/**
 * @author zr
 * @program: beitasystem
 * @Title: ReturnScore
 * @package: studio.beita.hdxg.beitasystem.model.domain
 * @description: 前台返回成绩实体类
 **/
public class ReturnScore implements Serializable {

    private static final long serialVersionUID = -7740819613087289710L;

    private String identifier;
    private Integer scoreNum;

    public ReturnScore() {}
    public ReturnScore(String identifier, Integer scoreNum) {
        this.identifier = identifier;
        this.scoreNum = scoreNum;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Integer getScoreNum() {
        return scoreNum;
    }

    public void setScoreNum(Integer scoreNum) {
        this.scoreNum = scoreNum;
    }

    @Override
    public String toString() {
        return "ReturnScore{" +
                "identifier='" + identifier + '\'' +
                ", scoreNum=" + scoreNum +
                '}';
    }
}
