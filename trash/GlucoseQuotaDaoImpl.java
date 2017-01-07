package org.buaa.ztang.dao.impl;

import org.buaa.ztang.dao.iface.GlucoseQuotaDao;
import org.buaa.ztang.dao.iface.QuotaDao;
import org.buaa.ztang.model.GlucoseQuota;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by qixiang on 1/4/17.
 */
public class GlucoseQuotaDaoImpl implements GlucoseQuotaDao {

    @Autowired
    QuotaDao quotaDao;

    @Override
    public List<GlucoseQuota> get(int uid) throws Exception {
       return quotaDao.get(uid, GlucoseQuota.domain_name, null, GlucoseQuota.class);
    }

    @Override
    public int add(GlucoseQuota glucose) throws Exception {
        return quotaDao.add(glucose);
    }
}