package com.carwel.webmagic.request;

import com.carwel.webmagic.dto.BaseDomain;
import lombok.Data;

@Data
public class CreateUserInterestRequest extends BaseDomain {
    /**内容名称**/
    private String  contentName;
    /**抓取地址**/
    private String  url;
    /**分类**/
    private Integer categoryType;
    /**用户id**/
    private String userId;
    /**类容id**/
    private Integer contentId;
    /**用户收藏id**/
    private Long userInterestId;

}
