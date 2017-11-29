/*
 *******************************************************************************
 * @FileName	HtmlParserDemo2.java
 * @package		HtmlParserTool
 * @author		jiangyao
 * @Date		2017年11月29日 下午9:10:46
 * @description 
 *******************************************************************************
 */
package HtmlParserTool;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.lexer.Page;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
 * htmlparser学习
 ******************************************
 * @author jiangyao  [2017年11月29日 下午9:10:46]
 ******************************************
 */
public class HtmlParserDemo2 {
	public static void main(String[] args) {
		try {
			String url = "http://www.travelsky.net/";
			Parser parser = new Parser(url);
			StringBuffer sb = new StringBuffer();
			NodeFilter filter = new HasAttributeFilter("class","menuyiji");
			NodeList nodes = parser.extractAllNodesThatMatch(filter);
			System.err.println(nodes.size());
			Node first = nodes.elementAt(0);
//			System.out.println(first.getText());
			NodeList firstChild = first.getChildren();
			for(int j=0;j<firstChild.size();j++){
				sb.append(firstChild.elementAt(j).toPlainTextString().trim());
			}
			System.out.println(first.getStartPosition());
			System.out.println(first.getEndPosition());
			Page page = first.getPage();
			System.out.println(page);
//			for(int i=0;i<nodes.size();i++){
//				Node node = nodes.elementAt(i);
//				sb.append(node.toPlainTextString().trim());
//				
////				System.out.println(node.toPlainTextString());
////				System.out.println(node.toHtml());
//				NodeList cnodes = node.getChildren();
//				for(int j=0;j<cnodes.size();j++){
//					Node cnode = cnodes.elementAt(j);
//					sb.append(cnode.toPlainTextString().trim());
//				}
//			}
//			System.err.println(sb);
		} catch (ParserException e) {
			System.err.println("解析错误");
			System.err.println(e.getMessage());
		}
	}
}
