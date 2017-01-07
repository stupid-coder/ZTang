package org.buaa.ztang.model;

import com.alibaba.fastjson.JSONObject;

import java.sql.Timestamp;

/**
 * Created by qixiang on 1/5/17.
 */
public class WeightQuota extends Quota {

    public static final String domain_name = "weight";

    private double PAweight;
    private Timestamp PAweightDate;

    public WeightQuota()
    {
        setDomain(domain_name);
    }

    @Override
    public int parseData(JSONObject dataJSON) {
        if (dataJSON.containsKey("PAweight") && dataJSON.containsKey("PAweightDate") ) {
            this.PAweight = dataJSON.getDoubleValue("PAweight");
            this.PAweightDate = dataJSON.getTimestamp("PAweightDate");
            setMeasure_time(PAweightDate);
            return 1;
        }
        return 0;
    }

    @Override
    public JSONObject dumpData() {
        setMeasure_time(PAweightDate);
        JSONObject dataJSON = new JSONObject();
        dataJSON.put("PAweight", PAweight);
        dataJSON.put("PAweightDate", PAweightDate);
        return dataJSON;
    }

    @Override
    public void mergeData(Quota other) {
        if ( other instanceof WeightQuota ) {
            WeightQuota otherQuota = (WeightQuota) other;
            if ( PAweightDate == null || ( otherQuota.PAweightDate != null && PAweightDate.compareTo(otherQuota.PAweightDate) <= 0) )
            {
                PAweight = otherQuota.PAweight;
                PAweightDate = otherQuota.PAweightDate;
            }
        }
    }

    public double getPAweight() {
        return PAweight;
    }

    public void setPAweight(double PAweight) {
        this.PAweight = PAweight;
    }

    public Timestamp getPAweightDate() {
        return PAweightDate;
    }

    public void setPAweightDate(Timestamp PAweightDate) {
        this.PAweightDate = PAweightDate;
    }
}
