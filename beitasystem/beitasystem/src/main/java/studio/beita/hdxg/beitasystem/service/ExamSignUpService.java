package studio.beita.hdxg.beitasystem.service;

import org.apache.ibatis.annotations.Select;
import studio.beita.hdxg.beitasystem.model.domain.ExamInfo;
import studio.beita.hdxg.beitasystem.model.domain.ExamSignupList;
import studio.beita.hdxg.beitasystem.model.domain.ReviewCandidate;
import studio.beita.hdxg.beitasystem.model.domain.ReviewPersonnel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author zr
 * @program: beitasystem
 * @Title: WebPortalsService
 * @package: studio.beita.hdxg.beitasystem.service
 * @description: 考试报名模块表Service层接口
 **/
public interface ExamSignUpService {

    /**
     * 身份验证（是否有反面身份证照片）
     *
     * @param userId
     * @return
     */
    Optional<String> userAuthentication(String userId);

    /**
     * 取出可以报名的考试类别表清单
     *
     * @return
     */
    Optional<List<ExamInfo>> getExamInfoList();

    /**
     * 用户添加用户报名表
     *
     * @param examTypeId
     * @param detailsId
     * @param signUpPic
     * @param signUpTime
     * @param isConfirm
     * @param birthMonth
     * @return
     */
    boolean insertExamSignupListByUser(String examTypeId, String detailsId, String signUpPic, Date signUpTime, boolean isConfirm, Integer birthMonth);

    /**
     * 验证是否为审核该考试的管理员
     *
     * @param typeId
     * @param userId
     * @return
     */
    Optional<String> verifyAdministrator(String typeId,String userId);

    /**
     * 根据出生月份，选择对应未审核的考生
     *
     * @param typeId
     * @param month
     * @return
     */
    Optional<List<ReviewCandidate>> reviewCandidateInformation(String typeId, int[] month);

    /**
     * 通过userId获取用户名
     *
     * @param userId
     * @return
     */
    Optional<String> getUserNameByUserId(String userId);

    /**
     * 通过userId获取身份证正面
     *
     * @param userId
     * @return
     */
    Optional<String> getFrontPhotoUrlByUserId(String userId);

    /**
     * 通过userId获取身份证反面
     *
     * @param userId
     * @return
     */
    Optional<String> getReversePhotoUrlByUserId(String userId);

    /**
     * 查询对应考试的管理员列表（对应列表的）
     *
     * @param typeId
     * @return
     */
    Optional<List<ReviewPersonnel>> getExamAdminNumberByExamTypeId(String typeId);

    /**
     * 未通过者，发邮件告知，删除其报名表
     *
     * @param userId
     * @return
     */
    boolean deleteCandidateByUserId(String userId,String typeId);

    /**
     * 审核通过者，发邮件告知，更改审核状态为1
     *
     * @param userId
     * @return
     */
    boolean changeExamSignupList(String userId,String typeId);

    /**
     * 审核通过,更改考试类别表的审核人数
     *
     * @param typeId
     * @return
     */
    boolean changeCandidateNum(String typeId);

    /**
     * 计算该考试的过审人数
     *
     * @param typeId
     * @return
     */
    Integer getPassNumByAdmin(String typeId);

    /**
     * 管理员生成准考证
     * @param examId
     * @param schoolName
     * @param saveDir
     * @return
     * @throws IOException
     */
    String generateAdmissionTicketByAdmin(String examId, String schoolName, String saveDir) throws IOException;
}
