package studio.beita.hdxg.beitasystem.service;

import studio.beita.hdxg.beitasystem.model.domain.ExamNews;
import studio.beita.hdxg.beitasystem.model.domain.ExamNewsType;

import java.util.List;
import java.util.Optional;

/**
 * @author zr
 * @program: beitasystem
 * @Title: WebPortalsService
 * @package: studio.beita.hdxg.beitasystem.service
 * @description: 门户网站模块表Service层接口
 **/
public interface WebPortalsService {

    /**
     * 管理员增加考试新闻
     *
     * @param etypeId
     * @param content
     * @param time
     * @param isNew
     * @param visits
     * @return
     */
    boolean insertExamNewsByAdmin(Integer etypeId, String content, String time, boolean isNew, Integer visits);

    /**
     * 管理员增加考试新闻资源
     *
     * @param newsId
     * @param address
     * @param savePath
     * @param createTime
     * @return
     */
    boolean insertResourceByAdmin(Integer newsId, String address, String savePath, String createTime);

    /**
     * 管理员增加考试新闻类别
     *
     * @param name
     * @return
     */
    boolean insertExamNewsTypeByAdmin(String name);

    /**
     * 管理员删除考试新闻
     *
     * @param newsId
     * @return
     */
    boolean deleteExamNewsByAdmin(Integer newsId);

    /**
     * 管理员删除考试新闻资源
     *
     * @param resourceId
     * @return
     */
    boolean deleteResourceByAdmin(Integer resourceId);

    /**
     * 管理员删除考试新闻类别
     *
     * @param etypeId
     * @return
     */
    boolean deleteExamNewsTypeByAdmin(Integer etypeId);

    /**
     * 管理员更改考试新闻资源
     *
     * @param resourceId
     * @param newsId
     * @param address
     * @param savePath
     * @param createTime
     * @return
     */
    boolean changeResourceByAdmin(Integer resourceId, Integer newsId, String address, String savePath, String createTime);

    /**
     * 管理员更改考试新闻类别
     *
     * @param etypeId
     * @param typeName
     * @return
     */
    boolean changeExamNewsTypeByAdmin(Integer etypeId,String typeName);

    /**
     * 管理员更改考试新闻
     *
     * @param newsId
     * @param etypeId
     * @param content
     * @param time
     * @param isNew
     * @param visits
     * @return
     */
    boolean changeExamNewsByAdmin(Integer newsId,Integer etypeId, String content, String time, boolean isNew, Integer visits);

    /**
     * 查看全部新闻
     *
     * @return
     */
    Optional<List<ExamNewsType>> getAllExamNews();

    /**
     * 根据新闻类别id查看新闻
     *
     * @param etypeId
     * @return
     */
    Optional<List<ExamNews>> getExamNewsByExamTypeId(Integer etypeId);

    /**
     * 根据新闻类别id查看考试新闻资源
     *
     * @param newsId
     * @return
     */
    Optional<List<ExamNews>> getResourceByNewsId(Integer newsId);

    /**
     * 游客点击新闻增加阅读量，下载新闻资源
     *
     * @param newsId
     * @return
     */
    boolean changeExamNewsVisits(Integer newsId);

    /**
     * 更改是否为最新新闻状态
     *
     * @param newsId
     * @param isNew
     * @return
     */
    boolean changeIsNewByAdmin(Integer newsId, boolean isNew);
}
