package com.carwel.webmagic.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeUtils {

    public static final String            DATA_FORMAT_yyyy_MM_dd   = "yyyy-MM-dd";
    public static final String            DATA_FORMAT_yyyyMMdd     = "yyyyMMdd";
    public static final String            DATA_FORMAT_yyMMddHHmmss = "yyyyMMddHHmmss";
    public static final String            DATE_TIME_STYLE          = "yyyy-MM-dd HH:mm:ss";
    public static final String            DATE_TIME_STYLE_yyyyMMddHHmmSSS          = "yyyyMMddHHmmssSSS";
 /*   private static final SimpleDateFormat DATE_TIME_FORMAT         = new SimpleDateFormat(DATE_TIME_STYLE);*/


    /**
     * @param date   格式化前的时间
     * @param format 需要转换的格式
     * @return 格式化后的时间
     * @throws ParseException
     */
    public static Date formatDate(String date, String format) {
        try {
            if (date == null) {
                return null;
            }
          /*  DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
            LocalDateTime dateTime = LocalDateTime.parse(date, df);
            Date date1=Date.from(dateTime.toInstant(ZoneOffset.of("+8")));
            return date1;*/
            SimpleDateFormat df=new SimpleDateFormat(format);
            df.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            Date e= new SimpleDateFormat(format).parse(date);
            return  e;
        } catch (Exception e) {
            return null;
        }
    }

    public static String formatString(Date date, String format) {
        try {
            if (date == null) {
                return null;
            }
            return  new SimpleDateFormat(format).format(date);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 返回当前时间 返回的数据类型是Timestamp
     * @return
     */
    public static Timestamp getNowTime(){
        Date date = new Date();
        return new Timestamp(date.getTime());
    }

    public static  void  main(String[] args){
//        String time="20190422101247000";
//       Date ti= DateTimeUtils.formatDate(time,DateTimeUtils.DATE_TIME_STYLE_yyyyMMddHHmmSSS);
//       Date d=new Date();
//       System.out.println(ti);
        String str = "2019-04-22 17:36:18";
        boolean latestWeek = isLatestWeek(formatDate(str, DATE_TIME_STYLE), new Date());
        System.out.println(latestWeek);
    }

    /**
     * 判断某个时间是否在七天内
     * @param addtime
     * @param now
     * @return
     */
    public static boolean isLatestWeek(Date addtime,Date now){
        Calendar calendar = Calendar.getInstance();  //得到日历
        calendar.setTime(now);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -7);  //设置为7天前
        Date before7days = calendar.getTime();   //得到7天前的时间
        if(before7days.getTime() < addtime.getTime()){
            return true;
        }else{
            return false;
        }
    }



}
