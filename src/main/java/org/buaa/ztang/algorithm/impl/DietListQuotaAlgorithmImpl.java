package org.buaa.ztang.algorithm.impl;

import org.buaa.ztang.algorithm.iface.QuotaAlgorithm;
import org.buaa.ztang.model.DietListQuota;
import org.buaa.ztang.model.DietQuota;
import org.buaa.ztang.model.ProfileQuota;
import org.buaa.ztang.model.Suggestion;
import org.buaa.ztang.utils.TimeUtils;

import java.sql.Timestamp;

/**
 * Created by qixiang on 1/14/17.
 */
public class DietListQuotaAlgorithmImpl implements QuotaAlgorithm {

    private String getDiet7dSuggestion(DietListQuota dietListQuota)
    {
        Timestamp before7d = TimeUtils.currentTime(-7L*TimeUtils.MS_IN_DAY);
        int days = 0;
        for (DietQuota dietQuota : dietListQuota.getDietQuotaList() )
        {
            if ( dietQuota.getDietDate().compareTo(before7d) > 0 ) days +=  1;
        }
        if ( days < 4 ) return "合理饮食控制，能够帮助改善糖尿病患者的血糖、血压、血脂和体重等指标，改善生活质量和临床结局，节约医疗费用。请在医生指导下合理控制饮食";
        else return null;
    }

    @Override
    public Suggestion algo(ProfileQuota profileQuota) {

        Suggestion suggestion = new Suggestion(profileQuota, DietQuota.domain_name);

        DietListQuota dietListQuota = profileQuota.getQuota(DietListQuota.domain_name);

        suggestion.addSuggestion("diet7d",getDiet7dSuggestion(dietListQuota));

        return suggestion;
    }
}
