package studio.beita.hdxg.beitasystem.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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
    //用户报名时，身份验证（真实姓名，身份证是否完善），取出可以报名的考试类别表清单，选取考试类别，考生上传转账截图，点击报名。
    //审核员审核，查询审核对应出生月份的考生，将其报名表改为通过（一起取），对审核结果发送通知（审核完就发）是否通过都发，计算审核通过人数，超出则添加考场
    //管理员增删改查考试新闻（富文本，html格式，新闻资源），新闻类别未确定，游客和用户获取考试新闻列表（随时间倒置排列），游客点击增加阅读量，可以下载新闻资源
}
