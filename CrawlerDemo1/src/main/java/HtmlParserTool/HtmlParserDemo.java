/*
 *******************************************************************************
 * @FileName	HtmlParserDemo.java
 * @package		HtmlParserTool
 * @author		jiangyao
 * @Date		2017年11月29日 下午8:32:56
 * @description 
 *******************************************************************************
 */
package HtmlParserTool;

import java.util.HashSet;
import java.util.Set;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import newFile.LinkFilter;

/**
 * 单个URL上的所有链接
 ******************************************
 * @author jiangyao  [2017年11月29日 下午8:32:56]
 ******************************************
 */
public class HtmlParserDemo {
	
	public static Set<String> extracLinks(String url, LinkFilter filter){
		Set<String> links = new HashSet<String>();
		try{
			Parser parse = new Parser(url);
			//过滤<frame>标签的filter 提取frame中的src属性
			NodeFilter frameFilter = new NodeFilter() {
				public boolean accept(Node node) {
					if(node.getText().startsWith("frame src=")){
						return true;
					} else {
						return false;
					}
				}
			};
			//OrFilter来设置过滤<a>标签和<frame>标签
			OrFilter linkFilter = new OrFilter(new NodeClassFilter(
					LinkTag.class), frameFilter);
			//得到所有经过过滤的标签
			NodeList list = parse.extractAllNodesThatMatch(linkFilter);
			for(int i=0;i<list.size();i++){
				Node tag = list.elementAt(i);
				if(tag instanceof LinkTag){	//<a>标签
					LinkTag link = (LinkTag)tag;
					String linkUrl = link.getLink();//url
					if(filter.accept(linkUrl)){
						links.add(linkUrl);
					}
				}
				//<frame>标签
				else {
					//提取frame中的src属性的连接
					String frame = tag.getText();
					int start = frame.indexOf("src=");
					frame = frame.substring(start);
					int end = frame.indexOf(" ");
					if(end==-1){
						end = frame.indexOf(">");
					}
					String frameUrl = frame.substring(5, end-1);
					if(filter.accept(frameUrl)){
						links.add(frameUrl);
					}
				}
			}
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return links;
	}
	public static void main(String[] args) {
		Set<String> urlSet = extracLinks("http://www.travelsky.net/",  new LinkFilter() {
			
			public boolean accept(String url) {
				if(url.startsWith("http://www.travelsky.net/")){
					return true;
				}
				else {
					return false;
				}
			}
		});
		System.out.println(urlSet.size());
		for(String str : urlSet){
			System.err.println(str);
		}
	}
}
