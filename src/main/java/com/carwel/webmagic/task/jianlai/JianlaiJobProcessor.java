package com.carwel.webmagic.task.jianlai;

import com.carwel.webmagic.dto.ChapterInfoDTO;
import com.carwel.webmagic.dto.SpiderInfoDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.scheduler.Scheduler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 剑来小说 爬取job
 */
@Component
public class JianlaiJobProcessor implements PageProcessor {

    @Override
    public void process(Page page) {


        Request request=page.getRequest();
        Map<String,Object>paramMap= request.getExtras();
        int num=0;
        if (paramMap!=null&&paramMap.get("num")!=null){
             num=(Integer) paramMap.get("num");
        }
        int originalNum=0;
        if (paramMap!=null&&paramMap.get("originalNum")!=null){
            originalNum=(Integer) paramMap.get("originalNum");
        }
        int start=0;
        if (paramMap!=null&&paramMap.get("start")!=null){
            start=(Integer) paramMap.get("start");
        }
        Integer contentId=0;
        if (paramMap!=null&&paramMap.get("contentId")!=null){
            contentId=(Integer) paramMap.get("contentId");
        }

        //如果是start =0 表示第一次进来 在目录页
        if (start==0){
            String latestUpdateTime= page.getHtml().xpath("head//meta[@property='og:novel:update_time']//@content").toString();
            String latestChapterName=page.getHtml().xpath("head//meta[@property='og:novel:latest_chapter_name" +
                    "']//@content").toString();
            ChapterInfoDTO chapterInfoDTO=new ChapterInfoDTO();
            chapterInfoDTO.setChapterName(latestChapterName);
            chapterInfoDTO.setUpdateTime(latestUpdateTime);
            chapterInfoDTO.setContentId(contentId);
            page.putField("chapterInfoDTO",chapterInfoDTO);
            //获取所有的链接
          List<String> subLinkList= page.getHtml().xpath("dl[@class='chapterlist']//dd//a//@href").all();
          if (!CollectionUtils.isEmpty(subLinkList)&&num<subLinkList.size()){
              int i=num;
              for (;i<subLinkList.size();i++){
                  String subLink=subLinkList.get(i);
                  String subLinkUrl=page.getUrl()+subLink;
                  Request targetRequest=new Request();
                  targetRequest.setUrl(subLinkUrl);
                  Map<String,Object> subParamMap=new HashMap<String, Object>();
                  BeanUtils.copyProperties(paramMap,subParamMap);
                  subParamMap.put("start",1);
                  subParamMap.put("num",i+1);
                  subParamMap.put("contentId",contentId);
                  subParamMap.put("originalNum",originalNum);
                  targetRequest.setExtras(subParamMap);
                  page.addTargetRequest(targetRequest);
                  break;

              }
          }

        }else {
            //获取页面详情
            String subTitle=page.getHtml().xpath("div[@id=BookCon]//h1/text()").toString();
            List<String> subContextList=page.getHtml().xpath("div[@id=BookText]//p").all();
            StringBuffer stringBuffer=new StringBuffer();
            for (int j=0;j<subContextList.size();j++){
                     stringBuffer.append(subContextList.get(j));
            }

            ChapterInfoDTO chapterInfoDTO=new ChapterInfoDTO();
            chapterInfoDTO.setChapterName(subTitle);
            chapterInfoDTO.setChapterContext(stringBuffer.toString());
            chapterInfoDTO.setContentId(contentId);
            chapterInfoDTO.setChapterNum(num-originalNum);
            page.putField("chapterInfoDTO",chapterInfoDTO);
        }
    }

    private Site site = Site.me()
            .setCharset("UTF-8")//设置编码
            .setTimeOut(10 * 1000)//设置超时时间
            .setRetrySleepTime(3000)//设置重试的间隔时间
            .setRetryTimes(3);//设置重试的次数

    @Override
    public Site getSite() {
        return site;
    }



    public void startJianlaiJob(SpiderInfoDTO spiderInfoDTO){
        Request request=new Request();
        Map<String,Object> map=new HashMap<>();
        map.put("num",spiderInfoDTO.getNum());
        map.put("contentId",spiderInfoDTO.getContentId());
        map.put("originalNum",spiderInfoDTO.getOriginalNum());
        request.setExtras(map);
        request.setUrl(spiderInfoDTO.getUrl());
        Spider spider =
                Spider.create(new JianlaiJobProcessor())
                        .addPipeline(new JianlaiSaveInfoPinple())
                        .addRequest(request)
                        .thread(5)
                        .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(10000000)));
        //设置布隆去重过滤器，指定最多对1000万数据进行去重操作
        Scheduler scheduler = spider.getScheduler();

        //执行爬虫
        spider.run();
    }


    /*public static void main(String[] args){
        SpiderInfoDTO spiderInfoDTO=new SpiderInfoDTO();
        spiderInfoDTO.setUrl("http://www.jianlaixiaoshuo.com");
        spiderInfoDTO.setPageNum(0);
        spiderInfoDTO.setNum(0);
        Request request=new Request();
        Map<String,Object> map=new HashMap<>();
        map.put("num",spiderInfoDTO.getNum());
        request.setExtras(map);
        request.setUrl(spiderInfoDTO.getUrl());

        Spider spider =
                Spider.create(new JianlaiJobProcessor())
                        .addPipeline(new JianlaiSaveInfoPinple())
                        .addRequest(request)
                        .thread(5)
                        .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(10000000)));
        //设置布隆去重过滤器，指定最多对1000万数据进行去重操作
        Scheduler scheduler = spider.getScheduler();

        //执行爬虫
        spider.run();
    }
*/



}
