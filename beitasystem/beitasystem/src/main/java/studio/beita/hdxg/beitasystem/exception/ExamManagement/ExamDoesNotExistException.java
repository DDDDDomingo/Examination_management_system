package studio.beita.hdxg.beitasystem.exception.ExamManagement;

import studio.beita.hdxg.beitasystem.constant.ResponseConstant;

/**
 * @author ydq
 * @program: beitasystem
 * @Title: ExamDoesNotExistException
 * @package: studio.beita.hdxg.beitasystem.exception.ExamManagement
 * @description: 考试不存在异常
 **/

public class ExamDoesNotExistException extends RuntimeException {
    private static final long serialVersionUID = -2921336531331859848L;

    public ExamDoesNotExistException() {
    }

    @Override
    public String getMessage() {
        return ResponseConstant.EXAM_DOES_NOT_EXIST;
    }
}
