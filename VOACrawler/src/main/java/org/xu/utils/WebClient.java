package org.xu.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebClient {

	//日志记录
	private static  Logger logger = 
			LoggerFactory.getLogger( Strategy.class);
	
	/*
	 * 根据传入的url 得到相应的  输入流
	 * 
	*/
	@SuppressWarnings("finally")
	public static InputStream url2sTream(StringBuilder url){
		
		
		
		InputStream instream = null;
			
		System.out.println(url.toString());
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			String str = url.toString();
			if(!str.contains("http://")){
				str = "http://www.51voa.com/"+str;
			}
			HttpGet httpGet = new HttpGet(str);
			CloseableHttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if(entity != null)
			{
				instream = entity.getContent();
			}
			
		} catch (ClientProtocolException e1) {
			// TODO Auto-generated catch block
			logger.error("url 转转为 stream出错:"+ e1.toString());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			logger.error("url 转转为 stream出错:"+ e1.toString());
		}finally{
			return instream;
		}
			
	}
}
