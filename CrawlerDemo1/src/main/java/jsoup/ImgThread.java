package jsoup;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ImgThread implements Runnable{
	
	private Element e;
	public ImgThread(Element e) {
		this.e = e;
	}
	
	public void getUrl(Element e) throws IOException{
		String url = e.attr("abs:href");
		Document doc = Jsoup.connect(url).get();
		Elements es = doc.select("a[href^=/p/4390833544?see_lz]");
		for (Element ele : es) {
			if(!ImgThreadMain.urlList.contains(ele.attr("abs:href"))){
				synchronized (ImgThread.class) {
					ImgThreadMain.urlList.add(url);
				}
				System.out.println(Thread.currentThread().getName()+"---"+e.attr("abs:href"));
				ImgThreadMain.fixedThreadPool.execute(new ImgThread(ele));
			}
		}
		
	}
	
	public void run() {
		try {
			getUrl(this.e);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Element getE() {
		return e;
	}

	public void setE(Element e) {
		this.e = e;
	}
	
	
}
