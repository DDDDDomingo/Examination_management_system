package studio.beita.hdxg.beitasystem.exception.ExamManagement;

import org.apache.commons.lang3.StringUtils;
import studio.beita.hdxg.beitasystem.constant.ResponseConstant;

/**
 * @author ydq
 * @program: beitasystem
 * @Title: ExamIsClosedException
 * @package: studio.beita.hdxg.beitasystem.exception.ExamManagement
 * @description: 考试已经关闭，无法修改，异常
 **/

public class ExamIsClosedException extends RuntimeException{
    private static final long serialVersionUID = 2279787263306378259L;

    public ExamIsClosedException() {
    }

    @Override
    public String getMessage() {
        return ResponseConstant.EXAM_SIGNUP_CANNOT_CHANGE;
    }
}
