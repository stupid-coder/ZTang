package org.buaa.ztang.model;

import com.alibaba.fastjson.JSONObject;
import org.buaa.ztang.algorithm.impl.QuotaAlgorithmImpl;
import org.buaa.ztang.utils.TimeUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by qixiang on 1/5/17.
 */
public class ProfileQuota extends Quota {

    public static final String domain_name = "userProfile";

    private Map<String,Quota> quotas;
    private Map<String,Suggestion> suggestions;

    public ProfileQuota() {
        setDomain(domain_name);
        quotas = new HashMap<String, Quota>();
        suggestions = new HashMap<String, Suggestion>();
    }

    @Override
    public JSONObject dumpData()
    {
        setMeasure_time(TimeUtils.currentTime(0L));
        JSONObject jsonObject = new JSONObject();
        JSONObject quotaObjects = new JSONObject();
        for ( Map.Entry<String,Quota> entry : quotas.entrySet() ) {
            quotaObjects.put(entry.getKey(),entry.getValue().dumpData());
        }
        jsonObject.put("quotas",quotaObjects);

        JSONObject suggestionObjects = new JSONObject();
        for ( Map.Entry<String,Suggestion> entry : suggestions.entrySet() ) {
            suggestionObjects.put(entry.getKey(),entry.getValue().dumpData());
        }
        jsonObject.put("suggestions",suggestionObjects);

        return jsonObject;
    }

    @Override
    public int parseData(JSONObject dataJSON)
    {
        JSONObject quotaObjects = dataJSON.getJSONObject("quotas");
        for ( String domain : quotaObjects.keySet() ) {
            Quota quota = Quota.GetQuota(domain);
            if (quota != null ) {
                quota.parseData(quotaObjects.getJSONObject(domain));
                quotas.put(domain,quota);
            }
        }

        JSONObject suggestionObjects = dataJSON.getJSONObject("suggestions");
        for ( String domain : suggestionObjects.keySet() ) {
            Suggestion suggestion = new Suggestion();
            suggestion.parseData(suggestionObjects.getJSONObject(domain));
            suggestions.put(domain,suggestion);
        }

        return quotas.size()+suggestions.size();
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

        Suggestion suggestion;
        if ( other.getDomain().compareToIgnoreCase(DietQuota.domain_name) == 0 ) {
            suggestion = QuotaAlgorithmImpl.getAlgorithm(DietListQuota.domain_name).algo(this);
        } else if ( other.getDomain().compareToIgnoreCase(ExerQuota.domain_name) == 0 ) {
            suggestion = QuotaAlgorithmImpl.getAlgorithm(ExerListQuota.domain_name).algo(this);
        } else {
            suggestion = QuotaAlgorithmImpl.getAlgorithm(other.getDomain()).algo(this);
        }

        suggestion.setQuota_id(other.getId());
        suggestions.put(other.getDomain(),suggestion);
    }

    public <T extends Quota> T getQuota(String domain) {
        if ( quotas.containsKey(domain) ) return (T) quotas.get(domain);
        else return null;
    }

    public JSONObject getQuotaData(String domain) {
        if ( quotas.containsKey(domain) ) return quotas.get(domain).dumpData();
        else return null;
    }

    public Suggestion getSuggestion(String domain) {
        if ( quotas.containsKey(domain) && suggestions.containsKey(domain) ) {
            if ( domain.compareToIgnoreCase(DietListQuota.domain_name) == 0
                    || domain.compareToIgnoreCase(ExerListQuota.domain_name) == 0)
                return QuotaAlgorithmImpl.getAlgorithm(domain).algo(this);
            else return suggestions.get(domain);
        }
        return null;
    }
}
