/*
 *******************************************************************************
 * @FileName	JsoupDemo1.java
 * @package		jsoup
 * @author		jiangyao
 * @Date		2017年12月3日 下午6:02:27
 * @description 
 *******************************************************************************
 */
package jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 ******************************************
 * @author jiangyao [2017年12月3日 下午6:02:27]
 ******************************************
 */
public class JsoupDemo1 {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		String url = "http://jwzx.cqupt.congm.in/";
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
			Elements links = doc.select("a[href]");
			Elements media = doc.select("[src]");
			Elements imports = doc.select("link[href]");

			for (Element src : media) {
				if (src.tagName().equals("img")&&src.hasAttr("src")&& !list.contains(src.attr("abs:src"))) {
					list.add(src.attr("abs:src").trim());
				} 
			}
			for (Element link : links) {
				if (link.hasAttr("href")&& ! list.contains(link.attr("abs:href"))) {
					list.add(link.attr("abs:href").trim());
				} 
			}
//			System.out.println(list);
			for(String s: list){
				System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
