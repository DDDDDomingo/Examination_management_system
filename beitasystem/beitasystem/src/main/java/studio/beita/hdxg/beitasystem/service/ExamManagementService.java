package studio.beita.hdxg.beitasystem.service;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import studio.beita.hdxg.beitasystem.model.domain.ExamInfo;
import studio.beita.hdxg.beitasystem.model.domain.ReviewPersonnel;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

/**
 * @author ydq
 * @program: beitasystem
 * @Title: ExamManagementService
 * @package: studio.beita.hdxg.beitasystem.service
 * @description: 考试管理模块Service层接口
 **/

public interface ExamManagementService {

    /**
     * 管理员添加考试
     *
     * @param examName
     * @param examCapacity
     * @param startTime
     * @param endTime
     * @return
     */
    boolean addExam(String examName, Integer examCapacity, String startTime, String endTime);

    /**
     * 管理员修改：考试是否已经结束
     *
     * @param examId
     * @return
     */
    boolean changeExamClosed(String examId);

    /**
     * 修改考试是否可以报名时，保证考试未结束
     *
     * @param examId
     * @return
     */
    Optional<String> assertExamIsClosed(String examId);

    /**
     * 管理员修改：考试是否可以报名
     *
     * @param examId
     * @param isSignUp
     * @return
     */
    boolean changeExamSignUp(String examId, boolean isSignUp);

    /**
     * 修改考试成绩为可查询时，保证考试未结束，考试报名已结束
     *
     * @param examId
     * @return
     */
    Optional<String> assertExamIsClosedSignUp(String examId);

    /**
     * 管理员修改：考试是否可以查询
     *
     * @param examId
     * @param isQuery
     * @return
     */
    boolean changeExamQuery(String examId, boolean isQuery);

    /**
     * 获取所有考试列表
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    List<ExamInfo> getExamList(Integer pageNumber, Integer pageSize);

    /**
     * 根据ID获取考试详细信息
     *
     * @param examId
     * @return
     */
    Optional<ExamInfo> getExamDetails(String examId);

    /**
     * 获取可报名考试列表
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    List<ExamInfo> getSignUpExamList(Integer pageNumber, Integer pageSize);

    /**
     * 管理员添加考试场次
     *
     * @param sessionPlace
     * @param sessionCapacity
     * @param sessionTime
     * @return
     */
    boolean addExamSession(String sessionPlace, Integer sessionCapacity, String sessionTime);

    /**
     * 管理员根据考试场次ID删除考场
     *
     * @param examId
     * @return
     */
    boolean deleteExamSession(String examId);

    /**
     * 管理员获取考场列表
     *
     * @param examId
     * @return
     */
    Optional<ExamInfo> adminGetExamDetails(String examId);

    /**
     * 获取该考试的管理员列表
     *
     * @param typeId
     * @param enterType
     * @param pageNumber
     * @param pageSize
     * @return
     */
    List<ReviewPersonnel> getExamAdminNumberByExamTypeId(String typeId, Integer enterType, Integer pageNumber, Integer pageSize);

    /**
     * 添加考试审核员
     *
     * @param typeId
     * @param userId
     * @param enterType
     * @return
     */
    boolean addReviewPerson(String typeId, String userId, Integer enterType);

    /**
     * 删除考试审核员
     *
     * @param enterPId
     * @return
     */
    boolean deleteReviewPerson(Integer enterPId);

}
