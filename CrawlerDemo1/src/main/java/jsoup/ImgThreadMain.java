/*
 *******************************************************************************
 * @FileName	ImgThreadMain.java
 * @package		jsoup
 * @author		jiangyao
 * @Date		2017年12月7日 下午8:17:31
 * @description 
 *******************************************************************************
 */
package jsoup;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 ******************************************
 * @author jiangyao  [2017年12月7日 下午8:17:31]
 ******************************************
 */
public class ImgThreadMain {
	
	public static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
	public static List<String> urlList = new LinkedList<String>();
	
	public void getUrlMain(String startUrl){
		try {
			Document doc = Jsoup.connect(startUrl).get();
			Elements es = doc.select("a[href^=/p/4390833544?see_lz]");
			for (Element e : es) {
				fixedThreadPool.execute(new ImgThread(e));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		ImgThreadMain imgThreadMain = new ImgThreadMain();
		imgThreadMain.getUrlMain("https://tieba.baidu.com/p/4390833544?see_lz=1&pn=1");
	}
}
