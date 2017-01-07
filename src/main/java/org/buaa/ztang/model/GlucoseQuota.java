package org.buaa.ztang.model;

import com.alibaba.fastjson.JSONObject;

import java.sql.Timestamp;

/**
 * Created by qixiang on 1/4/17.
 */
public class GlucoseQuota extends Quota {

    public static final String domain_name = "glucose";

    private double PAbg;
    private int PAbgPoint;
    private Timestamp PAbgDate;
    private int PAbgSite;

    public GlucoseQuota()
    {
        setDomain(domain_name);
        PAbg = -1.0;
        PAbgPoint = -1;
        PAbgDate = null;
        PAbgSite = -1;
    }

    @Override
    public int parseData(JSONObject dataJSON) {
        if ( dataJSON.containsKey("PAbg") &&
                dataJSON.containsKey("PAbgPoint") &&
                dataJSON.containsKey("PAbgDate") &&
                dataJSON.containsKey("PAbgSite") ) {
            PAbg = dataJSON.getDoubleValue("PAbg");
            PAbgPoint = dataJSON.getIntValue("PAbgPoint");
            PAbgDate = dataJSON.getTimestamp("PAbgDate");
            PAbgSite = dataJSON.getIntValue("PAbgSite");
            setMeasure_time(PAbgDate);
            return 1;
        } else return 0;
    }

    @Override
    public JSONObject dumpData() {
        JSONObject dataJSON = new JSONObject();
        dataJSON.put("PAbg",PAbg);
        dataJSON.put("PAbgPoint",PAbgPoint);
        dataJSON.put("PAbgDate",PAbgDate);
        dataJSON.put("PAbgSite",PAbgSite);
        setMeasure_time(PAbgDate);
        return dataJSON;
    }

    @Override
    public void mergeData(Quota other) {
        if ( other instanceof GlucoseQuota ) {
            GlucoseQuota otherQuota = (GlucoseQuota) other;
            if ( PAbgDate.compareTo(otherQuota.PAbgDate) <= 0 )
            {
                PAbg = otherQuota.PAbg;
                PAbgPoint = otherQuota.PAbgPoint;
                PAbgDate = otherQuota.PAbgDate;
                PAbgSite = otherQuota.PAbgSite;
            }
        }
    }

    public double getPAbg() {
        return PAbg;
    }

    public void setPAbg(double PAbg) {
        this.PAbg = PAbg;
    }

    public int getPAbgPoint() {
        return PAbgPoint;
    }

    public void setPAbgPoint(int PAbgPoint) {
        this.PAbgPoint = PAbgPoint;
    }

    public Timestamp getPAbgDate() {
        return PAbgDate;
    }

    public void setPAbgDate(Timestamp PAbgDate) {
        this.PAbgDate = PAbgDate;
    }

    public int getPAbgSite() {
        return PAbgSite;
    }

    public void setPAbgSite(int PAbgSite) {
        this.PAbgSite = PAbgSite;
    }
}
