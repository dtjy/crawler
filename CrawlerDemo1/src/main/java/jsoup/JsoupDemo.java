/*
 *******************************************************************************
 * @FileName	JsoupDemo.java
 * @package		jsoup
 * @author		jiangyao
 * @Date		2017年12月3日 下午4:28:37
 * @description 
 *******************************************************************************
 */
package jsoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 ******************************************
 * @author jiangyao  [2017年12月3日 下午4:28:37]
 ******************************************
 */
public class JsoupDemo {
	public static void test1() {
		String html = "<html><head><title>First parse</title>"
				+ "</head><body><p id='hehe'><span>Parsed HTML into"
				+ " a doc.</span></p></body></html>";
		Document doc = Jsoup.parse(html);
		System.out.println(doc);
		System.out.println(doc.getElementById("hehe").text());
		System.err.println(doc.getElementById("hehe").html());
		System.out.println(doc.getElementById("hehe").root());
	}
	public static void main(String[] args) {
		test1();
		try {
			Document doc=Jsoup.connect("http://www.jb51.net")
								.data("query", "java")
								.userAgent("Chrome")
								.cookie("auth", "token")
								.timeout(3000)
								.post();
//			System.out.println(doc.getElementById("trigger").text());
//			System.out.println(doc.getElementById("trigger").html());
//			System.out.println(doc.getElementById("trigger").parent().tagName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
