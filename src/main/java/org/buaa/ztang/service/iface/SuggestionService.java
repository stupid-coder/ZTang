package org.buaa.ztang.service.iface;

import org.buaa.ztang.model.ProfileQuota;
import org.buaa.ztang.model.Suggestion;

/**
 * Created by qixiang on 1/7/17.
 */
public interface SuggestionService {

    Suggestion get(int quota_id) throws Exception;

    int add(Suggestion suggestion) throws Exception;

    Suggestion suggestion(String domain, ProfileQuota profileQuota) throws Exception;

}
