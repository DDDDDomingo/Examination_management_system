package studio.beita.hdxg.beitasystem.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.beita.hdxg.beitasystem.model.domain.*;
import studio.beita.hdxg.beitasystem.repository.ExamManagementDao;
import studio.beita.hdxg.beitasystem.repository.ExamSignUpDao;
import studio.beita.hdxg.beitasystem.service.ExamSignUpService;
import studio.beita.hdxg.beitasystem.utils.GetUidUtils;
import studio.beita.hdxg.beitasystem.utils.WordUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.util.*;

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
    public boolean generateAdmissionTicketByAdmin(String examId, String schoolName, String saveDir) throws FileNotFoundException {
        //获取考试信息
        ExamInfo examInfo = examManagementDao.getExamDetails(examId);
        //获取报名信息考生列表
        List<ExamSignupList> examSignupListList = examSignUpDao.getExamSignupListByExamId(examId);
        //准考证信息表实体类list
        AdmissionTicketInfo admissionTicketInfoList = new AdmissionTicketInfo();
        //用来存放word中需要替换的
        HashMap<String, Object> admissMap = new HashMap<>();
        admissMap.put("${examName}",examInfo.getExamName());
        admissMap.put("${school}",schoolName);
        admissMap.put("${time}",examInfo.getStartTime());
        //计算考试时长
        int duration = (int)getDatePoor(examInfo.getEndTime(), examInfo.getStartTime());
        admissMap.put("${duration}",duration);
        if(null == examSignupListList || examSignupListList.size() ==0 ){
            return false;
        }else{
            for (ExamSignupList esl:examSignupListList) {

                //生成准考证号 准考证id(考试类别ID前4位+考生ID后8位+随机6位)
                StringBuffer identifier = new StringBuffer();
                identifier.append(examId,0,4);
                identifier.append(esl.getDetailsId(),4,12);
                identifier.append(GetUidUtils.getTicket());
                //插入和创建 之前先判断是否已经存在了，已存在直接退出当前循环，执行下一循环
                String flag = examSignUpDao.assertTicket(identifier.toString());
                if(!StringUtils.isEmpty(flag)){
                    continue;
                }
                //加入准考证号
                admissMap.put("${identifier}",identifier.toString());
                //加入真实姓名
                admissMap.put("${name}",esl.getRealName());
                //加入证件号码
                admissMap.put("${idCard}",esl.getRealName());
                //加入准考证头像
                admissMap.put("${photo}", WordUtils.inputStream2ByteArray(new FileInputStream(esl.getPhotoPath()),true));
                //计算出考场座位号
                return true;



            }

        }


        return false;
    }

    /**
     * 生成word
     * @param admissMap
     * @return
     */
    public static boolean generateAdmissionDoc(HashMap<String, Object> admissMap){
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
