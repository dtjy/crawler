package newFile;

import java.util.Set;

public class MyCrawler {
	/**
	 * 使用种子初始化URL队列
	 */
	private void initCrawlerWithSeeds(String[] seeds){
		for(int i=0;i<seeds.length;i++){
			LinkQueue.addUnvisitedURL(seeds[i]);
		}
	}
	
	/**
	 * 抓取过程
	 */
	public void crawling(String[] seeds){
		//定义过滤器
		LinkFilter filter = new LinkFilter() {
			
			public boolean accept(String url) {
				if(url.startsWith("http://www.travelsky.net/")){
					return true;
				}
				else {
					return false;
				}
			}
		};
		//初始化URL队列
		initCrawlerWithSeeds(seeds);
		//循环条件，带抓取的连接不空且网页不多于1000
		while(!LinkQueue.unVisitedURLIsEmpty()&&LinkQueue.getVisitedURLNum()<=1000){
			//队头URL出队列
			String visitUrl = (String) LinkQueue.unVisitedURLDeQueue();
			if(visitUrl==null){
				continue;
			}
			System.out.println(visitUrl);
			DownLoadFile downLoader = new DownLoadFile();
			//下载网页
			downLoader.downloadFile(visitUrl);
			//该URL放入月访问的URL中
			LinkQueue.addVisitedURL(visitUrl);
			//提取出下载网页中的URL
			Set<String> links = HTMLParseTool.extracLinks(visitUrl, filter);
			//新的未访问的URL入列
			for(String link:links){
				LinkQueue.addUnvisitedURL(link);
			}
		}
	}
	public static void main(String[] args) {
		MyCrawler crawler = new MyCrawler();
		crawler.crawling(new String[]{"http://www.travelsky.net/"});
	}
	
	
}




















