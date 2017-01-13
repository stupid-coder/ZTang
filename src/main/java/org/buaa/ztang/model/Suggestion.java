package org.buaa.ztang.model;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by qixiang on 1/14/17.
 */
public class Suggestion extends Quota {

    public static final String domain_name = "suggestion";

    private JSONObject suggestion;
    private int quota_id;
    private String quota_domain;
    private JSONObject quota_data;

    public Suggestion() {
        suggestion = null;
        quota_id = -1;
        quota_domain = null;
        quota_data = null;
    }

    public Suggestion(ProfileQuota profileQuota, String domain) {
        suggestion = null;
        quota_id = profileQuota.getQuota(domain).getId();
        quota_domain = domain;
        quota_data = profileQuota.getQuotaData(domain);
    }

    @Override
    public int parseData(JSONObject data) {
        if ( data.containsKey("suggestion") && data.containsKey("quota_id")
                && data.containsKey("quota_domain") && data.containsKey("quota_data")) {
            suggestion = data.getJSONObject("suggestion");
            quota_id = data.getIntValue("quota_id");
            quota_domain = data.getString("quota_domain");
            quota_data = data.getJSONObject("quota_data");
            return 1;
        }
        return 0;
    }

    @Override
    public JSONObject dumpData() {
        JSONObject data = new JSONObject();
        data.put("suggestion",suggestion);
        data.put("quota_id",quota_id);
        data.put("quota_domain",quota_domain);
        data.put("quota_data",quota_data);
        return data;
    }

    @Override
    public void mergeData(Quota other) {
        if ( other instanceof Suggestion ) {
            Suggestion otherQuota = (Suggestion) other;
            suggestion = otherQuota.suggestion;
            quota_id = otherQuota.quota_id;
            quota_domain = otherQuota.quota_domain;
            quota_data = otherQuota.quota_data;
        }
    }

    public JSONObject getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(JSONObject suggestion) {
        this.suggestion = suggestion;
    }

    public void addSuggestion(String key, Object value) {
        if ( suggestion == null ) suggestion = new JSONObject();
        suggestion.put(key,value);
    }

    public int getQuota_id() {
        return quota_id;
    }

    public void setQuota_id(int quota_id) {
        this.quota_id = quota_id;
    }

    public String getQuota_domain() {
        return quota_domain;
    }

    public void setQuota_domain(String quota_domain) {
        this.quota_domain = quota_domain;
    }

    public JSONObject getQuota_data() {
        return quota_data;
    }

    public void setQuota_data(JSONObject quota_data) {
        this.quota_data = quota_data;
    }
}
