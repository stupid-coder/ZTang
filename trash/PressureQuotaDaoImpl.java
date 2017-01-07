package org.buaa.ztang.dao.impl;

import org.buaa.ztang.dao.iface.PressureQuotaDao;
import org.buaa.ztang.dao.iface.QuotaDao;
import org.buaa.ztang.model.PressureQuota;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by qixiang on 1/5/17.
 */
public class PressureQuotaDaoImpl implements PressureQuotaDao {

    @Autowired
    QuotaDao quotaDao;

    @Override
    public List<PressureQuota> get(int uid) throws Exception {
        return quotaDao.get(uid, PressureQuota.domain_name, null, PressureQuota.class);
    }

    @Override
    public int add(PressureQuota pressureQuota) throws Exception {
        return quotaDao.add(pressureQuota);
    }
}
