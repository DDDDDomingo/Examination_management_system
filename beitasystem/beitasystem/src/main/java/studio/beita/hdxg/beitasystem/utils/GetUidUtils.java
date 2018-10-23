package studio.beita.hdxg.beitasystem.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author ydq
 * @program: beitasystem
 * @Title: GetUidUtils
 * @package: studio.beita.hdxg.beitasystem.utils
 * @description: 随机ID生成工具类
 **/

public class GetUidUtils {

    /**
     * 根据日期生成UserId
     * @return
     */
    public static int getNewUserId() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        String time = sdf.format(new Date(System.currentTimeMillis()));
        /**
         * 根据日期生成UUID
         */
        String uuid = time + "$" + UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        /**
         * 生成ID后缀
         */
        long suffix = Math.abs(uuid.hashCode() % 100000000);
        /**
         * 生成ID前缀
         */
        long prefix = Long.parseLong(time) * 100000000;
        return (int) (suffix + prefix);
    }

}
