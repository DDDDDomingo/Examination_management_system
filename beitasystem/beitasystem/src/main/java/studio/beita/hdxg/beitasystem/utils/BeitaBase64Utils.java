package studio.beita.hdxg.beitasystem.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.util.Base64Utils;

import java.io.File;
import java.util.UUID;

/**
 * @author ydq
 * @program: xyessc
 * @Title: BeitaBase64Utils
 * @package: studio.ydq.hdxg.xyessc.utils
 * @description: 前端拍照上传，处理函数
 **/
public class BeitaBase64Utils {

    /**
     * 保存第一次添加的人脸图片
     *
     * @param base64Facedetail
     * @return
     */
    public static String saveBase64Data(String base64Facedetail, String userFaceSavePath) {
        return base64ToJpg(base64Facedetail, userFaceSavePath);
    }

    /**
     * 暂时保存验证的人脸图片
     *
     * @param loginFacedetail
     * @return
     */
    public static String saveTempBase64Data(String loginFacedetail, String tempUserFaceSavePath) {
        return base64ToJpg(loginFacedetail, tempUserFaceSavePath);
    }

    private static String base64ToJpg(String base64Data, String savePath) {
        try {
            String dataPrix = "";
            String data = "";
            if (base64Data == null || "".equals(base64Data)) {
                throw new Exception("上传失败，上传图片数据为空");
            } else {
                String[] d = base64Data.split("base64,");
                if (d != null && d.length == 2) {
                    dataPrix = d[0];
                    data = d[1];
                } else {
                    throw new Exception("上传失败，数据不合法");
                }
            }
            String suffix = "";
            if ("data:image/jpeg;".equalsIgnoreCase(dataPrix)) {
                //data:image/jpeg;base64,base64编码的jpeg图片数据
                suffix = ".jpg";
            } else if ("data:image/x-icon;".equalsIgnoreCase(dataPrix)) {
                //data:image/x-icon;base64,base64编码的icon图片数据
                suffix = ".ico";
            } else if ("data:image/gif;".equalsIgnoreCase(dataPrix)) {
                //data:image/gif;base64,base64编码的gif图片数据
                suffix = ".gif";
            } else if ("data:image/png;".equalsIgnoreCase(dataPrix)) {
                //data:image/png;base64,base64编码的png图片数据
                suffix = ".png";
            } else if ("data:image/jpg;".equalsIgnoreCase(dataPrix)) {
                //data:image/png;base64,base64编码的png图片数据
                suffix = ".jpg";
            } else {
                throw new Exception("上传图片格式不合法");
            }
            String tempFileName = savePath + UUID.randomUUID().toString() + suffix;

            //因为BASE64Decoder的jar问题，此处使用spring框架提供的工具包
            byte[] bs = org.springframework.util.Base64Utils.decodeFromString(data);
            try {
                //使用apache提供的工具类操作流
                FileUtils.writeByteArrayToFile(new File(tempFileName), bs);
            } catch (Exception ee) {
                throw new Exception("上传失败，写入文件失败，" + ee.getMessage());
            }
            return tempFileName;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 删除人脸临时图片
     *
     * @param filaPath
     */
    public static void delFile(String filaPath) {
        File file = new File(filaPath);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }

}
