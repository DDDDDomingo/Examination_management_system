package studio.beita.hdxg.beitasystem.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studio.beita.hdxg.beitasystem.model.domain.ExamInfo;
import studio.beita.hdxg.beitasystem.repository.ExamManagementDao;
import studio.beita.hdxg.beitasystem.service.ExamManagementService;
import studio.beita.hdxg.beitasystem.utils.GetUidUtils;

import java.util.List;
import java.util.Optional;

/**
 * @author ydq
 * @program: beitasystem
 * @Title: ExamManagementServiceImpl
 * @package: studio.beita.hdxg.beitasystem.service.impl
 * @description:
 **/
@Service
public class ExamManagementServiceImpl implements ExamManagementService {

    @Autowired
    private ExamManagementDao examManagementDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addExam(String examName, Integer examCapacity, String startTime, String endTime) {
        return examManagementDao.addExam(GetUidUtils.getNewExamId(), examName, examCapacity, startTime, endTime) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean changeExamClosed(String examId) {
        return examManagementDao.changeExamClosed(examId) > 0;
    }

    @Override
    public Optional<String> assertExamIsClosed(String examId) {
        return Optional.ofNullable(examManagementDao.assertExamIsClosed(examId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean changeExamSignUp(String examId, boolean isSignUp) {
        return examManagementDao.changeExamSignUp(examId, isSignUp) > 0;
    }

    @Override
    public Optional<String> assertExamIsClosedSignUp(String examId) {
        return Optional.ofNullable(examManagementDao.assertExamIsClosedSignUp(examId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean changeExamQuery(String examId, boolean isQuery) {
        return examManagementDao.changeExamQuery(examId, isQuery) > 0;
    }

    // TODO: 2018/10/28 条件筛选
    @Override
    public List<ExamInfo> getExamList(Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        return examManagementDao.getExamListByAdmin();
    }

    @Override
    public Optional<ExamInfo> getExamDetails(String examId) {
        return Optional.ofNullable(examManagementDao.getExamDetails(examId));
    }

    @Override
    public List<ExamInfo> getSignUpExamList(Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        return examManagementDao.getSignUpExamList();
    }

    @Override
    public boolean addExamSession(String sessionPlace, Integer sessionCapacity, String sessionTime) {
        return examManagementDao.addExamSession(sessionPlace, sessionCapacity, sessionTime) > 0;
    }

    @Override
    public boolean deleteExamSession(String examId) {
        return examManagementDao.deleteExamSession(examId) > 0;
    }

    @Override
    public Optional<ExamInfo> adminGetExamDetails(String examId) {
        return Optional.ofNullable(examManagementDao.adminGetExamDetails(examId));
    }

}
