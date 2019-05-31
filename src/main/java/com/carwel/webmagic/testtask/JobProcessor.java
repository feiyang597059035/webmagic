package com.carwel.webmagic.testtask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.scheduler.Scheduler;

public class JobProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
        //xpath
        page.putField("title",page.getHtml().xpath("head//meta[@property='og:novel:book_name']//@content"));
//css选择器
        //jousp
        Document doc = Jsoup.parse(page.getHtml().toString());
        String title=doc.title();
        page.putField("title2",title);
        //css选择器
        page.putField("div", page.getHtml().css("head meta[property=og:novel:book_name]","content"));

    }

    @Override
    public Site getSite() {
        return site;
    }
    private Site site = Site.me()
            .setCharset("utf8")    //设置编码
            .setTimeOut(10000)   //设置超时时间，单位是ms毫秒
            .setRetrySleepTime(3000)  //设置重试的间隔时间
            .setSleepTime(3)  ;    //设置重试次数


    //主函数，执行爬虫
  /*  public static void main(String[] args) {
        Spider spider = Spider.create(new JobProcessor())
                .addUrl("http://www.jianlaixiaoshuo.com/")  //设置爬取数据的页面
                .addPipeline(new SavePinple())
                .thread(5)
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(10000000)));
        //设置布隆去重过滤器，指定最多对1000万数据进行去重操作


        Scheduler scheduler = spider.getScheduler();

        //执行爬虫
        spider.run();
    }*/

}
