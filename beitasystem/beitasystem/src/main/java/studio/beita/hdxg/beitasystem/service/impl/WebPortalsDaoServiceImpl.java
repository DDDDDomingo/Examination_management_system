package studio.beita.hdxg.beitasystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import studio.beita.hdxg.beitasystem.model.domain.ExamNews;
import studio.beita.hdxg.beitasystem.model.domain.ExamNewsType;
import studio.beita.hdxg.beitasystem.repository.PersonalInformationDao;
import studio.beita.hdxg.beitasystem.repository.WebPortalsDao;
import studio.beita.hdxg.beitasystem.service.WebPortalsDaoService;

import java.util.List;
import java.util.Optional;

public class WebPortalsDaoServiceImpl implements WebPortalsDaoService {
    @Autowired
    private WebPortalsDao webPortalsDao;

    @Override
    public boolean insertExamNewsByAdmin(Integer etypeId, String content, String time, boolean isNew, Integer visits) {
        return webPortalsDao.insertExamNewsByAdmin(etypeId,content,time,isNew,visits)>0;
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
    public Optional<List<ExamNewsType>> getAllExamNews() {
        return Optional.ofNullable(webPortalsDao.getAllExamNews());
    }

    @Override
    public Optional<List<ExamNews>> getExamNewsByExamTypeId(Integer etypeId) {
        return Optional.ofNullable(webPortalsDao.getExamNewsByExamTypeId(etypeId));
    }

    @Override
    public Optional<List<ExamNews>> getResourceByExamTypeId(Integer etypeId) {
        return Optional.ofNullable(webPortalsDao.getResourceByExamTypeId(etypeId));
    }

    @Override
    public boolean changeExamNewsVisits(Integer newsId) {
        return webPortalsDao.changeExamNewsVisits(newsId)>0;
    }
}
