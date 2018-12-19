package studio.beita.hdxg.beitasystem.utils;

import studio.beita.hdxg.beitasystem.model.domain.ReviewPersonnel;
import java.util.List;


public class ExamSignUpUtils {

    /**
     * 获取考生出生日期
     * @param idCard
     * @return
     */
    public static int getBirthMonth(String idCard){
        String month=idCard.substring(10, 12);
        Integer intMonth = Integer.valueOf(month);
        return intMonth;
    }

    /**
     * 根据管理员次序，获取相应的工作内容（提取相应月份的考生名单）
     * @param examAdminNumber
     * @param userId
     * @param reviewPersonnelList
     * @return
     */
    public static int[] getmonth(int examAdminNumber,String userId,List<ReviewPersonnel> reviewPersonnelList){
        //num为该管理员的次序
        int num = 0;
        for(int i=0;i<examAdminNumber;i++){
            if(reviewPersonnelList.get(i).getUserId()==userId){
                num = i+1;
            }
        }
        int a = 12/examAdminNumber;
        int b = 12%examAdminNumber;

        int month[];
        if (num>b){
            month = new int[a];
        }else{
            month = new int[a+1];
        }
        for(int i=0;i<month.length;i++){
            month[i] = num+examAdminNumber*i;
        }
        return month;
    }

}
