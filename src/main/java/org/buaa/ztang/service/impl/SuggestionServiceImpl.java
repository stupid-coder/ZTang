package org.buaa.ztang.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.buaa.ztang.algorithm.impl.QuotaAlgorithmImpl;
import org.buaa.ztang.dao.iface.SuggestionDao;
import org.buaa.ztang.model.ProfileQuota;
import org.buaa.ztang.model.Suggestion;
import org.buaa.ztang.service.iface.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by qixiang on 1/7/17.
 */
public class SuggestionServiceImpl implements SuggestionService {

    @Autowired
    SuggestionDao suggestionDao;

    @Override
    public Suggestion get(int quota_id) throws Exception {
        return suggestionDao.get(quota_id);
    }

    @Override
    public int add(Suggestion suggestion) throws Exception {
        return suggestionDao.add(suggestion);
    }

    @Override
    public Suggestion suggestion(String domain, ProfileQuota profileQuota) throws Exception {
        return QuotaAlgorithmImpl.getAlgorithm(domain).algo(profileQuota);
    }

}
