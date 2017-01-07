package org.buaa.ztang.model;

import com.alibaba.fastjson.JSONObject;

import java.sql.Timestamp;

/**
 * Created by qixiang on 1/7/17.
 */
public class WaistQuota extends Quota {

    public static final String domain_name = "waist";

    private double PAwaist;
    private Timestamp PAwaistDate;

    public WaistQuota() { setDomain(domain_name); }

    @Override
    public int parseData(JSONObject dataJSON) {
        if (dataJSON.containsKey("PAWaist") && dataJSON.containsKey("PAwaistDate") ) {
            this.PAwaist = dataJSON.getDoubleValue("PAWaist");
            this.PAwaistDate = dataJSON.getTimestamp("PAwaistDate");
            setMeasure_time(PAwaistDate);
            return 1;
        }
        return 0;
    }

    @Override
    public JSONObject dumpData() {
        setMeasure_time(PAwaistDate);
        JSONObject dataJSON = new JSONObject();
        dataJSON.put("PAwaist", PAwaist);
        dataJSON.put("PAwaistDate", PAwaistDate);
        return dataJSON;
    }

    @Override
    public void mergeData(Quota other) {
        if ( other instanceof WaistQuota ) {
            WaistQuota otherQuota = (WaistQuota) other;
            if (PAwaistDate == null || (otherQuota.PAwaistDate != null && PAwaistDate.compareTo(otherQuota.PAwaistDate) <= 0)) {
                PAwaist = otherQuota.PAwaist;
                PAwaistDate = otherQuota.PAwaistDate;
            }
        }
    }

    public double getPAwaist() {
        return PAwaist;
    }

    public void setPAwaist(double PAwaist) {
        this.PAwaist = PAwaist;
    }

    public Timestamp getPAwaistDate() {
        return PAwaistDate;
    }

    public void setPAwaistDate(Timestamp PAwaistDate) {
        this.PAwaistDate = PAwaistDate;
    }
}
