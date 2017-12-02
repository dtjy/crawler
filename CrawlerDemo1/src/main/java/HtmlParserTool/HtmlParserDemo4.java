/*
 *******************************************************************************
 * @FileName	HtmlParserDemo4.java
 * @package		HtmlParserTool
 * @author		jiangyao
 * @Date		2017年12月2日 上午10:29:05
 * @description 
 *******************************************************************************
 */
package HtmlParserTool;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
 * HtmlParser 学习
 ******************************************
 * @author jiangyao  [2017年12月2日 上午10:29:05]
 ******************************************
 */
public class HtmlParserDemo4 {
	
	public static void main(String[] args) {
		try {
			Parser parser = new Parser("C:/Users/Administrator/git/crawler/CrawlerDemo1/src/main/java/newFile/demo3.html");
			parser.setEncoding("UTF-8");
//			for(NodeIterator i=parser.elements(); i.hasMoreNodes();){
//				Node node = i.nextNode();
//				System.out.println(node.getText());
//			}
			NodeList nodeList = parser.extractAllNodesThatMatch(new NodeFilter() {
				/**
				 * [2017年12月2日 上午11:14:01]
				 */
				private static final long serialVersionUID = 1L;

				public boolean accept(Node node) {
					return true;
				}
			});
			System.err.println(nodeList.size());
			for(int i=0;i<nodeList.size();i++){
				System.out.println(i+":----"+nodeList.elementAt(i));
			}
//			Parser parser2 = new Parser("C:/Users/Administrator/git/crawler/CrawlerDemo1/src/main/java/newFile/demo3.html");;
//			NodeFilter filter = new NodeClassFilter(LinkTag.class);
//			NodeFilter filter1 = new TagNameFilter("frame");
//			NodeFilter filter2 = new OrFilter(filter, filter1);
//			NodeList nodes = parser2.extractAllNodesThatMatch(filter2);
//			System.err.println(nodes.size());
		} catch (ParserException e) {
			System.err.println("解析错误");
		}
	}
}
