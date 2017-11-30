package newFile;

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

public class HTMLParseTool {
	//获取一个网站上的连接、、链接，filter用来过滤链接
	public static Set<String> extracLinks(String url, LinkFilter filter){
		Set<String> links = new HashSet<String>();
		try{
			Parser parse = new Parser(url);
			parse.setEncoding("gbk2312");
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
//					String frame = tag.getText(); //不会显示  < 符号   与  > 符号
					String frame = tag.toHtml();
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
}










