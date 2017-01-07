package org.buaa.ztang.model;

import com.alibaba.fastjson.JSONObject;

import java.sql.Timestamp;

/**
 * Created by qixiang on 1/5/17.
 */
public class ExerQuota extends Quota {

    public static final String domain_name = "exer";

    private int exer;
    private double exerValue;
    private Timestamp exerDate;

    public ExerQuota()
    {
        setDomain(domain_name);
        exer = -1;
        exerValue = -1.0;
        exerDate = null;
    }

    @Override
    public int parseData(JSONObject data) {
        if ( data.containsKey("exer")) exer = data.getIntValue("exer");
        else if ( data.containsKey("exerValue")) exerValue = data.getDoubleValue("exerValue");
        else return 0;

        if ( data.containsKey("exerDate") ) exerDate = data.getTimestamp("exerDate");
        else return 0;

        return 1;
    }

    @Override
    public JSONObject dumpData() {
        JSONObject jsonObject = new JSONObject();
        if ( exer != -1 ) jsonObject.put("exer",exer);
        if ( exerValue != -1.0 ) jsonObject.put("exerValue",exerValue);
        jsonObject.put("exerDate",exerDate);
        return jsonObject;
    }

    public int getExer() {
        return exer;
    }

    public void setExer(int exer) {
        this.exer = exer;
    }

    public double getExerValue() {
        return exerValue;
    }

    public void setExerValue(double exerValue) {
        this.exerValue = exerValue;
    }

    public Timestamp getExerDate() {
        return exerDate;
    }

    public void setExerDate(Timestamp exerDate) {
        this.exerDate = exerDate;
    }
}
