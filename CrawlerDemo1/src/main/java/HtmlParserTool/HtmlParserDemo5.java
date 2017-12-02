/*
 *******************************************************************************
 * @FileName	HtmlParserDemo5.java
 * @package		HtmlParserTool
 * @author		jiangyao
 * @Date		2017年12月2日 上午10:59:14
 * @description 
 *******************************************************************************
 */
package HtmlParserTool;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
 * Node对象具体细节,函数区别与输出
 ******************************************
 * @author jiangyao  [2017年12月2日 上午10:59:14]
 ******************************************
 */
public class HtmlParserDemo5 {
	public static void main(String[] args) {
		try {
			Parser parser = new Parser("C:/Users/Administrator/git/crawler/CrawlerDemo1/src/main/java/newFile/demo5html.html");
			parser.setEncoding("UTF-8");
			NodeList nodeList = parser.extractAllNodesThatMatch(new NodeFilter() {
				/**
				 * [2017年12月2日 上午11:11:57]
				 */
				private static final long serialVersionUID = 1L;

				public boolean accept(Node node) {
					return true;
				}
			});
			for(int i=0; i<nodeList.size(); i++){
				System.err.println(i+"--------------"+nodeList.elementAt(i).getText());
			}
			System.err.println("--------------"+nodeList.elementAt(9).getText());
			System.err.println("--------------"+nodeList.elementAt(9).toHtml());
			System.err.println("--------------"+nodeList.elementAt(9).toPlainTextString());
			System.err.println("--------------"+nodeList.elementAt(8).getText());
			System.err.println("--------------"+nodeList.elementAt(8).toHtml());
			System.err.println("--------------"+nodeList.elementAt(8).toPlainTextString());
			System.err.println("--------------"+nodeList.elementAt(10).getText());
			System.err.println("--------------"+nodeList.elementAt(10).toHtml());
			System.err.println("--------------"+nodeList.elementAt(10).toPlainTextString());
			
		} catch (ParserException e) {
			System.err.println("解析异常");
		}
	}
}
