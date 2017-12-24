package jsoup;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupDemoThread {
	public static Object lock = new Object();
	public static List<String> pageList = new LinkedList<String>();
	public static List<String> imgList = new LinkedList<String>();
//	public static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
	public static BlockingQueue queue = new LinkedBlockingQueue();
	public static ThreadPoolExecutor fixedThreadPool = new ThreadPoolExecutor(3, 6, 1, TimeUnit.SECONDS, queue);  
	public static ExecutorService fixedThreadPool1 = Executors.newFixedThreadPool(6);
	public static final int SIZE = 190;
	public volatile boolean o = false;
	/**
	 * 获取page链接 [2017年12月5日 下午9:25:14]
	 * 
	 * @param 初始链接
	 */
	public static void getPageUrl(String url) {
		try {
			Document doc = Jsoup.connect(url).get();
			Elements es = doc.select("a[href^=/p/4390833544?see_lz]");
			for (Element e : es) {
				fixedThreadPool1.execute(new PageUrlThread(e));
			}
//			while(true){
//				if(pageList.size()>=180){
//					fixedThreadPool1.shutdown();
//				}
//				if(fixedThreadPool1.isTerminated()){
//					System.err.println("page线程结束+++++++++++++++++++");
//					break;
//				}
//			}
			for (String pageUrl : pageList) {
				fixedThreadPool.execute(new ImgDownThread(pageUrl));
			}
			fixedThreadPool.shutdown();
			while(true){    
				if(fixedThreadPool.isTerminated()){
					System.err.println("img线程结束----------------------");
					break;
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		JsoupDemoThread demoThread = new JsoupDemoThread();
		String startUrl = "https://tieba.baidu.com/p/4390833544?see_lz=1&pn=2";
		getPageUrl(startUrl);
		
//		fixedThreadPool1.shutdown();
//		fixedThreadPool.shutdown();
		
		

	}
}
class PageUrlThread implements Runnable {
	
	private Element element;
	
	public PageUrlThread(Element element) {
		this.element = element;
	}
	
	public void run() {
		if (!JsoupDemoThread.pageList.contains(this.element.attr("abs:href"))) {
			System.out.println(Thread.currentThread().getId()+"---"+this.element.attr("abs:href"));
			JsoupDemoThread.pageList.add(this.element.attr("abs:href"));
			JsoupDemoThread.getPageUrl(this.element.attr("abs:href"));
		}
//		System.out.println("page:"+JsoupDemoThread.pageList.size());
	}
	
}
class ImgDownThread implements Runnable {

	private String url;

	public ImgDownThread(String url) {
		this.url = url;
	}

	public void getUrl(String pageUrl) {
		try {
			Document doc = Jsoup.connect(pageUrl).get();
			Elements ele = doc.select("img[src~=\\.(jpe?g)]");
			for (Element e : ele) {
				if (e.attr("class").equals("BDE_Image") && !e.parent().tagName().equals("a")) {
					String imgUrl = e.attr("abs:src");
					if (!JsoupDemoThread.imgList.contains(imgUrl)) {
						JsoupDemoThread.imgList.add(imgUrl);
						System.out.println(Thread.currentThread().getName() + "--" + imgUrl);
						URL url = null;
						URLConnection conn = null;
						InputStream in = null;
						BufferedOutputStream out = null;
						String imgName = imgUrl.substring(imgUrl.lastIndexOf("/"), imgUrl.length());
						File imgFile = new File("d:/img" + File.separator + imgName);
						url = new URL(imgUrl);
						conn = url.openConnection();
						in = conn.getInputStream();
						out = new BufferedOutputStream(new FileOutputStream(imgFile));
						byte[] buf = new byte[1024];
						int size;
						while ((size = in.read(buf)) != -1) {
							out.write(buf, 0, size);
						}
						out.flush();
						out.close();
						in.close();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void run() {
		getUrl(this.url);
//		System.out.println("img:"+JsoupDemoThread.imgList.size());
	}

}
