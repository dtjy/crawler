/*
 *******************************************************************************
 * @FileName	JsoupGetUrl.java
 * @package		jsoup
 * @author		jiangyao
 * @Date		2017年12月5日 下午8:01:24
 * @description 
 *******************************************************************************
 */
package jsoup;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 百度贴吧，获取只看楼主图片
 ******************************************
 * @author jiangyao [2017年12月5日 下午8:01:24]
 ******************************************
 */
public class JsoupGetUrl {

	// url集合
	private static List<String> listUrl = new ArrayList<String>();

	/**
	 * 获取page链接
	 * [2017年12月5日 下午9:25:14]
	 * @param 初始链接
	 */
	public static void getUrl(String url) {
		try {
			Document doc = Jsoup.connect(url).get();
			Elements es = doc.select("a[href^=/p/4390833544?see_lz]");
			for (Element e : es) {
				if (listUrl.contains(e.attr("abs:href"))) {
					continue;
				}
				listUrl.add(e.attr("abs:href"));
				getUrl(e.attr("abs:href"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化链接顶点
	 * [2017年12月5日 下午9:25:52]
	 */
	public static void workGo() {
		String headUrl = "https://tieba.baidu.com/p/4390833544?see_lz=1&pn=1";
		//获取所有page的URL
		getUrl(headUrl);
		try {
			File file = new File("d:/img");
			if(!file.exists()){
				file.mkdir();
			}
			for(String pageUrl:listUrl){
				getImgUrl(pageUrl);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取图片的链接
	 * [2017年12月5日 下午9:26:59]
	 * @param link
	 */
	public static void getImgUrl(String link){
		try {
			Document doc = Jsoup.connect(link).get();
			Elements ele = doc.select("img[src~=\\.(jpe?g)]");
//			Elements ele = doc.select("img[src~=(?s)\\.(png|jpe?g)]");
			System.out.println(ele.size());
			for(Element e : ele){
				if(e.attr("class").equals("BDE_Image") && !e.parent().tagName().equals("a")){
					String imgurl = e.attr("abs:src");
					//BDE_Image
					down(imgurl);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据图片链接下载图片
	 * [2017年12月5日 下午9:26:25]
	 * @param imgUrl 图片链接
	 */
	public static void down(String imgUrl){
		try{
			URL url = null;
			URLConnection conn = null;
			InputStream  in = null;
			BufferedOutputStream out = null;
			String imgName = imgUrl.substring(imgUrl.lastIndexOf("/"), imgUrl.length());
			File imgFile = new File("d:/img"+File.separator+imgName);
			url = new URL(imgUrl);
			conn = url.openConnection();
			in = conn.getInputStream();
			out = new BufferedOutputStream(new FileOutputStream(imgFile));
			byte[] buf = new byte[1024];
			int size;
			while((size=in.read(buf))!=-1){
				out.write(buf,0,size);
			}
			out.flush();
			out.close();
			in.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		workGo();
	}
}
