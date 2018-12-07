package studio.beita.hdxg.beitasystem.repository;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import studio.beita.hdxg.beitasystem.model.domain.ExamNews;
import studio.beita.hdxg.beitasystem.model.domain.ExamNewsType;
import studio.beita.hdxg.beitasystem.model.domain.Resource;
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

    /**
     * 管理员增加考试新闻
     *
     * @param etypeId
     * @param title
     * @param content
     * @param time
     * @param isNew
     * @param visits
     * @return
     */
    @InsertProvider(type = WebPortalsDaoProvider.class, method = "insertExamNewsByAdmin")
    Integer insertExamNewsByAdmin(@Param("etypeId")Integer etypeId,@Param("title")String title, @Param("content")String content, @Param("time")String time, @Param("isNew")boolean isNew, @Param("visits")Integer visits);

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
    @UpdateProvider(type = WebPortalsDaoProvider.class, method = "changeExamNewsByAdmin")
    Integer changeExamNewsByAdmin(@Param("newsId")Integer newsId,@Param("etypeId")Integer etypeId,@Param("title")String title, @Param("content")String content, @Param("time")String time, @Param("isNew")boolean isNew, @Param("visits")Integer visits);

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
    @Select("SELECT etype_id, etype_name FROM exam_news_type ORDER BY etype_id ASC")
            @Results(
            id = "getAllExamNews",
            value = {
                    @Result(id = true, property = "etypeId", column = "etype_id"),
                    @Result(property = "typeName", column = "etype_name"),
                    @Result(property = "examNewsList", column = "etype_id",many = @Many(select = "studio.beita.hdxg.beitasystem.repository.WebPortalsDao.getExamNewsByExamTypeId"))
            }
    )
    List<ExamNewsType> getAllExamNews();

    /**
     * 根据新闻类别id查看新闻
     *
     * @param etypeId
     * @return
     */
    @Select("SELECT news_id, etype_id,news_title, news_content, news_time, news_isnew, news_visits FROM exam_news WHERE etype_id = #{etypeId} ORDER BY news_time DESC")
            @Results(
            id = "getAllExamNewAndResource",
            value = {
                    @Result(id = true, property = "newsId", column = "news_id"),
                    @Result(property = "etypeId", column = "etype_id"),
                    @Result(property = "title", column = "news_title"),
                    @Result(property = "content", column = "news_content"),
                    @Result(property = "time", column = "news_time"),
                    @Result(property = "isNew", column = "news_isnew"),
                    @Result(property = "visits", column = "news_visits"),
                    @Result(property = "resourceList", column = "news_id",many = @Many(select = "studio.beita.hdxg.beitasystem.repository.WebPortalsDao.getResourceByNewsId"))
            }
    )
    List<ExamNews> getExamNewsByExamTypeId(Integer etypeId);

    /**
     * 根据新闻id查看考试新闻资源
     *
     * @param newsId
     * @return
     */
    @Select("SELECT resource_id,news_id, res_link_address, res_savepath, res_createtime FROM resource WHERE news_id = #{newsId} ORDER BY resource_id DESC")
    @Results(
            id = "getResourceNewAndResource",
            value = {
                @Result(id = true, property = "resourceId", column = "resource_id"),
                @Result(property = "newsId", column = "news_id"),
                @Result(property = "address", column = "res_link_address"),
                @Result(property = "savePath", column = "res_savepath"),
                @Result(property = "createTime", column = "res_createtime")
            }
    )
    List<Resource> getResourceByNewsId(Integer newsId);

    /**
     * 游客点击新闻增加阅读量，下载新闻资源
     *
     * @param newsId
     * @return
     */
    @UpdateProvider(type = WebPortalsDaoProvider.class, method = "changeExamNewsVisits")
    Integer changeExamNewsVisits(@Param("newsId")Integer newsId);

    /**
     * 更改是否为最新新闻状态
     *
     * @param newsId
     * @param isNew
     * @return
     */
    @UpdateProvider(type = WebPortalsDaoProvider.class, method = "changeIsNewByAdmin")
    Integer changeIsNewByAdmin(@Param("newsId")Integer newsId, @Param("isNew")boolean isNew);

    /**
     * 标题模糊查询新闻
     *
     * @param title
     * @return
     */
    @Select("SELECT news_id, etype_id,news_title, news_content, news_time, news_isnew, news_visits FROM exam_news WHERE  news_title LIKE \"%\"#{title}\"%\" ORDER BY news_time DESC")
    @Results(
            id = "getExamNewsByTitel",
            value = {
                    @Result(id = true, property = "newsId", column = "news_id"),
                    @Result(property = "etypeId", column = "etype_id"),
                    @Result(property = "title", column = "news_title"),
                    @Result(property = "content", column = "news_content"),
                    @Result(property = "time", column = "news_time"),
                    @Result(property = "isNew", column = "news_isnew"),
                    @Result(property = "visits", column = "news_visits"),
                    @Result(property = "resourceList", column = "news_id",many = @Many(select = "studio.beita.hdxg.beitasystem.repository.WebPortalsDao.getResourceByNewsId"))
            }
    )
    List<ExamNews> getExamNewsByTitel(@Param("title")String title);
}
