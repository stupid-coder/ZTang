package org.buaa.ztang.algorithm.impl;

import com.alibaba.fastjson.JSONObject;
import org.buaa.ztang.algorithm.iface.QuotaAlgorithm;
import org.buaa.ztang.model.PressureQuota;
import org.buaa.ztang.model.ProfileQuota;

/**
 * Created by qixiang on 1/7/17.
 */
public class PressureQuotaAlgorithmImpl implements QuotaAlgorithm {


    public int getPAbpE(PressureQuota pressureQuota)
    {
        double PAsbp = pressureQuota.getPAsbp();
        double PAdbp = pressureQuota.getPAsbp();

        if ( PAsbp < 120 && PAdbp < 80 ) return 1;
        else if ( PAsbp >= 120 && PAsbp < 140 && PAdbp >= 80 && PAdbp < 90 ) return 2;
        else if ( (PAsbp >= 140 && PAsbp < 160) || (PAdbp >= 90 && PAdbp < 100) ) return 3;
        else if ( (PAsbp >= 160 && PAsbp < 180) || (PAdbp >= 100 && PAdbp < 110) ) return 4;
        else return 5;
    }

    public String getPApgESuggestion(int PAbpE)
    {
        if ( PAbpE == 1 ) return "正常血压";
        else if ( PAbpE == 2 ) return "正常高值血压，未来心血管风险相对于正常血压人群增加1倍以上，请注意控制到正常水平（高压低于120，低压低于80）";
        else if ( PAbpE == 3 ) return "轻度高血压，请咨询医生结合生活方式和药物进行控制。控制目标为高压低于140，低压低于80";
        else if ( PAbpE == 4 ) return "中度高血压，请咨询医生结合生活方式和药物进行控制。控制目标为高压低于140，低压低于80";
        else return "重度高血压，请咨询医生结合生活方式和药物进行控制。控制目标为高压低于140，低压低于80";
    }

    @Override
    public JSONObject algo(ProfileQuota profileQuota)
    {

        JSONObject suggestion = new JSONObject();

        PressureQuota pressureQuota = profileQuota.getQuota(PressureQuota.domain_name);

        if ( pressureQuota == null ) suggestion.put("error", "please add Pressure Quota");
        else {
            suggestion.put("PAbpE",getPApgESuggestion(getPAbpE(pressureQuota)));
        }

        return suggestion;
    }
}
