package org.buaa.ztang.model;

import com.alibaba.fastjson.JSONObject;
import org.buaa.ztang.utils.TimeUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by qixiang on 1/5/17.
 */
public class ProfileQuota extends Quota {

    public static final String domain_name = "userProfile";

    private Map<String,Quota> quotas;

    public ProfileQuota() {
        setDomain(domain_name);
        quotas = new HashMap<String, Quota>();
    }

    @Override
    public JSONObject dumpData()
    {
        setMeasure_time(TimeUtils.currentTime());
        JSONObject jsonObject = new JSONObject();
        for ( Map.Entry<String,Quota> entry : quotas.entrySet() ) {
            jsonObject.put(entry.getKey(),entry.getValue().dumpData());
        }
        return jsonObject;
    }

    @Override
    public int parseData(JSONObject dataJSON)
    {
        for ( String domain : dataJSON.keySet() ) {
            Quota quota = Quota.GetQuota(domain);
            if (quota != null ) {
                quota.parseData(dataJSON.getJSONObject(domain));
                quotas.put(domain,quota);
            }
        }
        return quotas.size();
    }

    @Override
    public void mergeData(Quota other) {
        Quota quota;

        if ( other.getDomain().compareToIgnoreCase(DietQuota.domain_name) == 0 ) {
            quota = quotas.get(DietListQuota.domain_name);
            if ( quota == null ) { quota = new DietListQuota(); }
        } else if ( other.getDomain().compareToIgnoreCase(ExerQuota.domain_name) == 0 ) {
            quota = quotas.get(ExerListQuota.domain_name);
            if ( quota == null ) quota = new ExerListQuota();
        } else {
            quota = quotas.get(other.getDomain());
            if ( quota == null ) quota = other;
        }

        quota.mergeData(other);
        if ( !quotas.containsKey(quota.getDomain()) ) quotas.put(quota.getDomain(),quota);
    }

    public <T extends Quota> T getQuota(String domain) {
        if ( quotas.containsKey(domain) ) return (T) quotas.get(domain);
        else return null;
    }

    public JSONObject getQuotaData(String domain) {
        if ( quotas.containsKey(domain) ) return quotas.get(domain).dumpData();
        else return null;
    }
}
