package org.buaa.ztang.dao.impl;

import org.buaa.ztang.dao.iface.SuggestionDao;
import org.buaa.ztang.model.Quota;
import org.buaa.ztang.model.Suggestion;
import org.buaa.ztang.service.iface.QuotaService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by qixiang on 1/14/17.
 */
public class SuggestionDaoImpl implements SuggestionDao {

    @Autowired
    QuotaService quotaService;

    @Override
    public Suggestion get(int quota_id) throws Exception {
        Quota quota = quotaService.get(quota_id);
        if ( quota == null ) return null;

        List<Suggestion> suggestionList = quotaService.get(quota.getUid(),Suggestion.domain_name,null, Suggestion.class);

        for ( Suggestion suggestion : suggestionList ) {
            if ( suggestion.getQuota_id() == quota_id )
                return suggestion;
        }

        return null;
    }

    @Override
    public int add(Suggestion suggestion) throws Exception {
        return quotaService.add(suggestion);
    }

    @Override
    public int update(Suggestion suggestion) throws Exception {
        return quotaService.update(suggestion);
    }

}
