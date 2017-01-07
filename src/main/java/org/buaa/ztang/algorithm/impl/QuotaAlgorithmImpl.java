package org.buaa.ztang.algorithm.impl;

import com.alibaba.fastjson.JSONObject;
import org.buaa.ztang.algorithm.iface.QuotaAlgorithm;
import org.buaa.ztang.model.GlucoseQuota;
import org.buaa.ztang.model.ProfileQuota;

/**
 * Created by qixiang on 1/7/17.
 */
public class QuotaAlgorithmImpl implements QuotaAlgorithm {

    @Override
    public JSONObject algo(ProfileQuota profileQuota) {
        return null;
    }


    public static QuotaAlgorithm getAlgorithm(String domain) {
        if ( domain.compareToIgnoreCase(GlucoseQuota.domain_name) == 0 )
            return new GlucoseQuotaAlgorithmImpl();
        else return null;
    }

}
