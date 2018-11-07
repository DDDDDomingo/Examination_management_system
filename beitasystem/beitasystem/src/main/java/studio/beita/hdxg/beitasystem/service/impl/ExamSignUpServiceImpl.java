package studio.beita.hdxg.beitasystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.beita.hdxg.beitasystem.model.domain.ExamInfo;
import studio.beita.hdxg.beitasystem.model.domain.ExamSignupList;
import studio.beita.hdxg.beitasystem.model.domain.ReviewCandidate;
import studio.beita.hdxg.beitasystem.model.domain.ReviewPersonnel;
import studio.beita.hdxg.beitasystem.repository.ExamSignUpDao;
import studio.beita.hdxg.beitasystem.service.ExamSignUpService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author zr
 * @program: beitasystem
 * @Title: ExamSignUpServiceImpl
 * @package: studio.beita.hdxg.beitasystem.service.impl
 * @description:
 **/
@Service
public class ExamSignUpServiceImpl implements ExamSignUpService {
    @Autowired
    private ExamSignUpDao examSignUpDao;

    @Override
    public Optional<String> userAuthentication(String userId) {
        return  Optional.ofNullable(examSignUpDao.userAuthentication(userId));
    }

    @Override
    public Optional<List<ExamInfo>> getExamInfoList() {
        return Optional.ofNullable(examSignUpDao.getExamInfoList());
    }

    @Override
    public boolean insertExamSignupListByUser(String examTypeId, String detailsId, String signUpPic, Date signUpTime, boolean isConfirm, Integer birthMonth) {
        return examSignUpDao.insertExamSignupListByUser(examTypeId, detailsId, signUpPic, signUpTime, isConfirm, birthMonth)>0;
    }

    @Override
    public Optional<String> verifyAdministrator(String typeId, String userId) {
        return  Optional.ofNullable(examSignUpDao.verifyAdministrator(typeId,userId));
    }

    @Override
    public Optional<List<ReviewCandidate>> reviewCandidateInformation(String typeId, int[] month) {
        return Optional.ofNullable(examSignUpDao.reviewCandidateInformation(typeId,month));
    }

    @Override
    public Optional<String> getUserNameByUserId(String userId) {
        return Optional.ofNullable(examSignUpDao.getUserNameByUserId(userId));
    }

    @Override
    public Optional<String> getFrontPhotoUrlByUserId(String userId) {
        return Optional.ofNullable(examSignUpDao.getFrontPhotoUrlByUserId(userId));
    }

    @Override
    public Optional<String> getReversePhotoUrlByUserId(String userId) {
        return Optional.ofNullable(examSignUpDao.getReversePhotoUrlByUserId(userId));
    }

    @Override
    public Optional<List<ReviewPersonnel>> getExamAdminNumberByExamTypeId(String typeId) {
        return Optional.ofNullable(examSignUpDao.getExamAdminNumberByExamTypeId(typeId));
    }

    @Override
    public boolean deleteCandidateByUserId(String userId,String typeId) {
        return examSignUpDao.deleteCandidateByUserId(userId,typeId)>0;
    }

    @Override
    public boolean changeExamSignupList(String userId,String typeId) {
        return examSignUpDao.changeExamSignupList(userId,typeId)>0;
    }

    @Override
    public boolean changeCandidateNum(String typeId) {
        return examSignUpDao.changeCandidateNum(typeId)>0;
    }

    @Override
    public Integer getPassNumByAdmin(String typeId) {
        return examSignUpDao.getPassNumByAdmin(typeId);
    }
}
