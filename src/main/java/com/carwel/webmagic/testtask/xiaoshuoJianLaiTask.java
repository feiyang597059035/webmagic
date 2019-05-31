package com.carwel.webmagic.testtask;

import com.carwel.webmagic.util.HttpClientUtils;
import com.carwel.webmagic.util.HttpUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.carwel.webmagic.util.HttpClientUtils.httpGet;

@Component
public class xiaoshuoJianLaiTask {
@Autowired
private HttpUtils httpUtils;


    private static final ObjectMapper MAPPER =  new ObjectMapper();


    //当下载任务完成后，间隔多长时间进行下一次的任务。
    public void itemTask() throws Exception {
        String url="http://www.jianlaixiaoshuo.com/";
        String html= httpUtils.doGetHtml(url);
        if (!StringUtils.isEmpty(html)){
            //解析html获取Document
            Document doc = Jsoup.parse(html);
            String title=doc.title();
            String updateTime=doc.head().select("meta[property=og:novel:update_time]").attr("content");
            String latestChapterName=doc.head().select("meta[property=og:novel:latest_chapter_name]").attr("content");
            String latestChapterUrl=doc.head().select("meta[property=og:novel:latest_chapter_url]").attr("content");
           /* //获取某个元素
            Elements dl=doc.select("dl.chapterlist");*/
            Elements dd=doc.select("dl.chapterlist dd");
            //遍历 dd
            //获取迭代器
            for (int i=49;i<dd.size();i++){
                Element element = dd.get(i);
                List<Node> childNodes=element.childNodes();
                String attributes= childNodes.get(0).attr("href");
                String subUrl=url+attributes;
                String subhtml=httpGet(subUrl,null);
                Document subDoc = Jsoup.parse(subhtml);
                String subTitle=subDoc.select("#BookCon h1").text();
                String subContext=subDoc.select("#BookText ").html();
            }

        }

    }


}
