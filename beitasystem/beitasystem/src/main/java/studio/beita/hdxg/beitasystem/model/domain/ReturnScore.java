package studio.beita.hdxg.beitasystem.model.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    private Integer scoreNum;

    public ReturnScore() {}

    public ReturnScore(String identifier, String name, Integer scoreNum) {
        this.identifier = identifier;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ReturnScore{" +
                "identifier='" + identifier + '\'' +
                ", name='" + name + '\'' +
                ", scoreNum=" + scoreNum +
                '}';
    }
}
