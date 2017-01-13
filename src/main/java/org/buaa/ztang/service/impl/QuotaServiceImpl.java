package org.buaa.ztang.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.buaa.ztang.dao.iface.QuotaDao;
import org.buaa.ztang.dao.iface.SuggestionDao;
import org.buaa.ztang.model.*;
import org.buaa.ztang.service.iface.QuotaService;
import org.buaa.ztang.service.iface.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qixiang on 1/5/17.
 */
public class QuotaServiceImpl implements QuotaService {

    @Autowired
    QuotaDao quotaDao;

    @Autowired
    SuggestionService suggestionService;

    @Override
    public Quota get(int id) throws Exception {
        return quotaDao.get(id);
    }

    @Override
    public List<Quota> get(int uid, String domain, String status) throws Exception {

        List<Quota> ret = new ArrayList<Quota>();

        if ( domain.compareToIgnoreCase(GlucoseQuota.domain_name) == 0 )
            ret.addAll(get(uid,domain,status,GlucoseQuota.class));
        else if ( domain.compareToIgnoreCase(PressureQuota.domain_name) == 0 )
            ret.addAll(get(uid,domain,status,PressureQuota.class));
        else if ( domain.compareToIgnoreCase(WeightQuota.domain_name) == 0 )
            ret.addAll(get(uid,domain,status,WeightQuota.class));
        else if ( domain.compareToIgnoreCase(DietQuota.domain_name) == 0)
            ret.addAll(get(uid,domain,status,DietQuota.class));
        else if ( domain.compareToIgnoreCase(DietListQuota.domain_name) == 0)
            ret.addAll(get(uid,domain,status,DietListQuota.class));
        else if ( domain.compareToIgnoreCase(ExerQuota.domain_name) == 0)
            ret.addAll(get(uid,domain,status,ExerQuota.class));
        else if ( domain.compareToIgnoreCase(ExerListQuota.domain_name) == 0)
            ret.addAll(get(uid,domain,status,ExerListQuota.class));
        else if ( domain.compareToIgnoreCase(ProfileQuota.domain_name) == 0 )
            ret.addAll(get(uid,domain,status,ProfileQuota.class));
        else if ( domain.compareToIgnoreCase(Suggestion.domain_name) == 0 )
            ret.addAll(get(uid,domain,status,Suggestion.class));

        return ret;
    }

    @Override
    public <T extends Quota> List<T> get(int uid, String domain, String status, Class<T> clazz) throws Exception {
        List<Quota> quotaList = quotaDao.get(uid,domain,status);

        List<T> tList = new ArrayList<T>();

        for ( Quota quota : quotaList )
        {
            T t = clazz.newInstance();
            t.fromQuota(quota);
            tList.add(t);
        }

        return tList;
    }

    @Override
    public JSONArray getData(int uid, String domain, String status) throws Exception {
        List<Quota> quotaList = quotaDao.get(uid,domain,status);

        JSONArray jsonArray = new JSONArray();
        for ( Quota quota : quotaList ) {
            JSONObject jsonObject = quota.dumpData();
            jsonObject.put("id",quota.getId());
            jsonArray.add(jsonObject);
        }

        return jsonArray;
    }

    @Override
    public int add(Quota quota) throws Exception {
        quota.toQuota();
        return quotaDao.add(quota);
    }

    @Override
    public int update(Quota quota) throws Exception {
        quota.toQuota();
        return quotaDao.update(quota);
    }

    @Override
    public int profileUpdate(Quota quota) throws Exception {
        int ret = 0;
        List<ProfileQuota> profileQuotaList = get(quota.getUid(),ProfileQuota.domain_name,null,ProfileQuota.class);
        if (profileQuotaList.size() == 0) {
            ProfileQuota profileQuota = new ProfileQuota();
            profileQuota.setUid(quota.getUid());
            profileQuota.mergeData(quota);
            ret = suggestionService.add(profileQuota.getSuggestion(quota.getDomain())) & add(profileQuota);
        } else {
            ProfileQuota profileQuota = profileQuotaList.get(0);
            profileQuota.mergeData(quota);
            ret = suggestionService.add(profileQuota.getSuggestion(quota.getDomain())) & update(profileQuota);
        }

        return ret;
    }
}
