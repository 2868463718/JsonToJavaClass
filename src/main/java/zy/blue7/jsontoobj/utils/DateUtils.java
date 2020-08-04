package zy.blue7.jsontoobj.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author blue7
 * @date 2020/8/4 9:40
 **/
public class DateUtils {

    /**
     * 获取当前时间
     * @return
     */
    public static String getCurrentDate(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH: mm: ss: SSS");
        String str=simpleDateFormat.format(new Date());

        return str;
    }

}
