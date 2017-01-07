package org.buaa.ztang.algorithm.iface;

import com.alibaba.fastjson.JSONObject;
import org.buaa.ztang.model.ProfileQuota;


/**
 * Created by qixiang on 1/7/17.
 */
public interface QuotaAlgorithm {

    JSONObject algo(ProfileQuota profileQuota);

}
