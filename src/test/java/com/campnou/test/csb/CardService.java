package com.campnou.test.csb;

import com.alibaba.fastjson.JSON;
import com.campnou.test.util.CsbPostUtil;
import com.campnou.test.util.FormatUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static com.alibaba.fastjson.JSON.toJSON;

/**
 * @Author:LiTongjing
 * @Description:
 * @Date:Create by 上午11:15 2018/1/4
 */
public class CardService extends BaseService {
    @Test
    public void queryByAcount() {
        Map<String, String> params = Maps.newHashMap();
        params.put("seqNo","17213584");
        params.put("code","0");
        System.out.println(params);
        String apiName = "com.intime.card.client.service.wallet.CardBaseService.getAvailableAmount";
        System.out.println(FormatUtil.formatJson(CsbPostUtil.cardCsbPost(apiName, params, test)));
    }
}
