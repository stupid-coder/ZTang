package org.buaa.ztang.algorithm.impl;

import com.alibaba.fastjson.JSONObject;
import org.buaa.ztang.algorithm.iface.QuotaAlgorithm;
import org.buaa.ztang.model.GlucoseQuota;

/**
 * Created by qixiang on 1/7/17.
 */
public class GlucoseQuotaAlgorithmImpl implements QuotaAlgorithm {

    @Override
    public String algo(JSONObject jsonObject) {
        GlucoseQuota quota = new GlucoseQuota();
        quota.parseData(jsonObject);

        return "";
    }
}
