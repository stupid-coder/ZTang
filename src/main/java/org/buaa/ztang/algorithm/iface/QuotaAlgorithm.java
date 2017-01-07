package org.buaa.ztang.algorithm.iface;

import com.alibaba.fastjson.JSONObject;
import org.buaa.ztang.algorithm.impl.GlucoseQuotaAlgorithmImpl;
import org.buaa.ztang.model.GlucoseQuota;

/**
 * Created by qixiang on 1/7/17.
 */
public interface QuotaAlgorithm {

    String algo(JSONObject jsonObject);

}
