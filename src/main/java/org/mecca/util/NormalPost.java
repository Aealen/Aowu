package org.mecca.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Show API的普通POST接口
 */
public class NormalPost {
    /**
     *普通post请求接口的方法示例
     * @param uri
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @param charset
     * @return
     */
    public static String sendPost(String uri, String param, String charset) {
        String result = null;
        PrintWriter out = null;
        InputStream in = null;
        try {
            URL url = new URL(uri);
            HttpURLConnection urlcon = (HttpURLConnection) url.openConnection(); //得到的是URLConnection对象
            urlcon.setDoInput(true); // 设置是否从httpUrlConnection读入，默认情况下是true;
            urlcon.setDoOutput(true);// 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在http正文内，因此需要设为true, 默认情况下是false;
            urlcon.setUseCaches(false);// Post 请求不能使用缓存
            urlcon.setRequestMethod("POST");
            urlcon.connect();// 获取连接
            out = new PrintWriter(urlcon.getOutputStream());//获取输出流
            out.print(param);
            out.flush();
            in = urlcon.getInputStream();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(in, charset));
            StringBuffer bs = new StringBuffer();
            String line = null;
            while ((line = buffer.readLine()) != null) {
                bs.append(line);
            }
            result = bs.toString();
        } catch (Exception e) { System.out.println("[请求异常][地址：" + uri + "][参数：" + e.getMessage() + "]");
        } finally {
            try {
                if (null != in)
                    in.close();
                if (null != out)
                    out.close();
            } catch (Exception e2) {
                System.out.println("[关闭流异常][错误信息：" + e2.getMessage() + "]");
            }
        }
        return result;
    }
}
