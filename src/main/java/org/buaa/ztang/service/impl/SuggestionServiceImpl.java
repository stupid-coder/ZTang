package org.buaa.ztang.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.buaa.ztang.algorithm.impl.QuotaAlgorithmImpl;
import org.buaa.ztang.model.ProfileQuota;
import org.buaa.ztang.service.iface.QuotaService;
import org.buaa.ztang.service.iface.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by qixiang on 1/7/17.
 */
public class SuggestionServiceImpl implements SuggestionService {

    @Autowired
    QuotaService quotaService;

    @Override
    public JSONObject suggestion(String domain, ProfileQuota profileQuota) throws Exception {
        return QuotaAlgorithmImpl.getAlgorithm(domain).algo(profileQuota);
    }

}
