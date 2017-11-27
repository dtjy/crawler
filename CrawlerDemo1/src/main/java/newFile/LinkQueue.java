package newFile;

import java.util.HashSet;
import java.util.Set;

public class LinkQueue {
	//已访问的URL集合
	private static Set visitedURL = new HashSet();
	
	//待访问的URL集合
	private static Queue unVisitedURL = new Queue();
	
	//获得URL队列
	public static Queue getUnVisitedURL(){
		return unVisitedURL;
	}
	
	//添加到访问过的URL队列中
	public static void addVisitedURL(String url) {
		visitedURL.add(url);
	}
	
	//移除访问过的URL
	public static void removeVisitedURL(String url){
		visitedURL.remove(url);
	}
	
	//未访问过的URL出队列
	public static Object unVisitedURLDeQueue(){
		return unVisitedURL.deQueue();
	}
	
	//保证每个URL只被访问过一次
	public static void addUnvisitedURL(String url){
		if(null != url && "".equals(url.trim())
				&& !visitedURL.contains(url)
				&& !unVisitedURL.contains(url)){
			//加入未访问队列
			unVisitedURL.enQueue(url);
		}
	}
	
	//获得已访问的URL的数目
	public static int getVisitedURLNum() {
		return visitedURL.size();
	}
	
	//判断未访问的队列是否为空
	public static boolean unVisitedURLIsEmpty(){
		return unVisitedURL.isQueueEmpty();
	}
}



























