package studio.beita.hdxg.beitasystem.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import studio.beita.hdxg.beitasystem.model.domain.ExamInfo;
import studio.beita.hdxg.beitasystem.model.domain.ExamSignupList;
import studio.beita.hdxg.beitasystem.model.domain.UserDetails;

import java.util.Date;
import java.util.List;

/**
 * @author zr
 * @program: beitasystem
 * @Title: ExamSignUpDao
 * @package: studio.beita.hdxg.beitasystem.service
 * @description: 考试报名模块Service层接口
 */
@Mapper
@Repository
public interface ExamSignUpDao {
    // TODO: 2018/11/5  用户报名时，身份验证（真实姓名，身份证是否完善），取出可以报名的考试类别表清单，选取考试类别，考生上传转账截图，点击报名。
    // TODO: 2018/11/5  审核员审核，查询审核对应出生月份的考生，将其报名表改为通过（一起取），对审核结果发送通知（审核完就发）是否通过都发，计算审核通过人数，超出则添加考场,结束考试直接
    // TODO: 2018/11/5  select添加升降序
    // TODO: 2018/11/5  限定考试审核员添加时间 
    //管理员增删改查考试新闻（富文本，html格式，新闻资源），新闻类别未确定，游客和用户获取考试新闻列表（随时间倒置排列），游客点击增加阅读量，可以下载新闻资源

    /**
     * 身份验证（真实姓名，身份证是否完善）需要修改
     *
     * @param userId
     * @return
     */
    @Select("SELECT details_id, details_avatar, details_savepath, details_phone, details_address, details_realname, details_idcard FROM user_details WHERE details_id = #{userId}")
    UserDetails userAuthentication(Integer userId);

    /**
     * 取出可以报名的考试类别表清单
     *
     * @return
     */
    @Select("SELECT exam_type_id, exam_name, exam_isclosed, exam_issignup, exam_isquery, exam_audited_num, exam_capacity, exam_starttime, exam_endtime FROM exam_type WHERE exam_issignup = 1 GROUP BY exam_type_id DESC")
    List<ExamInfo> getExamInfoList();

    @Insert()
    ExamSignupList insertExamSignupListByUser(String examTypeId, String detailsId, String signUpPic, Date signUpTime, boolean isConfirm);



}
