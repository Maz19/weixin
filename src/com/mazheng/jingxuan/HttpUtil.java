package com.mazheng.jingxuan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;


public class HttpUtil {
	public static InputStream get(String path) throws IOException{
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("apikey",  "f619078d1f952289fb386bf36077115e");
        conn.connect();
        InputStream is = conn.getInputStream();
		return is;
		
	}
	public static String isToString(InputStream is)throws IOException{
		StringBuilder sb = new StringBuilder();
		String line = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		while((line = reader.readLine()) != null){
			sb.append(line);
		}
		return sb.toString();
	}
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	


