package studio.beita.hdxg.beitasystem.exception.ExamManagement;

import studio.beita.hdxg.beitasystem.constant.ResponseConstant;

/**
 * @author ydq
 * @program: beitasystem
 * @Title: ExamQueryCannotChangeException
 * @package: studio.beita.hdxg.beitasystem.exception.ExamManagement
 * @description: 考试成绩查询无法修改异常
 **/

public class ExamQueryCannotChangeException extends RuntimeException {
    private static final long serialVersionUID = -130717742931325216L;

    public ExamQueryCannotChangeException() {
    }

    @Override
    public String getMessage() {
        return ResponseConstant.EXAM_QUERY_CANNOT_CHANGE;
    }
}
