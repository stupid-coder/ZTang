package org.buaa.ztang.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by qixiang on 1/6/17.
 */
public class ExerListQuota extends Quota {

    public static final String domain_name = "exerList";

    private List<ExerQuota> exerQuotaList;

    public ExerListQuota()
    {
        setDomain(domain_name);
        exerQuotaList = new ArrayList<ExerQuota>();
    }

    @Override
    public int parseData(JSONObject data) {
        JSONArray jsonArray = data.getJSONArray("exerQuotaList");
        for (int i = 0; i < jsonArray.size(); ++ i) {
            ExerQuota exerQuota = new ExerQuota();
            exerQuota.parseData(jsonArray.getJSONObject(i));
            exerQuotaList.add(exerQuota);
        }
        return exerQuotaList.size();
    }

    @Override
    public JSONObject dumpData() {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for ( int i = 0; i < exerQuotaList.size() && i <= 7; ++i ) {
            jsonArray.add(exerQuotaList.get(i).dumpData());
        }
        jsonObject.put("exerQuotaList",exerQuotaList);
        return jsonObject;
    }

    @Override
    public void mergeData(Quota other) {
        if ( other instanceof  ExerQuota ) {
            ExerQuota otherQuota = (ExerQuota) other;
            exerQuotaList.add(otherQuota);
            Collections.sort(exerQuotaList);
        }
    }

    public List<ExerQuota> getExerQuotaList() {
        return exerQuotaList;
    }

    public void setExerQuotaList(List<ExerQuota> exerQuotaList) {
        this.exerQuotaList = exerQuotaList;
    }
}
