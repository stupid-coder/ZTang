package org.buaa.ztang.algorithm.iface;

import org.buaa.ztang.model.ProfileQuota;
import org.buaa.ztang.model.Suggestion;


/**
 * Created by qixiang on 1/7/17.
 */
public interface QuotaAlgorithm {

    Suggestion algo(ProfileQuota profileQuota);

}
