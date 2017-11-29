package newFile;

import java.util.HashSet;
import java.util.Set;

/**
 * 已访问过的URL队列类
 ******************************************
 * @author jiangyao  [2017年11月29日 下午7:51:07]
 ******************************************
 */
public class LinkQueue {
	
	//已访问的URL集合
	private static Set visitedURL = new HashSet();
	
	//待访问的URL集合
	private static Queue unVisitedURL = new Queue();
	
	/**
	 * 获得URL队列
	 * [2017年11月29日 下午7:56:29]
	 * @return URL队列
	 */
	public static Queue getUnVisitedURL(){
		return unVisitedURL;
	}
	
	/**
	 * 添加到已经访问过的URL队列中
	 * [2017年11月29日 下午7:57:22]
	 * @param url 已访问过的URL
	 */
	public static void addVisitedURL(String url) {
		visitedURL.add(url);
	}
	
	/**
	 * 移除访问过的URL
	 * [2017年11月29日 下午7:58:07]
	 * @param url 已访问过的URL
	 */
	public static void removeVisitedURL(String url){
		visitedURL.remove(url);
	}
	
	/**
	 * 未访问过的URL出队列
	 * [2017年11月29日 下午7:58:37]
	 * @return  未访问过的URL
	 */
	public static Object unVisitedURLDeQueue(){
		return unVisitedURL.deQueue();
	}
	
	/**
	 * 保证每个URL只被访问过一次，加入未访问的队列
	 * [2017年11月29日 下午7:59:00]
	 * @param url
	 */
	public static void addUnvisitedURL(String url){
		if(null != url && "".equals(url.trim())
				&& !visitedURL.contains(url)
				&& !unVisitedURL.contains(url)){
			//加入未访问队列
			unVisitedURL.enQueue(url);
		}
	}
	
	/**
	 * 获得已访问的URL的数目
	 * [2017年11月29日 下午8:00:25]
	 * @return 获得的已访问url数量
	 */
	public static int getVisitedURLNum() {
		return visitedURL.size();
	}
	
	/**
	 * 判断未访问的队列是否为空
	 * [2017年11月29日 下午8:00:50]
	 * @return 判断结果
	 */
	public static boolean unVisitedURLIsEmpty(){
		return unVisitedURL.isQueueEmpty();
	}
}



























