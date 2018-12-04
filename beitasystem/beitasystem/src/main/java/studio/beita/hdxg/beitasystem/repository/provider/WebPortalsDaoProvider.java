package studio.beita.hdxg.beitasystem.repository.provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @author zr
 * @program: beitasystem
 * @Title: WebPortalsDaoProvider
 * @package: studio.beita.hdxg.beitasystem.repository.provider
 * @description: 门户网站模块Dao层动态SQL语句
 **/
public class WebPortalsDaoProvider {
    /**
     * 管理员增加考试新闻
     *
     * @param ienMap
     * @return
     */
    public String insertExamNewsByAdmin(Map<String, Object> ienMap) {
        Integer etypeId = (Integer) ienMap.get("etypeId");
        String content = (String) ienMap.get("content");
        String time = (String) ienMap.get("time");
        boolean isNew = (boolean) ienMap.get("isNew");
        Integer visits = (Integer) ienMap.get("visits");

        return new SQL() {
            {
                INSERT_INTO("exam_news");
                VALUES("etype_id", "#{etypeId}");
                VALUES("news_content", "#{content}");
                VALUES("news_time", "#{time}");
                VALUES("news_isnew", "#{isNew}");
                VALUES("news_visits", "#{visits}");
            }
        }.toString();
    }

    /**
     * 管理员增加考试新闻资源
     *
     * @param irMap
     * @return
     */
    public String insertResourceByAdmin(Map<String, Object> irMap) {
        Integer newsId = (Integer) irMap.get("newsId");
        String address = (String) irMap.get("address");
        String savePath = (String) irMap.get("savePath");
        String createTime = (String) irMap.get("createTime");

        return new SQL() {
            {
                INSERT_INTO("resource");
                VALUES("news_id", "#{newsId}");
                VALUES("res_link_address", "#{address}");
                VALUES("res_savepath", "#{savePath}");
                VALUES("res_createtime", "#{createTime}");
            }
        }.toString();
    }

    /**
     * 管理员增加考试新闻类别
     *
     * @param ientMap
     * @return
     */
    public String insertExamNewsTypeByAdmin(Map<String, Object> ientMap) {
        String name = (String) ientMap.get("name");

        return new SQL() {
            {
                INSERT_INTO("exam_news_type");
                VALUES("etype_name", "#{name}");
            }
        }.toString();
    }

    /**
     * 管理员更改考试新闻
     *
     * @param cesMap
     * @return
     */
    public String changeExamNewsByAdmin(Map<String, Object> cesMap) {
        Integer newsId = (Integer) cesMap.get("newsId");
        Integer etypeId = (Integer) cesMap.get("etypeId");
        String content = (String) cesMap.get("content");
        String time = (String) cesMap.get("time");
        boolean isNew = (boolean) cesMap.get("isNew");
        Integer visits = (Integer) cesMap.get("visits");

        return new SQL() {
            {
                UPDATE("exam_news");
                SET("news_id='" + newsId + "'");
                SET("etype_id='" + etypeId + "'");
                SET("news_content='" + content + "'");
                SET("news_time='" + time + "'");
                SET("news_isnew=" + isNew + "");
                SET("news_visits='" + visits + "'");
                WHERE("news_id =" + newsId);
            }
        }.toString();
    }

    /**
     * 管理员更改考试新闻资源
     *
     * @param crMap
     * @return
     */
    public String changeResourceByAdmin(Map<String, Object> crMap) {
        Integer resourceId = (Integer) crMap.get("resourceId");
        Integer newsId = (Integer) crMap.get("newsId");
        String address = (String) crMap.get("address");
        String savePath = (String) crMap.get("savePath");
        String createTime = (String) crMap.get("createTime");

        return new SQL() {
            {
                UPDATE("resource");
                SET("resource_id='" + resourceId + "'");
                SET("news_id='" + newsId + "'");
                SET("res_link_address='" + address + "'");
                SET("res_savepath='" + savePath + "'");
                SET("res_createtime='" + createTime + "'");
                WHERE("resource_id =" + resourceId);
            }
        }.toString();
    }

    /**
     * 管理员更改考试新闻类别
     *
     * @param centMap
     * @return
     */
    public String changeExamNewsTypeByAdmin(Map<String, Object> centMap) {
        Integer etypeId = (Integer) centMap.get("etypeId");
        String typeName = (String) centMap.get("typeName");

        return new SQL() {
            {
                UPDATE("exam_news_type");
                SET("etype_id='" + etypeId + "'");
                SET("etype_name='" + typeName + "'");
                WHERE("etype_id =" + etypeId);
            }
        }.toString();
    }

    /**
     *游客点击新闻增加阅读量，下载新闻资源
     *
     * @param ceMap
     * @return
     */
    public String changeExamNewsVisits(Map<String, Object> ceMap) {
        Integer newsId = (Integer) ceMap.get("newsId");

        return new SQL() {
            {
                UPDATE("exam_news");
                SET("news_visits = news_visits + 1");
                WHERE("news_id =" + newsId);
            }
        }.toString();
    }

    /**
     *游客点击新闻增加阅读量，下载新闻资源
     *
     * @param cinMap
     * @return
     */
    public String changeIsNewByAdmin(Map<String, Object> cinMap) {
        Integer newsId = (Integer) cinMap.get("newsId");
        boolean isNew = (boolean) cinMap.get("isNew");
        return new SQL() {
            {
                UPDATE("exam_news");
                SET("news_isnew =" + isNew + "");
                WHERE("news_id =" + newsId);
            }
        }.toString();
    }
}
