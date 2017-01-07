package org.buaa.ztang.dao.iface;

import org.buaa.ztang.model.PressureQuota;

import java.util.List;

/**
 * Created by qixiang on 1/5/17.
 */
public interface PressureQuotaDao {

    List<PressureQuota> get(int uid) throws Exception;

    int add(PressureQuota pressureQuota) throws Exception;

}
