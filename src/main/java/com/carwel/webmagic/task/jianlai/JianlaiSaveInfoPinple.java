package com.carwel.webmagic.task.jianlai;

import com.alibaba.fastjson.JSON;
import com.carwel.webmagic.dto.ChapterInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
@Component
@Slf4j
public class JianlaiSaveInfoPinple implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {
         if (resultItems!=null){
             Integer start=(Integer)resultItems.getRequest().getExtras().get("start");
             ChapterInfoDTO chapterInfoDTO=resultItems.get("chapterInfoDTO");
             if (start==null||start.intValue()<=0){
                 log.info("更新最新章节：result={}", JSON.toJSONString(chapterInfoDTO));
             }else {
                 log.info("爬取章节：result={}", JSON.toJSONString(chapterInfoDTO));
             }
         }

    }
}
