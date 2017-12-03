/*
 *******************************************************************************
 * @FileName	JsoupDemo2.java
 * @package		jsoup
 * @author		jiangyao
 * @Date		2017年12月3日 下午4:52:30
 * @description 
 *******************************************************************************
 */
package jsoup;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 ******************************************
 * @author jiangyao  [2017年12月3日 下午4:52:30]
 ******************************************
 */
public class JsoupDemo2 {
	public static void main(String[] args) {
		try {
			String html = "<div><p>Lorem ipsum.</p></div>";
			Document doc1 = Jsoup.parse(html);
			Document doc2 = Jsoup.parseBodyFragment(html);
			System.out.println(doc2);
			System.out.println(doc1);
			File file = new File("C:/Users/Administrator/git/crawler/CrawlerDemo1/src/main/java/newFile/demo5.html");
			Document doc = Jsoup.parse(file, "utf-8");
//			System.out.println(doc);
			Element content = doc.getElementById("div1");
			System.out.println(content);
			Elements links = doc.getElementsByTag("a");
			for(Element link : links){
				String linkHref = link.attr("href");
				String lineText = link.text();
				System.out.println(linkHref);
				System.out.println(lineText);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
