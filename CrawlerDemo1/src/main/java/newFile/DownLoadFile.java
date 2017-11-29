package newFile;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class DownLoadFile {
	
	/**
	 * 根据URL生成文件名，去除非文件名字符
	 * [2017年11月29日 下午8:02:57]
	 * @param url 链接
	 * @param contentType 类型
	 * @return 文件名
	 */
	public String getFileNameByUrl(String url, String contentType) {
		//移除 http://
		url = url.substring(7);
		//text/html类型
		if(contentType.indexOf("html")!=-1){
			url = url.replace("[\\?/:*|<>\"]", "_")+".html";
			return url;
		}
		//其他 如application/pdf
		else{
			return url.replace("*[\\?/:*|<>\"]", "_")+"·"+
					contentType.substring(contentType.lastIndexOf("/")+1);
		}
	}
	
	/**
	 * 保存文字数组到本地文件
	 * [2017年11月29日 下午8:06:48]
	 * @param data 
	 * @param filePath 
	 */
	private void saveToLocal(byte[] data, String filePath){
		try{
			DataOutputStream out = new DataOutputStream(new 
					FileOutputStream(new File(filePath)));
			for(int i=0;i<data.length;i++){
				out.write(i);
			}
				out.flush();
				out.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/** 下载URL指向的网页
	 * [2017年11月29日 下午8:07:03]
	 * @param url 链接
	 * @return 
	 */
	public String downloadFile(String url) {
		String filePath= null;
		//生成httpClient对象并设置参数
		HttpClient httpClient = new HttpClient();
		//设置HTTP连接超时5s
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);;
		
		//生成GetMethod对象并设置参数
		GetMethod getMethod = new GetMethod();
		//设置get请求超时5s
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
		//设置请求重试处理
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		
		//执行HTTP GET请求
		try{
			int statusCode = httpClient.executeMethod(getMethod);
			//判断访问状态码
			if(statusCode != HttpStatus.SC_OK){
				System.err.println("Method Failed:"+getMethod.getStatusLine());
				filePath = null;
			}
			//读取字节数组
			byte[] responseBody = getMethod.getResponseBody();
			//根据网页url生成保存的文件名
			filePath = "temp\\"+getFileNameByUrl(url, getMethod.getResponseHeader(
					"Content-Type").getValue());
			saveToLocal(responseBody, filePath);
			System.out.println(filePath);
		}catch(IOException e){
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();
		}
		return filePath;
	}
}





