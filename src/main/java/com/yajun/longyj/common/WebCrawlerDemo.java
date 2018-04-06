package com.yajun.longyj.common;
 
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawlerDemo {
	
	public static void main(String[] args) {
		getJsonByInternet("http://sichuan.ouchn.cn/mod/page/view.php?id=139930");
	}
	
	 public static String getJsonByInternet(String path){
	        try {
	            URL url = new URL(path.trim());
	            //打开连接
	            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

	            if(200 == urlConnection.getResponseCode()){
	                //得到输入流
	                InputStream is =urlConnection.getInputStream();
	                ByteArrayOutputStream baos = new ByteArrayOutputStream();
	                byte[] buffer = new byte[1024];
	                int len = 0;
	                while(-1 != (len = is.read(buffer))){
	                    baos.write(buffer,0,len);
	                    baos.flush();
	                }
	                System.out.println(baos);
	                return baos.toString("utf-8");
	            }
	        }  catch (IOException e) {
	            e.printStackTrace();
	        }

	        return null;
	    }

} 

	
