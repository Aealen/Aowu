package org.mecca.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    /**
     * 获取当前Day
     * @return
     */
    public static int getDateByDay(){
        Date date=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd");

        String dateS =formatter.format(date);
        return Integer.valueOf(dateS);
    }

    /**
     * 获取当前月份
     * @return
     */
    public static int getDateByMonth(){
        Date date=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("M");
        String dateS =formatter.format(date);
        return Integer.valueOf(dateS);
    }

    /**
     * get Hours
     * @return
     */
    public static int getTimeByHour(){
        Date date=new Date();
        SimpleDateFormat format=new SimpleDateFormat("HH");
        String HourS=format.format(date);
        return Integer.valueOf(HourS);
    }/**
     * get Minutes
     * @return
     */
    public static int getTimeByMinute(){
        Date date=new Date();
        SimpleDateFormat format=new SimpleDateFormat("mm");
        String MinuteS=format.format(date);
        return Integer.valueOf(MinuteS);
    }

    /**
     * 获取当前月有多少天
     * @return
     */
    public static int getTotalOfMonth(){
        //获取当前时间
        Calendar cal = Calendar.getInstance();
        //下面可以设置月份，注：月份设置要减1，所以设置1月就是1-1，设置2月就是2-1，如此类推
        cal.set(Calendar.MONTH, getDateByMonth());
        //调到上个月
        cal.add(Calendar.MONTH, -1);
        //得到一个月最最后一天日期(31/30/29/28)
        int MaxDay=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //按你的要求设置时间
        cal.set( cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), MaxDay, 23, 59, 59);
        //按格式输出
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        return Integer.valueOf(sdf.format(cal.getTime())).intValue();
    }

    public static boolean isEndOfMonth(int flag){
        if (flag==getTotalOfMonth()){
            //是月底
            return true;
        }else{
            return false;
        }
    }


    public static void main(String[] args) {



    }

}
