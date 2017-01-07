package org.buaa.ztang.model;

import com.alibaba.fastjson.JSONObject;
import java.sql.Timestamp;

/**
 * Created by qixiang on 1/5/17.
 */
public class PressureQuota extends Quota {

    public static final String domain_name = "pressure";

    private double PAsbp;
    private double PAdbp;
    private double PAhr;
    private Timestamp PressureDate;

    public PressureQuota()
    {
        setDomain(domain_name);
        PAsbp = -1.0;
        PAdbp = -1.0;
        PAhr = -1.0;
        PressureDate = null;
    }

    @Override
    public int parseData(JSONObject dataJSON) {
        if ( dataJSON.containsKey("PAsbp") &&
                dataJSON.containsKey("PAdbp") &&
                dataJSON.containsKey("PAhr") &&
                dataJSON.containsKey("PressureDate") ) {
            PAsbp = dataJSON.getDoubleValue("PAsbp");
            PAdbp = dataJSON.getDoubleValue("PAdbp");
            PAhr = dataJSON.getDoubleValue("PAhr");
            PressureDate = dataJSON.getTimestamp("PressureDate");
            setMeasure_time(PressureDate);
        } else return 0;
        return 1;
    }

    @Override
    public JSONObject dumpData() {
        setMeasure_time(PressureDate);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("PAsbp",PAsbp);
        jsonObject.put("PAdbp",PAdbp);
        jsonObject.put("PAhr",PAhr);
        jsonObject.put("PressureDate",PressureDate);
        return jsonObject;
    }

    @Override
    public void mergeData(Quota other) {
        if ( other instanceof  PressureQuota ) {
            PressureQuota otherQuota = (PressureQuota) other;
            if ( PressureDate.compareTo(otherQuota.PressureDate) <= 0 ) {
                PAsbp = otherQuota.PAsbp;
                PAdbp = otherQuota.PAdbp;
                PAhr = otherQuota.PAhr;
            }
        }
    }

    public double getPAsbp() {
        return PAsbp;
    }

    public void setPAsbp(double PAsbp) {
        this.PAsbp = PAsbp;
    }

    public double getPAdbp() {
        return PAdbp;
    }

    public void setPAdbp(double PAdbp) {
        this.PAdbp = PAdbp;
    }

    public double getPAhr() {
        return PAhr;
    }

    public void setPAhr(double PAhr) {
        this.PAhr = PAhr;
    }

    public Timestamp getPressureDate() {
        return PressureDate;
    }

    public void setPressureDate(Timestamp pressureDate) {
        PressureDate = pressureDate;
    }


}
