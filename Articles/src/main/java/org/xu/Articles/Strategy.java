package org.xu.Articles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





/*
 * 采用策略模式
 * 同样 都是讲  InputStream 转换为  StringBuilder
 * 根据不同的 要求  ，存在 不同的 解析策略
 */
public class Strategy {
	
	private static  Logger logger = 
			LoggerFactory.getLogger( Strategy.class);
	
	

	/*
	 * 将 网页的list标签中的url获取
	 */
	@SuppressWarnings("finally")
	public static StringBuilder getPageList(InputStream stream, String filter) {

		String builder = null;
		StringBuilder result = null;
		if (stream == null)
			return null;

		BufferedReader reader = new BufferedReader(
				new InputStreamReader(stream));

		try {
			while ((builder = reader.readLine()) != null) {
				if (builder.contains(filter)) {
					result = new StringBuilder(builder);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("部分非热点Reader 读取 url流 出错:"+e.toString());
		} finally {
			try {
				stream.close();
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("部分非热点Reader 读取 url流 ,关闭 流 出错:"+e.toString());
			} finally {
				return result;
			}
		}
	}

	/*
	 * 由于页面的因素，需要跳过 前面的 几个标签块
	 * 这个是  遗留系统的
	 * 多余  可省去
	 */
	@SuppressWarnings("finally")
	public static StringBuilder getList(InputStream stream) {

		return getList(stream, 10);
	}

	
	
	
	/*
	 * 由于页面的因素，需要跳过 前面的 几个标签块
	 */
	@SuppressWarnings("finally")
	public static StringBuilder getList(InputStream stream,int space) {

		String str = null;
		StringBuilder builder = null;		
		if (stream == null)
			return null;

		BufferedReader reader = null;
		builder = new StringBuilder();
		try {
			reader = new BufferedReader(new InputStreamReader(stream));
			int k = 0;
			while ((str = reader.readLine()) != null) {
				if (k < space) {
					k++;
					continue;
				}
				builder.append(str);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("部分非热点Reader 读取 url流 出错:"+e.toString());
		}
		try {
			reader.close();
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("部分非热点Reader 读取 url流 ,关闭 流 出错:"+e.toString());
		}
		return builder;
	}
	
	
	
	
	
	
	
	/*
	 * 单纯的将Stream 转换 为   字符串
 	 */
	@SuppressWarnings("finally")
	public static StringBuilder getString(InputStream stream) {

		String str = null;
		StringBuilder builder = null;		
		if (stream == null)
			return null;

		BufferedReader reader = null;
		builder = new StringBuilder();
		try {
			reader = new BufferedReader(new InputStreamReader(stream));
			int k = 0;
			while ((str = reader.readLine()) != null) {
				
				builder.append(str);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("部分非热点Reader 读取 url流 出错:"+e.toString());
		}
		try {
			reader.close();
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("部分非热点Reader 读取 url流 ,关闭 流 出错:"+e.toString());
		}
		return builder;
	}

	
	

}
