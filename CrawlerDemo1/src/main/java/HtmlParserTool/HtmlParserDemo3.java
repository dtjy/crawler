/*
 *******************************************************************************
 * @FileName	HtmlParserDemo3.java
 * @package		HtmlParserTool
 * @author		jiangyao
 * @Date		2017年11月30日 下午7:47:05
 * @description 
 *******************************************************************************
 */
package HtmlParserTool;

import java.util.LinkedList;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
 * HtmlParser学习
 ******************************************
 * @author jiangyao  [2017年11月30日 下午7:47:05]
 ******************************************
 */
public class HtmlParserDemo3 {
	
	public static void main(String[] args) {
		try {
			Parser parser = new Parser("C:/Users/Administrator/git/crawler/CrawlerDemo1/src/main/java/newFile/demo3.html");
//			NodeFilter filter = new HasAttributeFilter("class");
//			NodeList nodes = parser.extractAllNodesThatMatch(filter);
//			System.out.println(nodes.size());
			NodeFilter filter2 = new NodeClassFilter(LinkTag.class);
			NodeFilter filter3 = new NodeFilter() {
				/**
				 * [2017年11月30日 下午9:07:45]
				 */
				private static final long serialVersionUID = 1L;

				public boolean accept(Node node) {
					if(node.getText().startsWith("frame src")){
						return true;
					} else {
						return false;
					}
				}
			};
			NodeFilter filter4 = new NodeFilter() {
				/**
				 * [2017年11月30日 下午9:08:07]
				 */
				private static final long serialVersionUID = 1L;

				public boolean accept(Node node) {
					if(node.getText().startsWith("iframe src")){
						return true;
					} else {
						return false;
					}
				}
			};
			LinkedList<String> urlList = new LinkedList<String>();
//			NodeFilter filter = new OrFilter(filter2,filter3);
			NodeFilter filter = new OrFilter(new OrFilter(filter2,filter3),filter4);
			NodeList nodeList = parser.extractAllNodesThatMatch(filter);
			System.out.println("node个数："+nodeList.size());
			for(int i=0;i<nodeList.size();i++){
				Node n = nodeList.elementAt(i);
//				String url = linkTag.getLink();
//				urlList.add(((LinkTag) nodeList.elementAt(i)).getLink());
				if(n instanceof LinkTag){
					//a
					urlList.add(((LinkTag) n).getLink());
				}else {
					// frame,iframe
					String str = n.getText();
					System.err.println(n.toHtml());
					int start = str.indexOf("src=");
					String startStr = str.substring(start);
					String url = startStr.substring(5);
					int end = url.indexOf("\"");
					url = url.substring(0,end);
//					System.out.println(startStr.substring(5, end));
					urlList.add(url.trim());
//					
				}
			}
			System.out.println(urlList);
//			System.out.println(nodeList.elementAt(0).getText());
		} catch (ParserException e) {
			System.err.println("url解析错误");
		}
		
	}
}	
