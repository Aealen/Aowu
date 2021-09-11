package org.mecca.api;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.mecca.util.HttpUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodayInHistory {

    public static final String APPKEY = "338684bdbdb9113b";// 你的appkey
    public static final String URL = "https://api.jisuapi.com/todayhistory/query";

    public static final String month = null;
    public static final String day = null;

    public TodayInHistory() {


    }

    public static String Get() {

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("d-M-YYYY");
        String[] dateStr = formatter.format(date).split("-");

        /**
         * 不确定当前day 是否会自动补0
         * 保险起见  加了这行代码
         */
        if (dateStr[0].substring(0, 1).equals("0")){ dateStr[0]=dateStr[0].substring(1,2); }

        String result = null;
        String url = URL + "?appkey=" + APPKEY + "&month="+dateStr[1]+ "&day=" + dateStr[0];
        String RsltMsg="那年今日 -- "+dateStr[2]+"年"+dateStr[1]+"月"+dateStr[0]+"日\n";


        try {
            result = HttpUtil.sendGet(url, "utf-8");
            JSONObject json = JSONObject.fromObject(result);
            if (json.getInt("status") != 0) {
                System.out.println(json.getString("msg"));
            } else {
                JSONArray resultarr = json.optJSONArray("result");
                for (int i = 0; i < resultarr.size(); i++) {
                    JSONObject obj = (JSONObject) resultarr.opt(i);
                    String title = obj.getString("title");
                    String year = obj.getString("year");
                    String month = obj.getString("month");
                    String day = obj.getString("day");
                    String content = obj.getString("content");
                    System.out.println(title + " " + year + " " + month + " " + day );

                    RsltMsg=RsltMsg+year + "年" + month + "月" + day+"日 "+title+"\n";
                }


                System.out.println(RsltMsg);
                return RsltMsg;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "获取那年今日出错，请联系作者QQ：1592769715";
    }

    public static void main(String[] args) {
        Get();
    }
}