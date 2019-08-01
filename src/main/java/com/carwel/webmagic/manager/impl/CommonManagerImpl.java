package com.carwel.webmagic.manager.impl;

import com.carwel.webmagic.dto.ChapterInfoDTO;
import com.carwel.webmagic.dto.Enum.MQResultTypeEnum;
import com.carwel.webmagic.manager.ChapterManager;
import com.carwel.webmagic.manager.CommonManager;
import com.carwel.webmagic.manager.SendMQManager;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class CommonManagerImpl implements CommonManager {

    @Autowired
    private ChapterManager chapterManager;
    @Autowired
    private SendMQManager sendMQManager;
    /**
     * 保存剑来章节信息
     *
     * @param chapterInfoDTO
     * @return
     */
    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int insertJianlaiChapter(ChapterInfoDTO chapterInfoDTO) {
        SendResult sendResult= chapterManager.insertJianlaiChapter(chapterInfoDTO);
        if (sendResult!=null&&sendResult.getSendStatus()== SendStatus.SEND_OK){
            //提交消息
            sendMQManager.endTransactionMessage(sendResult,MQResultTypeEnum.COMMIT_MESSAGE.getValue());
        }
        return  1;
    }
}
