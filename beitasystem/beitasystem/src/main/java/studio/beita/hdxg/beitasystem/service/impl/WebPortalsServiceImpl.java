package studio.beita.hdxg.beitasystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.beita.hdxg.beitasystem.model.domain.ExamNews;
import studio.beita.hdxg.beitasystem.model.domain.ExamNewsType;
import studio.beita.hdxg.beitasystem.model.domain.Resource;
import studio.beita.hdxg.beitasystem.repository.WebPortalsDao;
import studio.beita.hdxg.beitasystem.service.WebPortalsService;

import java.util.List;
import java.util.Optional;

/**
 * @author zr
 * @program: beitasystem
 * @Title: WebPortalsServiceImpl
 * @package: studio.beita.hdxg.beitasystem.service.impl
 * @description:
 **/
@Service
public class WebPortalsServiceImpl implements WebPortalsService {
    @Autowired
    private WebPortalsDao webPortalsDao;

    @Override
    public boolean insertExamNewsByAdmin(Integer etypeId, String title, String content, String time, boolean isNew, Integer visits) {
        return webPortalsDao.insertExamNewsByAdmin(etypeId,title,content,time,isNew,visits)>0;
    }

    @Override
    public boolean insertResourceByAdmin(Integer newsId, String address, String savePath, String createTime) {
        return webPortalsDao.insertResourceByAdmin(newsId, address, savePath, createTime)>0;
    }

    @Override
    public boolean insertExamNewsTypeByAdmin(String name) {
        return webPortalsDao.insertExamNewsTypeByAdmin(name)>0;
    }

    @Override
    public boolean deleteExamNewsByAdmin(Integer newsId) {
        return webPortalsDao.deleteExamNewsByAdmin(newsId)>0;
    }

    @Override
    public boolean deleteResourceByAdmin(Integer resourceId) {
        return webPortalsDao.deleteResourceByAdmin(resourceId)>0;
    }

    @Override
    public boolean deleteExamNewsTypeByAdmin(Integer etypeId) {
        return webPortalsDao.deleteExamNewsTypeByAdmin(etypeId)>0;
    }

    @Override
    public boolean changeResourceByAdmin(Integer resourceId, Integer newsId, String address, String savePath, String createTime) {
        return webPortalsDao.changeResourceByAdmin(resourceId, newsId, address, savePath, createTime)>0;
    }

    @Override
    public boolean changeExamNewsTypeByAdmin(Integer etypeId, String typeName) {
        return webPortalsDao.changeExamNewsTypeByAdmin(etypeId, typeName)>0;
    }

    @Override
    public boolean changeExamNewsByAdmin(Integer newsId, Integer etypeId, String title, String content, String time, boolean isNew, Integer visits) {
        return webPortalsDao.changeExamNewsByAdmin(newsId, etypeId, title, content, time, isNew, visits)>0;
    }

    @Override
    public Optional<List<ExamNewsType>> getAllExamNews() {
        return Optional.ofNullable(webPortalsDao.getAllExamNews());
    }

    @Override
    public Optional<List<ExamNews>> getExamNewsByExamTypeId(Integer etypeId) {
        return Optional.ofNullable(webPortalsDao.getExamNewsByExamTypeId(etypeId));
    }

    @Override
    public Optional<List<Resource>> getResourceByNewsId(Integer newsId) {
        return Optional.ofNullable(webPortalsDao.getResourceByNewsId(newsId));
    }

    @Override
    public boolean changeExamNewsVisits(Integer newsId) {
        return webPortalsDao.changeExamNewsVisits(newsId)>0;
    }

    @Override
    public boolean changeIsNewByAdmin(Integer newsId, boolean isNew) {
        return  webPortalsDao.changeIsNewByAdmin(newsId,isNew)>0;
    }

    @Override
    public Optional<List<ExamNews>> getExamNewsByTitel(String title) {
        return Optional.ofNullable(webPortalsDao.getExamNewsByTitel(title));
    }
}
