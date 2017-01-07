package org.buaa.ztang.service.impl;

import org.buaa.ztang.dao.iface.GlucoseQuotaDao;
import org.buaa.ztang.model.GlucoseQuota;
import org.buaa.ztang.service.iface.GlucoseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by qixiang on 1/4/17.
 */
public class GlucoseServiceImpl implements GlucoseService {

    @Autowired
    GlucoseQuotaDao glucoseQuotaDao;

    @Override
    public List<GlucoseQuota> get(int uid) throws Exception {
        return glucoseQuotaDao.get(uid);
    }

    @Override
    public int add(GlucoseQuota glucose) throws Exception {
        glucose.setDomain(GlucoseQuota.domain_name);
        return glucoseQuotaDao.add(glucose);
    }
}
