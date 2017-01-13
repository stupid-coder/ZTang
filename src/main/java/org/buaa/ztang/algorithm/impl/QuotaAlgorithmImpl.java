package org.buaa.ztang.algorithm.impl;

import org.buaa.ztang.algorithm.iface.QuotaAlgorithm;
import org.buaa.ztang.model.*;

/**
 * Created by qixiang on 1/7/17.
 */
public class QuotaAlgorithmImpl implements QuotaAlgorithm {

    @Override
    public Suggestion algo(ProfileQuota profileQuota) {
        return null;
    }

    public static QuotaAlgorithm getAlgorithm(String domain) {
        if ( domain.compareToIgnoreCase(GlucoseQuota.domain_name) == 0 )
            return new GlucoseQuotaAlgorithmImpl();
        if ( domain.compareToIgnoreCase(PressureQuota.domain_name) == 0 )
            return new PressureQuotaAlgorithmImpl();
        if ( domain.compareToIgnoreCase(DietListQuota.domain_name) == 0 )
            return new DietListQuotaAlgorithmImpl();
        if ( domain.compareToIgnoreCase(ExerListQuota.domain_name) == 0 )
            return new ExerListQuotaAlgorithmImpl();
        return null;
    }

}
