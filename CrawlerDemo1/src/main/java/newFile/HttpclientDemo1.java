package newFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class HttpclientDemo1 {
	private static HttpClient httpclient = new HttpClient();
	
	static {
		//设置代理服务器IP和端口
//		httpclient.getHostConfiguration().setProxy("192.168.0.1", 9527);
	}
	public static boolean downloadPage(String path) throws HttpException, IOException{
		InputStream input = null;
		OutputStream output = null;
		//post
		PostMethod postMethod = new PostMethod(path);
		//设置post方法参数
		NameValuePair[] postData = new NameValuePair[2];
		postData[0] = new NameValuePair("name", "dt");
		postData[1] = new NameValuePair("password", "123456");
		postMethod.addParameters(postData);
		//返回状态码
		int code = httpclient.executeMethod(postMethod);
		if(code == HttpStatus.SC_OK){
			input = postMethod.getResponseBodyAsStream();
			//得到文件名
			String fileName = path.substring(path.lastIndexOf("/")+1);
			//获得文件输出流
			output = new FileOutputStream(fileName);
			
			//输出到文件
			int tempByte = -1;
			while((tempByte=input.read())>0){
				output.write(tempByte);
			}
			//关闭流
			if(input!=null){
				input.close();
			}
			if(output!=null){
				output.close();
			}
			return true;
		}
		return false;
	}
	public static void main(String[] args) {
		
		try {
			HttpclientDemo1.downloadPage("https://www.baidu.com");
		} catch (HttpException e) {
			System.err.println("HttpException");
		} catch (IOException e) {
			System.err.println("IOException");
		}
		
		
		
	}
}












































