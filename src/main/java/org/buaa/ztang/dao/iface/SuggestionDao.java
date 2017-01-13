package org.buaa.ztang.dao.iface;

import org.buaa.ztang.model.Suggestion;

/**
 * Created by qixiang on 1/14/17.
 */
public interface SuggestionDao {

    Suggestion get(int quota_id) throws Exception;

    int add(Suggestion suggestion) throws Exception;

    int update(Suggestion suggestion) throws Exception;
}
