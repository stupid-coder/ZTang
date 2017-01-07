package org.buaa.ztang.dao.iface;

import org.buaa.ztang.model.Quota;

import java.util.List;

/**
 * Created by qixiang on 1/4/17.
 */
public interface QuotaDao {

    Quota get(int id) throws Exception;

    List<Quota> get(int uid, String domain, String status) throws Exception;

    int add(Quota quota) throws Exception;

    int update(Quota quota) throws Exception;
}
