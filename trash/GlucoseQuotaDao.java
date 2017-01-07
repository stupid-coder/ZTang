package org.buaa.ztang.dao.iface;

import org.buaa.ztang.model.GlucoseQuota;

import java.util.List;

/**
 * Created by qixiang on 1/4/17.
 */
public interface GlucoseQuotaDao {

    List<GlucoseQuota> get(int uid) throws Exception;

    int add(GlucoseQuota glucose) throws Exception;
}
