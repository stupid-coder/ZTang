package org.buaa.ztang.algorithm.impl;

import com.alibaba.fastjson.JSONObject;
import org.buaa.ztang.algorithm.iface.QuotaAlgorithm;
import org.buaa.ztang.model.GlucoseQuota;
import org.buaa.ztang.model.ProfileQuota;

/**
 * Created by qixiang on 1/7/17.
 */
public class GlucoseQuotaAlgorithmImpl implements QuotaAlgorithm {

    int getPabgE(GlucoseQuota glucoseQuota) {
        int PAbgE = 0;
        double PAbg = glucoseQuota.getPAbg();
        int PAbgPoint = glucoseQuota.getPAbgPoint();

        if ( PAbg < 3.9 ) PAbgE = 1;
        else if ( PAbg <= 4.3 ) PAbgE = 2;
        else if ( PAbg <= 16.6 ) {
            if (PAbgPoint == 1 || PAbgPoint == 3 || PAbgPoint == 5 || PAbgPoint == 8) {
                if (PAbg <= 7.0) PAbgE = 3;
                else if (PAbg <= 9.8) PAbgE = 4;
                else PAbgE = 5;
            } else {
                if (PAbg <= 10.0) PAbgE = 3;
                else if (PAbg <= 13.2) PAbgE = 4;
                else PAbgE = 5;
            }
        } else if ( PAbg <= 33.2 ) PAbgE = 6;
        else PAbgE = 7;

        return PAbgE;
    }

    String getPAbgESuggestion(int PAbgE)
    {
        if ( PAbgE == 1 )
            return "血糖低于3.9，属于低血糖范畴。对于意识清楚者，口服15~20g糖类食物（葡萄糖为佳）；对于意识障碍者，请就诊（给予50%葡萄糖液20~40mL静脉注射，或胰高血糖素0.5~1.0mg肌注）。每15分钟监测血糖1次：血糖低于3.0，请就诊（给予50%葡萄糖60mL静脉注射）；低于3.9，给予葡萄糖口服（或静脉注射）；高于3.9，但距离下一次就餐时间在1小时以上，给予含淀粉或蛋白质食物。";
        if ( PAbgE == 2 )
            return "血糖偏低，有发生低血糖的风险。请避免运动，并尽快补充糖分。15分钟再监测血糖。";
        if ( PAbgE == 3 )
            return "血糖达到控制水平，请继续保持。";
        if ( PAbgE == 4 )
            return "血糖偏高，请咨询医生结合生活方式和药物进行控制。控制目标为空腹4.4~7.0，非空腹低于10。";
        if ( PAbgE == 5 )
            return "血糖高，请咨询医生结合生活方式和药物进行控制。控制目标为空腹4.4~7.0，非空腹低于10。";
        if ( PAbgE == 6 )
            return "血糖严重超高，有可能发生糖尿病酮症酸中毒，请尽快就医检查和治疗。";
        return "血糖严重超高，有可能发生高血糖高渗综合征，请尽快就医检查和治疗。";
    }

    @Override
    public JSONObject algo(ProfileQuota profileQuota) {
        JSONObject jsonObject = new JSONObject();

        GlucoseQuota glucoseQuota = profileQuota.getQuota(GlucoseQuota.domain_name);

        if ( glucoseQuota == null ) jsonObject.put("error","please add Glucose Quota");
        else {
            jsonObject.put("PAbgE",getPAbgESuggestion(getPabgE(glucoseQuota)));
        }

        return jsonObject;
    }
}
