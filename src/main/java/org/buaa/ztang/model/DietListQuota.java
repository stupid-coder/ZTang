package org.buaa.ztang.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by qixiang on 1/6/17.
 */
public class DietListQuota extends Quota {
    public static final String domain_name = "dietList";

    private List<DietQuota> dietQuotaList;

    public DietListQuota()
    {
        setDomain(domain_name);
        dietQuotaList = new ArrayList<DietQuota>();
    }

    @Override
    public int parseData(JSONObject data) {
        JSONArray jsonArray = data.getJSONArray("dietQuotaList");
        for ( int i = 0; i < jsonArray.size(); ++ i) {
            DietQuota dietQuota = new DietQuota();
            dietQuota.parseData(jsonArray.getJSONObject(i));
            dietQuotaList.add(dietQuota);
        }
        return dietQuotaList.size();
    }

    @Override
    public JSONObject dumpData() {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for ( int i = 0; i < dietQuotaList.size() && i <= 7 ; ++ i) {
            jsonArray.add(dietQuotaList.get(i).dumpData());
        }
        jsonObject.put("dietQuotaList",jsonArray);
        return jsonObject;
    }

    @Override
    public void mergeData(Quota other) {
        if ( other instanceof DietQuota ) {
            DietQuota otherQuota = (DietQuota) other;
            dietQuotaList.add(otherQuota);
            Collections.sort(dietQuotaList);
        }
    }

    public List<DietQuota> getDietQuotaList() {
        return dietQuotaList;
    }

    public void setDietQuotaList(List<DietQuota> dietQuotaList) {
        this.dietQuotaList = dietQuotaList;
    }
}
