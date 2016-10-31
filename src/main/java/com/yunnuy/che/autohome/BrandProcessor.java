package com.yunnuy.che.autohome;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.yunnuy.che.model.Brand;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * get all brands from autohome
 * 
 * @author feitian124@qq.com
 */
public class BrandProcessor implements PageProcessor {
	private String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36";
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setUserAgent(userAgent);

	/**
	 * when user access the page, only brands whose name start with A is included.
	 * others are get through ajax
	 * 
	 * <div class="uibox" id="boxA">...</div>
	 * <div class="uibox" id="boxB" style="display:none">...</div>
	 */
	@Override
	public void process(Page page) {
		Document doc = page.getHtml().getDocument();
		
		for (char c = 'A'; c <= 'Z'; c++) {
			String key = "box" + c;
			Element boxDiv = doc.getElementById(key);
			if( boxDiv == null) {
				System.out.println(key + ": null");
			} else if(boxDiv.attr("style").contains("display:none")) {
				System.out.println(key + ": " + boxDiv.attr("style"));
			} else {
				System.out.println(boxDiv.toString());
				Elements dls = boxDiv.select("dl");
				for(Element dl: dls){
					String name = dl.select("dt > div > a").text();
					String logo = dl.select("dt > a > img").attr("src");
					String url  = dl.select("dt > a").attr("href");
					Brand brand = new Brand(name, logo, url);
					System.out.println(brand);
				}
			}
		}
	}

	@Override
	public Site getSite() {
		return site;
	}
	
    public static void main(String[] args) {
        Spider.create(new BrandProcessor())
                .addUrl("http://www.autohome.com.cn/car")
                .thread(1)
                .run();
    }
}
