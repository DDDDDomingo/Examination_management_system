package studio.beita.hdxg.beitasystem.repository;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import studio.beita.hdxg.beitasystem.model.domain.ExamNews;
import studio.beita.hdxg.beitasystem.model.domain.ExamNewsType;
import studio.beita.hdxg.beitasystem.repository.provider.ExamSignUpDaoProvider;
import studio.beita.hdxg.beitasystem.repository.provider.WebPortalsDaoProvider;

import java.util.List;

/**
 * @author zr
 * @program: beitasystem
 * @Title: ExamSignUpDao
 * @package: studio.beita.hdxg.beitasystem.service
 * @description: 门户网站模块Dao层接口
 */
@Mapper
@Repository
public interface WebPortalsDao {
    // TODO: 2018/11/6  管理员增删改查考试新闻（富文本，html格式，新闻资源），游客和用户获取考试新闻列表（随时间倒置排列），游客点击增加阅读量，可以下载新闻资源

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
    @InsertProvider(type = WebPortalsDaoProvider.class, method = "insertExamNewsByAdmin")
    Integer insertExamNewsByAdmin(@Param("etypeId")Integer etypeId, @Param("content")String content, @Param("time")String time, @Param("isNew")boolean isNew, @Param("visits")Integer visits);

    /**
     * 管理员增加考试新闻资源
     *
     * @param newsId
     * @param address
     * @param savePath
     * @param createTime
     * @return
     */
    @InsertProvider(type = WebPortalsDaoProvider.class, method = "insertResourceByAdmin")
    Integer insertResourceByAdmin(@Param("newsId")Integer newsId, @Param("address")String address, @Param("savePath")String savePath, @Param("createTime")String createTime);

    /**
     * 管理员增加考试新闻类别
     *
     * @param name
     * @return
     */
    @InsertProvider(type = WebPortalsDaoProvider.class, method = "insertExamNewsTypeByAdmin")
    Integer insertExamNewsTypeByAdmin(@Param("name")String name);

    /**
     * 管理员删除考试新闻
     *
     * @param newsId
     * @return
     */
    @Delete("DELETE FROM exam_news WHERE news_id = #{newsId}")
    Integer deleteExamNewsByAdmin(Integer newsId);

    /**
     * 管理员删除考试新闻资源
     *
     * @param resourceId
     * @return
     */
    @Delete("DELETE FROM resource WHERE resource_id = #{resourceId}")
    Integer deleteResourceByAdmin(Integer resourceId);

    /**
     * 管理员删除考试新闻类别
     * @param etypeId
     * @return
     */
    @Delete("DELETE FROM exam_news_type WHERE etype_id = #{etypeId}")
    Integer deleteExamNewsTypeByAdmin(Integer etypeId);

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
    @UpdateProvider(type = WebPortalsDaoProvider.class, method = "changeExamSignupList")
    Integer changeExamNewsByAdmin(@Param("newsId")Integer newsId,@Param("etypeId")Integer etypeId, @Param("content")String content, @Param("time")String time, @Param("isNew")boolean isNew, @Param("visits")Integer visits);

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
    @UpdateProvider(type = WebPortalsDaoProvider.class, method = "changeResourceByAdmin")
    Integer changeResourceByAdmin(@Param("resourceId")Integer resourceId,@Param("newsId")Integer newsId, @Param("address")String address, @Param("savePath")String savePath, @Param("createTime")String createTime);

    /**
     * 管理员更改考试新闻类别
     *
     * @param etypeId
     * @param typeName
     * @return
     */
    @UpdateProvider(type = WebPortalsDaoProvider.class, method = "changeExamNewsTypeByAdmin")
    Integer changeExamNewsTypeByAdmin(@Param("etypeId")Integer etypeId,@Param("typeName")String typeName);

    /**
     * 查看全部新闻
     *
     * @return
     */
    @Select("SELECT etype_id, etype_name FROM review_personnel ORDER BY etype_id ASC")
    @Results(
            id = "getAllExamNews",
            value = {
                    @Result(id = true, property = "signup_id", column = "etype_id"),
                    @Result(property = "examTypeId", column = "etype_name"),
                    @Result(property = "examNewsList", column = "etype_id",many = @Many(select = "studio.beita.hdxg.beitasystem.repository.WebPortalsDao.getExamNewsByExamTypeId")),
                    @Result(property = "resourceList", column = "etype_id",many = @Many(select = "studio.beita.hdxg.beitasystem.repository.WebPortalsDao.getResourceByExamTypeId")),
            }
    )
    List<ExamNewsType> getAllExamNews();

    /**
     * 根据新闻类别id查看新闻
     *
     * @param etypeId
     * @return
     */
    @Select("SELECT news_id, etype_id, news_content, news_time, news_isnew, news_visits FROM exam_news WHERE etype_id = #{etypeId} ORDER BY news_time DESC")
    List<ExamNews> getExamNewsByExamTypeId(Integer etypeId);

    /**
     * 根据新闻类别id查看考试新闻资源
     *
     * @param etypeId
     * @return
     */
    @Select("SELECT resource_id,news_id, res_link_address, res_savepath, res_createtime FROM exam_news WHERE etype_id = #{etypeId} ORDER BY resource_id DESC")
    List<ExamNews> getResourceByExamTypeId(Integer etypeId);

    /**
     * 游客点击新闻增加阅读量，下载新闻资源
     *
     * @param newsId
     * @return
     */
    @UpdateProvider(type = ExamSignUpDaoProvider.class, method = "changeExamNewsVisits")
    Integer changeExamNewsVisits(@Param("newsId")Integer newsId);
    
}
