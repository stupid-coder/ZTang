package org.buaa.ztang.algorithm.impl;

import org.buaa.ztang.algorithm.iface.QuotaAlgorithm;
import org.buaa.ztang.model.*;
import org.buaa.ztang.utils.TimeUtils;

import java.sql.Timestamp;

/**
 * Created by qixiang on 1/14/17.
 */
public class ExerListQuotaAlgorithmImpl implements QuotaAlgorithm {

    private String getExer7d30Suggestion(ExerListQuota exerListQuota)
    {
        Timestamp before7d = TimeUtils.currentTime(-7L * TimeUtils.MS_IN_DAY );

        int days = 0;

        for (ExerQuota exerQuota : exerListQuota.getExerQuotaList() ) {
            if ( exerQuota.getExerDate().compareTo(before7d) > 0 )
                ++ days;
        }

        if ( days < 3 ) return "运动频率一般以1周3~7天、每次30分钟为宜，请您咨询医生制定合理的运动计划。";
        else return null;
    }

    @Override
    public Suggestion algo(ProfileQuota profileQuota) {
        Suggestion suggestion = new Suggestion(profileQuota, ExerQuota.domain_name);

        ExerListQuota exerListQuota = profileQuota.getQuota(ExerListQuota.domain_name);

        suggestion.addSuggestion("exer7d30f",getExer7d30Suggestion(exerListQuota));

        return suggestion;
    }
}
