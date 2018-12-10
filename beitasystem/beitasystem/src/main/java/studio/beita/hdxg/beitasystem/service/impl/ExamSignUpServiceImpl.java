package studio.beita.hdxg.beitasystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.beita.hdxg.beitasystem.model.domain.*;
import studio.beita.hdxg.beitasystem.repository.ExamManagementDao;
import studio.beita.hdxg.beitasystem.repository.ExamSignUpDao;
import studio.beita.hdxg.beitasystem.service.ExamSignUpService;
import studio.beita.hdxg.beitasystem.utils.GetUidUtils;

import java.util.ArrayList;
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
    @Autowired
    private ExamManagementDao examManagementDao;

    @Override
    public Optional<String> userAuthentication(String userId) {
        return Optional.ofNullable(examSignUpDao.userAuthentication(userId));
    }

    @Override
    public Optional<List<ExamInfo>> getExamInfoList() {
        return Optional.ofNullable(examSignUpDao.getExamInfoList());
    }

    @Override
    public boolean insertExamSignupListByUser(String examTypeId, String detailsId, String signUpPic, Date signUpTime, boolean isConfirm, Integer birthMonth) {
        return examSignUpDao.insertExamSignupListByUser(examTypeId, detailsId, signUpPic, signUpTime, isConfirm, birthMonth) > 0;
    }

    @Override
    public Optional<String> verifyAdministrator(String typeId, String userId) {
        return Optional.ofNullable(examSignUpDao.verifyAdministrator(typeId, userId));
    }

    @Override
    public Optional<List<ReviewCandidate>> reviewCandidateInformation(String typeId, int[] month) {
        return Optional.ofNullable(examSignUpDao.reviewCandidateInformation(typeId, month));
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
    public boolean deleteCandidateByUserId(String userId, String typeId) {
        return examSignUpDao.deleteCandidateByUserId(userId, typeId) > 0;
    }

    @Override
    public boolean changeExamSignupList(String userId, String typeId) {
        return examSignUpDao.changeExamSignupList(userId, typeId) > 0;
    }

    @Override
    public boolean changeCandidateNum(String typeId) {
        return examSignUpDao.changeCandidateNum(typeId) > 0;
    }

    @Override
    public Integer getPassNumByAdmin(String typeId) {
        return examSignUpDao.getPassNumByAdmin(typeId);
    }

    @Override
    public boolean generateAdmissionTicketByadmin(String examId, String schoolName, String saveDir) {
        //获取考试信息
        ExamInfo examInfo = examManagementDao.getExamDetails(examId);
        //获取报名信息考生列表
        List<ExamSignupList> examSignupListList = examSignUpDao.getExamSignupListByExamId(examId);
        //准考证信息表实体类list
        AdmissionTicketInfo admissionTicketInfoList = new AdmissionTicketInfo();
        if(null == examSignupListList || examSignupListList.size() ==0 ){
            return false;
        }else{
            for (ExamSignupList esl:examSignupListList) {
                //生成准考证号 准考证id(考试类别ID前4位+考生ID后8位+随机6位)
                StringBuffer identifier = new StringBuffer();
                identifier.append(examId,0,4);
                identifier.append(esl.getDetailsId(),4,12);
                identifier.append(GetUidUtils.getTicket());
                //计算考试时长
                int duration = (int)getDatePoor(examInfo.getEndTime(), examInfo.getStartTime());
                //插入和创建 之前先判断是否已经存在了

            }


        }


        return false;
    }

    public static long getDatePoor(Date endDate, Date nowDate) {
        long nm = 1000 * 60;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少分钟
        long min = diff / nm;
        return min;
    }
}
