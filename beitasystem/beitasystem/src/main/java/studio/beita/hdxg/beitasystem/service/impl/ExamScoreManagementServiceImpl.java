package studio.beita.hdxg.beitasystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.beita.hdxg.beitasystem.model.domain.ExamScore;
import studio.beita.hdxg.beitasystem.model.domain.ReturnScore;
import studio.beita.hdxg.beitasystem.model.domain.ReviewPersonnel;
import studio.beita.hdxg.beitasystem.repository.ExamScoreManagementDao;
import studio.beita.hdxg.beitasystem.service.ExamScoreManagementService;

import java.util.List;
import java.util.Optional;

/**
 * @author zr
 * @program: beitasystem
 * @Title: ExamScoreManagementServiceImpl
 * @package: studio.beita.hdxg.beitasystem.service.impl
 * @description:
 **/
@Service
public class ExamScoreManagementServiceImpl implements ExamScoreManagementService {

    @Autowired
    ExamScoreManagementDao examScoreManagementDao;

    @Override
    public Optional<ExamScore> getExamScoreByIdentifier(String identifier) {
        return Optional.ofNullable(examScoreManagementDao.getExamScoreByIdentifier(identifier));
    }

    @Override
    public Optional<String> checkUserInfo(String identifier, String name) {
        return Optional.ofNullable(examScoreManagementDao.checkUserInfo(identifier, name));
    }

    @Override
    public boolean changeExamScoreByReturnScore(List<ReturnScore> returnScore) {
        return examScoreManagementDao.changeExamScoreByReturnScore(returnScore)>0;
    }

    @Override
    public Optional<List<ExamScore>> getExamScoreList() {
        return Optional.ofNullable(examScoreManagementDao.getExamScoreList());
    }

    @Override
    public Optional<ReviewPersonnel> verifyTimeByUserId(Integer userId) {
        return Optional.ofNullable(examScoreManagementDao.verifyTimeByUserId(userId));
    }

    @Override
    public boolean changeIsCheck(Integer userId) {
        return examScoreManagementDao.changeIsCheck(userId)>0;
    }
}
