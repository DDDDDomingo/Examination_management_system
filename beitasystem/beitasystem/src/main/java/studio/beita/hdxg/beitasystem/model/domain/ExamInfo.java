package studio.beita.hdxg.beitasystem.model.domain;

import java.io.Serializable;

/**
 * @author ydq
 * @program: beitasystem
 * @Title: ExamInfo
 * @package: studio.beita.hdxg.beitasystem.model.domain
 * @description: 考试类别信息实体类
 **/

public class ExamInfo implements Serializable {

    private static final long serialVersionUID = -1293128255629636887L;

    /**
     * 考试类别ID
     */
    private String examId;
    /**
     * 考试名称
     */
    private String examName;
    /**
     * 考试是否已经结束，默认0
     */
    private boolean isClosed;
    /**
     * 考试是否正在报名，默认0
     */
    private boolean isSignUp;
    /**
     * 考试成绩是否可以查询，默认0
     */
    private boolean isQuery;
    /**
     * 考试已审核人数
     */
    private Integer auditedNum;
    /**
     * 考试可容纳人数
     */
    private Integer capacity;

    public ExamInfo() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public boolean isSignUp() {
        return isSignUp;
    }

    public void setSignUp(boolean signUp) {
        isSignUp = signUp;
    }

    public boolean isQuery() {
        return isQuery;
    }

    public void setQuery(boolean query) {
        isQuery = query;
    }

    public Integer getAuditedNum() {
        return auditedNum;
    }

    public void setAuditedNum(Integer auditedNum) {
        this.auditedNum = auditedNum;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "ExamInfo{" +
                "examId='" + examId + '\'' +
                ", examName='" + examName + '\'' +
                ", isClosed=" + isClosed +
                ", isSignUp=" + isSignUp +
                ", isQuery=" + isQuery +
                ", auditedNum=" + auditedNum +
                ", capacity=" + capacity +
                '}';
    }
}
