package newFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class DemoGit {
	public static void main(String[] args) {
		String path = "https://www.baidu.com";
		try {
			URL pageURL = new URL(path);
			InputStream stream = pageURL.openStream();
			HttpClient httpClient = new HttpClient();
			GetMethod getMethod = new GetMethod(path);
			int statusCode = httpClient.executeMethod(getMethod);
			System.out.println(statusCode);
//			System.out.println(getMethod.getResponseBodyAsString());
			PostMethod postMethod  = new PostMethod(path);
//			httpClient.executeMethod(postMethod);
//			System.out.println(postMethod.getResponseBodyAsString());
			NameValuePair[] postData = new NameValuePair[2];
			postData[0] = new NameValuePair("武器", "枪");
			postData[1] = new NameValuePair("什么枪", "不知道");
			postMethod.addParameters(postData);
			System.out.println(httpClient.executeMethod(postMethod));
			System.out.println(postMethod.getResponseBodyAsString());
			getMethod.releaseConnection();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
