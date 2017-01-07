package org.buaa.ztang.service.iface;

import com.alibaba.fastjson.JSONObject;
import org.buaa.ztang.model.ProfileQuota;

/**
 * Created by qixiang on 1/7/17.
 */
public interface SuggestionService {

    JSONObject suggestion(String domain, ProfileQuota profileQuota) throws Exception;

}
