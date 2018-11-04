package studio.beita.hdxg.beitasystem.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author zr
 * @program: xyessc
 * @Title: ExcelUtils
 * @package: studio.ydq.hdxg.xyessc.utils
 * @description: excel处理函数
 **/
public class ExcelUtils {
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    public static String uploadExcel(MultipartFile file, String adSavePath) {
        if (file.isEmpty()) {
            return null;
        }
        //获取原文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 解决中文问题，liunx下中文路径，图片显示问题
        fileName = UUID.randomUUID() + suffixName;
        //int size = (int) file.getSize();
        //System.out.println(fileName + "-->" + size);
        String savePath = adSavePath + "/" + fileName;
        File dest = new File(savePath);
        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        try {
            //保存文件
            file.transferTo(dest);
            return fileName;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
