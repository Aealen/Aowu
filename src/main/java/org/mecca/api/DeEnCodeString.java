package org.mecca.api;


import net.sf.json.JSONObject;
import org.jetbrains.annotations.Nullable;
import org.mecca.util.HttpUtil;

public class DeEnCodeString {
    public static void main(String[] args) {

        System.out.println(EncodeWithSalt("Mecca","12"));
        System.out.println(EncodeWithoutSalt("Mecca"));
        System.out.println(DecodeWithSalt("ImtqdlN5NzcyUVwvU25cLzlKY3k1TVFWQT09Ig==", "12"));
        System.out.println(DecodeWithoutSalt("ImF6cUpuT1wvaW5nOUxSYVJYMkNHdGhBPT0i"));
    }


    public static String EncodeWithSalt(String str,String salt){
        String rs = "Encoded!!\nString: "+str+"  Salt: "+salt+"\nResult: ";
        String url="https://api.vvhan.com/api/jm?key="+salt+"&string="+str+"&type=en";
        JSONObject json=null;
        String result = null;

        try {
            result= HttpUtil.sendGet(url,"utf-8");
            json = JSONObject.fromObject(result);
            if (json.getString("success").equals("false")){
              return json.getString("message");
            }else if (json.getString("success").equals("true")){
               return rs+json.getString("enmissString");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Encode功能出错 ,联系作者QQ 1592769715";
    }

    public static String EncodeWithoutSalt(String str){
        String rs = "Encoded!!\nString: "+str+"\nResult: ";
        String url="https://api.vvhan.com/api/jm?string="+str+"&type=en";
        JSONObject json=null;
        String result = null;


        try {
            result = HttpUtil.sendGet(url, "utf-8");
            json=JSONObject.fromObject(result);
            return rs+json.getString("enmissString");

        } catch (Exception e) {
            e.printStackTrace();
        }


        return "Encode功能出错 ,联系作者QQ 1592769715";
    }


    public static String DecodeWithSalt(String str,String salt){
        String rs = "Decoded!!\nString: "+str+"  Salt: "+salt+"\nResult:　";
        String url="https://api.vvhan.com/api/jm?key="+salt+"&string="+str+"&type=de";
        JSONObject json=null;
        String result = null;

        try {
            result=HttpUtil.sendGet(url,"utf-8");
            json=JSONObject.fromObject(result);

            return rs+json.getString("demissString");


        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Decode功能出错 ,联系作者QQ 1592769715";
    }

    public static String DecodeWithoutSalt(String str){
        String rs = "Decoded!!\nString: "+str+"\nResult: ";
        String url="https://api.vvhan.com/api/jm?string="+str+"&type=de";
        JSONObject json=null;
        String result = null;


        try {
            result=HttpUtil.sendGet(url,"utf-8");
            json=JSONObject.fromObject(result);

            return rs+json.getString("demissString");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Decode功能出错 ,联系作者QQ 1592769715";
    }
}
