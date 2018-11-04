package studio.beita.hdxg.beitasystem.service.impl;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import studio.beita.hdxg.beitasystem.model.domain.ExamScore;
import studio.beita.hdxg.beitasystem.model.domain.ExamSession;
import studio.beita.hdxg.beitasystem.model.domain.ReturnScore;
import studio.beita.hdxg.beitasystem.model.domain.ReviewPersonnel;
import studio.beita.hdxg.beitasystem.repository.ExamScoreManagementDao;
import studio.beita.hdxg.beitasystem.service.ExamScoreManagementService;
import studio.beita.hdxg.beitasystem.utils.ExcelUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author zr
 * @program: beitasystem
 * @Title: ExamScoreManagementServiceImpl
 * @package: studio.beita.hdxg.beitasystem.service.impl
 * @description:
 **/
@Service
public class ExamScoreManagementServiceImpl implements ExamScoreManagementService {

    /**
     * EXCEL存储位置
     */
    @Value("${USER_EXCEL_FILE_REPOSITORY}")
    private String USER_EXCEL_FILE_REPOSITORY;


    @Autowired
    ExamScoreManagementDao examScoreManagementDao;

    @Override
    public Optional<ExamScore> getExamScoreByIdentifier(String identifier) {
        return Optional.ofNullable(examScoreManagementDao.getExamScoreByIdentifier(identifier));
    }

    @Override
    public Optional<String> getExamNameByIdentifier(String identifier) {
        return Optional.ofNullable(examScoreManagementDao.getExamNameByIdentifier(identifier));
    }

    @Override
    public Optional<String> checkUserInfo(String identifier, String name) {
        return Optional.ofNullable(examScoreManagementDao.checkUserInfo(identifier, name));
    }

    @Override
    public boolean changeExamScoreByReturnScore(List<ReturnScore> returnScore) {
        return examScoreManagementDao.changeExamScoreByReturnScore(returnScore)>0;
    }

    @Override
    public Optional<List<ExamScore>> getExamScoreListBySession(Integer sessionId) {
        return Optional.ofNullable(examScoreManagementDao.getExamScoreListBySession(sessionId));
    }

    @Override
    public Optional<String> getRealNameByIdentifier(String identifier) {
        return Optional.ofNullable(examScoreManagementDao.getExamNameByIdentifier(identifier));
    }

    @Override
    public Optional<ReviewPersonnel> verifyTimeByUserId(Integer userId) {
        return Optional.ofNullable(examScoreManagementDao.verifyTimeByUserId(userId));
    }

    @Override
    public boolean changeIsCheck(Integer userId) {
        return examScoreManagementDao.changeIsCheck(userId)>0;
    }

    @Override
    public boolean getIsQueryByIdentifier(String identifier) {
        return examScoreManagementDao.getIsQueryByIdentifier(identifier);
    }

    @Override
    public Optional<List<ExamSession>> getExamSessionList() {
        return Optional.ofNullable(examScoreManagementDao.getExamSessionList());
    }

    @Override
    public List<ReturnScore> changeExamScoreByByExcel(MultipartFile file) throws IOException {
        //储存Excel
        String fileName = ExcelUtils.uploadExcel(file, USER_EXCEL_FILE_REPOSITORY);
        //取出Excel进行解析
        FileInputStream inputStream = new FileInputStream(new File(USER_EXCEL_FILE_REPOSITORY+fileName));
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        //动态匹配准考证和成绩对应列
        int identifierLine=0,scoreNumline=0;
        XSSFRow row = sheet.getRow(0);
        XSSFCell cell;
        for(int i=0;i<10;i++){
            cell = row.getCell(i);
            if(cell.getStringCellValue().equals("准考证")){
                identifierLine = i;
            }
            if(cell.getStringCellValue().equals("成绩")){
                scoreNumline = i;
            }
        }
        //将数据填入List
        List<ReturnScore> returnScoreList = new ArrayList();
        //获取总行数
        int rowNum=sheet.getLastRowNum();
        ReturnScore rs = new ReturnScore();
        for(int i=0;i<rowNum;i++){
            row = sheet.getRow(i);
            rs.setIdentifier(row.getCell(identifierLine).getStringCellValue());
            if(row.getCell(scoreNumline).getStringCellValue()!=null){
                rs.setScoreNum(Integer.valueOf(row.getCell(scoreNumline).getStringCellValue()));
            }
            returnScoreList.add(rs);
        }
        return returnScoreList;
    }

    @Override
    public boolean outputExamScoreListByExcel(Optional<List<ExamScore>> examScoreListOptional) throws IOException {
        //获取成绩列表
        List<ExamScore> examScoreList = examScoreListOptional.get();
        //获取创建行数的数量（加title）
        int rowNumber = examScoreList.size()+1;
        //创建Excel工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("成绩");
        XSSFRow row = sheet.createRow(0);
        //在第一行填写标题
        String title[] = {"考试成绩表id","考试类别表id","考试场地id","准考证","成绩"};
        XSSFCell xssfCell;
        for (int i=0;i<5;i++){
            xssfCell = row.createCell(i);
            xssfCell.setCellValue(title[i]);
        }
        //填写数据
        for (int i=1;i<rowNumber;i++){
            row = sheet.createRow(i);
            xssfCell = row.createCell(0);
            xssfCell.setCellValue(examScoreList.get(i-1).getExamId());
            xssfCell = row.createCell(1);
            xssfCell.setCellValue(examScoreList.get(i-1).getExamName());
            xssfCell = row.createCell(2);
            xssfCell.setCellValue(examScoreList.get(i-1).getIdentifier());
            xssfCell = row.createCell(3);
            xssfCell.setCellValue(examScoreList.get(i-1).getRealName());
            xssfCell = row.createCell(4);
            xssfCell.setCellValue(examScoreList.get(i-1).getScoreNum());
        }
        FileOutputStream outputStream = new FileOutputStream(new File("C:\\Users\\hp\\Desktop\\ExamScore.xls"));
        workbook.write(outputStream);
        outputStream.close();
        return true;
    }
}
