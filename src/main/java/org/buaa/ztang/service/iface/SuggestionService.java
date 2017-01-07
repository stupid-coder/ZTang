package org.buaa.ztang.service.iface;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by qixiang on 1/7/17.
 */
public interface SuggestionService {

    String suggestion(String domain, JSONObject data) throws Exception;

}
