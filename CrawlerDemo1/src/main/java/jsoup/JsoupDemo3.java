/*
 *******************************************************************************
 * @FileName	JsoupDemo3.java
 * @package		jsoup
 * @author		jiangyao
 * @Date		2017年12月3日 下午5:07:28
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
 * @author jiangyao  [2017年12月3日 下午5:07:28]
 ******************************************
 */
public class JsoupDemo3 {
	public static void main(String[] args) {
		try {
//			Document doc = Jsoup.connect("http://jwzx.cqupt.congm.in/").get();
//			System.out.println(doc.title());
			Document doc1 = Jsoup.parse(
					new File("C:/Users/Administrator/git/crawler/CrawlerDemo1/src/main/java/newFile/demo5.html"),"utf-8");
			Element link = doc1.select("a").first();//查找第一个a元素
			System.out.println(link.attr("href"));
			System.out.println(link.text());
			System.out.println(link.outerHtml());
			System.out.println(link.parent().html());
			System.out.println("-------------------");
			Elements links = doc1.select("a[href]");
			System.out.println(links);
			Elements links1 = doc1.select("div > a");
			System.out.println(links1);
			Elements e = doc1.select("#div1");
			System.out.println(e);
			Elements e1 = doc1.select(".col");
			System.out.println(e1);
			System.out.println("-------------------");
			Elements e2 = doc1.select("[class]");
			System.out.println(e2);
			System.out.println("-------------------");
			Elements e3 = doc1.select("[class$=l]");
			System.out.println(e3);
			//			Element e = doc1.getElementById("div1");
//			System.out.println(e.html());
//			e.append("good job!!!");
//			System.out.println(e.html());
//			System.err.println("111"+e.data());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
