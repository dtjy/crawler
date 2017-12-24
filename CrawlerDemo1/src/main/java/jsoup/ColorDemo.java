package jsoup;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ColorDemo {
	public static void main(String[] args) {
//		}
		try {
			Document doc = Jsoup.connect("https://tieba.baidu.com/p/4390833544?see_lz=1&pn=3").get();
			Elements ele = doc.select("img[src~=\\.(jpe?g)]");
//			Elements ele = doc.select("img[src~=(?s)\\.(png|jpe?g)]");
			System.out.println(ele.size());
			for(Element e : ele){
				if(e.attr("class").equals("BDE_Image") && !e.parent().tagName().equals("a")){
					String imgurl = e.attr("abs:src");
					//BDE_Image
					System.out.println(e.parent().tagName()+"---"+imgurl);
					donw(imgurl);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	private static void donw(String imgUrl) {
		try {
			File file = new File("d:/img");
			if(!file.exists()){
				file.mkdir();
			}
			String imgName = imgUrl.substring(imgUrl.lastIndexOf("/"), imgUrl.length());
			File imgFile = new File("d:/img"+File.separator+imgName);
			URL url = new URL(imgUrl);
			URLConnection conn = url.openConnection();
			InputStream in = conn.getInputStream();
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(imgFile));
			byte[] buf = new byte[1024];
			int size;
			while((size=in.read(buf))!=-1){
				out.write(buf,0,size);
			}
			out.flush();
			out.close();
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
