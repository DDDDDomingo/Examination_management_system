package studio.beita.hdxg.beitasystem.model.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @author zr
 * @program: beitasystem
 * @Title: Operating
 * @package: studio.beita.hdxg.beitasystem.model.domain
 * @description: 操作表实体类
 **/
public class Operating implements Serializable {

    private static final long serialVersionUID = 6025723646169133601L;

    private Integer operatingId;
    private String name;
    private String coding;
    private String intercept;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer parent_id;

    public Operating(){}
    public Operating(Integer operatingId, String name, String coding, String intercept, Integer parent_id) {
        this.operatingId = operatingId;
        this.name = name;
        this.coding = coding;
        this.intercept = intercept;
        this.parent_id = parent_id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getOperatingId() {
        return operatingId;
    }

    public Operating setOperatingId(Integer operatingId) {
        this.operatingId = operatingId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Operating setName(String name) {
        this.name = name;
        return this;
    }

    public String getCoding() {
        return coding;
    }

    public Operating setCoding(String coding) {
        this.coding = coding;
        return this;
    }

    public String getIntercept() {
        return intercept;
    }

    public Operating setIntercept(String intercept) {
        this.intercept = intercept;
        return this;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public Operating setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
        return this;
    }

    @Override
    public String toString() {
        return "Operating{" +
                "operatingId=" + operatingId +
                ", name='" + name + '\'' +
                ", coding='" + coding + '\'' +
                ", intercept='" + intercept + '\'' +
                ", parent_id=" + parent_id +
                '}';
    }
}
