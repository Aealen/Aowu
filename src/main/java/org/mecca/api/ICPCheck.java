package org.mecca.api;

import net.sf.json.JSONObject;
import org.mecca.util.HttpUtil;
import org.mecca.util.NormalPost;


public class ICPCheck {

    public static String Get(String flag) {
        String url = "https://api.vvhan.com/api/icp?url="+flag;
        String result = null;
        String RsMsg="备案查询:  " + flag+"\n";
        JSONObject json=null;
//        {
//            "success":true,
//                "domain":"baidu.com",
//                "info":
//            {"name":"北京百度网讯科技有限公司",
//                    "nature":"企业",
//                    "icp":"京ICP证030173号-1",
//                    "title":"百度",
//                    "time":"2021-09-11 15:49:39"
//            }
//        }

        try {
            result = HttpUtil.sendGet(url, "utf-8");
            json= JSONObject.fromObject(result);
            System.out.println(json);
            System.out.println(json.getJSONObject("info").getString("time"));

            String domain= json.getString("domain");
            String name= json.getJSONObject("info").getString("name");
            String type= json.getJSONObject("info").getString("nature");
            String icp= json.getJSONObject("info").getString("icp");
            String title= json.getJSONObject("info").getString("title");
            String time= json.getJSONObject("info").getString("time");

            return RsMsg+"域名:     "+domain+"\n备案方:    "+name+"\n类型:　     "+type+"\nICP备案号:  "+icp+"\n网站标题:   "+title+"\n时间:     "+time;
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (json.getString("message").equals("此域名未备案")){
            return json.getString("message");
        }

        return "备案查询功能出现问题,请尽快联系作者QQ: 1592769715";
    }

    public static void main(String[] args) {

        System.out.println( Get("blog.fangmingxuan.com"));

    }




}
