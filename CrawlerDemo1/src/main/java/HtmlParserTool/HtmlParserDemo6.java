/*
 *******************************************************************************
 * @FileName	HtmlParserDemo6.java
 * @package		HtmlParserTool
 * @author		jiangyao
 * @Date		2017年12月3日 下午12:52:16
 * @description 
 *******************************************************************************
 */
package HtmlParserTool;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.ScriptTag;
import org.htmlparser.tags.SelectTag;
import org.htmlparser.tags.StyleTag;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
 ******************************************
 * @author jiangyao  [2017年12月3日 下午12:52:16]
 ******************************************
 */
public class HtmlParserDemo6 {
	public static void main(String[] args) {
		Parser parser;
		try {
			parser = new Parser("C:/Users/Administrator/git/crawler/CrawlerDemo1/src/main/java/newFile/demo5.html");
			parser.setEncoding("UTF-8");
			NodeFilter filter = new TagNameFilter("body");
			NodeList nodeList = parser.extractAllNodesThatMatch(filter);
			System.out.println(nodeList.size());
			List list = extracHtml(nodeList.elementAt(0));
//			System.out.println(list);
//			while(list.iterator().hasNext()){
//				System.out.println(list.iterator().next().);
//			}
			/*NodeVisitor visitor = new NodeVisitor() {
				public void visitTag(Tag tag){
					System.err.println("tag name is："+tag.getTagName()+
							"\n Class is:"+tag.getClass());
					System.out.println(tag.getText());
				}
			};
			parser.visitAllNodesWith(visitor);	*/
					
			/*NodeFilter  filter = new StringFilter("dt");
			NodeList list = parser.extractAllNodesThatMatch(filter);
			for(int i=0;i<list.size();i++){
				System.err.println(i+"--------"+list.elementAt(i).toPlainTextString());
				System.err.println(i+"--------"+list.elementAt(i).getText());
				System.err.println(i+"--------"+list.elementAt(i).toHtml());
			}*/
		} catch (ParserException e) {
			System.err.println("解析失败");
		}
	}
	
	public static List extracHtml(Node nodeP){
		NodeList nodeList = nodeP.getChildren();
		boolean b1 = false;
		if(nodeList==null || nodeList.size()==0){
			return null;
		}
		if((nodeP instanceof Div) || (nodeP instanceof TextNode)){
			b1 = true;
		}
		ArrayList tableList = new ArrayList();
		try{
			for(NodeIterator e=nodeList.elements();e.hasMoreNodes();){
				Node node = e.nextNode();
				if(node instanceof LinkTag){
					tableList.add(node);
				}
				else if(node instanceof ScriptTag ||
						node instanceof StyleTag || node instanceof SelectTag){
					
				}
				else if(node instanceof TextNode){
					if(node.getText().trim().length()>0){
						tableList.add(node);
					}
				}
				else {
					List tempList = extracHtml(node);
					if((tempList!=null)&&(tempList.size()>0)){
						Iterator ti = tempList.iterator();
						while (ti.hasNext()) {
							tableList.add(ti.next());
							
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
//		if((tableList!=null)&&(tableList.size()>0)){
//			if(b1){
//				TableContext tc = new TableContext();
//			}
//		}
		return tableList;
	}
}






