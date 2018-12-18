package studio.beita.hdxg.beitasystem.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studio.beita.hdxg.beitasystem.model.domain.*;
import studio.beita.hdxg.beitasystem.repository.ExamManagementDao;
import studio.beita.hdxg.beitasystem.repository.ExamSignUpDao;
import studio.beita.hdxg.beitasystem.service.ExamSignUpService;
import studio.beita.hdxg.beitasystem.utils.DateUtils;
import studio.beita.hdxg.beitasystem.utils.GetUidUtils;
import studio.beita.hdxg.beitasystem.utils.WordUtils;

import java.io.*;
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
    @Transactional(rollbackFor = Exception.class)
    public String generateAdmissionTicketByAdmin(String examId, String schoolName, String saveDir, int duration) throws IOException {
        //获取考试信息
        ExamInfo examInfo = examManagementDao.getExamDetails(examId);
        //用来存放word中需要替换的
        HashMap<String, Object> admissMap = new HashMap<>();
        admissMap.put("examName", examInfo.getExamName());
        admissMap.put("school", schoolName);
        admissMap.put("time", DateUtils.formatDate(examInfo.getStartTime(),"yyyy-MM-dd HH:mm:ss"));
        //计算考试时长
        //int duration = (int) getDatePoor(examInfo.getEndTime(), examInfo.getStartTime());
        admissMap.put("shichang", String.valueOf(duration));
        //获取考试场次信息
        List<ExamSession> examSessionList = examSignUpDao.getExamSessionByTypeId(examId);
        //用于计数
        int currentNum = 0;
        if (null == examSessionList || examSessionList.size() == 0) {
            return "该考试目前没有场次！请先添加考场";
        } else {
            for (int i = 0; i < examSessionList.size(); i++) {
                int sessionCapacity = examSessionList.get(i).getSessionCapacity();
                String sessionPlace = examSessionList.get(i).getSessionPlace();
                //根据考场容量获取报名信息
                List<ExamSignupList> examSignupListList = examSignUpDao.getExamSignupListByExamId(examId, currentNum, sessionCapacity);
                //如果报名信息列表为空直接返回
                /*if (null == examSignupListList || examSignupListList.size() == 0) {
                    return "";
                }*/
                for (int j = 0; j < examSignupListList.size(); j++) {
                //for (ExamSignupList esl : examSignupListList) {
                    //生成准考证号 准考证id(考试类别ID前4位+考生ID后8位+随机6位)
                    StringBuffer identifier = new StringBuffer();
                    identifier.append(examId, 0, 4);
                    identifier.append(examSignupListList.get(j).getDetailsId(), 4, 12);
                    identifier.append(GetUidUtils.getTicket());
                    //插入和创建 之前先判断是否已经存在了，已存在直接退出当前循环，执行下一循环
                    String flag = examSignUpDao.assertTicket(examSignupListList.get(j).getDetailsId(), sessionPlace);
                    if (!StringUtils.isEmpty(flag)) {
                        continue;
                    }
                    //加入准考证号
                    admissMap.put("dentifier", identifier.toString());
                    //加入真实姓名
                    admissMap.put("same", examSignupListList.get(j).getRealName());
                    //加入证件号码
                    admissMap.put("dCard", examSignupListList.get(j).getIdCard());
                    //加入考场
                    admissMap.put("sessionPlace", sessionPlace);
                    //加入准考证头像
                    Map<String,Object> photo = new HashMap<>();
                    photo.put("width",100);
                    photo.put("height",150);
                    // TODO: 2018/12/18 自动获取图片格式
                    photo.put("type","jpg");
                    photo.put("content",WordUtils.inputStream2ByteArray(new FileInputStream(examSignupListList.get(j).getPhotoPath()), true));
                    admissMap.put("hoto", photo);
                    //计算出考场座位号
                    admissMap.put("zuoweihao",String.valueOf(j+1));
                    //生成word
                    CustomXWPFDocument doc = WordUtils.generateWord(admissMap, saveDir+"\\moban.docx");
                    //判断目录是否存在，不存在就新建
                    File savePath = new File(saveDir+"\\"+examId);
                    if (!savePath.exists()) {
                        savePath.mkdir();
                    }
                    //输出到指定目录
                    FileOutputStream fopts = new FileOutputStream(saveDir+"\\"+examId+"\\"+identifier.toString()+".docx");
                    doc.write(fopts);
                    fopts.close();
                    //插入数据库
                    examSignUpDao.insertAdmissionTicketInfo(examSignupListList.get(j).getDetailsId(), examSignupListList.get(j).getRealName(),
                            identifier.toString(), examInfo.getStartTime(), duration, sessionPlace, j+1, schoolName );
                    //删除key
                }
                //添加用于下一次循环
                currentNum = currentNum + sessionCapacity;
            }
            return "准考证生成完成！";
        }
    }

    /**
     * 生成word
     *
     * @param admissMap
     * @return
     */
    public static boolean generateAdmissionDoc(HashMap<String, Object> admissMap) {
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
