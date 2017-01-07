package org.buaa.ztang.model;

import com.alibaba.fastjson.JSONObject;

import java.sql.Timestamp;

/**
 * Created by qixiang on 1/7/17.
 */
public class HeightQuota extends Quota {
    public static final String domain_name = "height";

    private double PAheight;
    private Timestamp PAheightDate;


    public HeightQuota() { setDomain(domain_name);}

    @Override
    public int parseData(JSONObject dataJSON) {
        if ( dataJSON.containsKey("PAheight") && dataJSON.containsKey("PAheightDate") ) {
            PAheight = dataJSON.getDoubleValue("PAheight");
            PAheightDate = dataJSON.getTimestamp("PAheightDate");
            setMeasure_time(PAheightDate);
            return 1;
        }
        return 0;
    }

    @Override
    public JSONObject dumpData() {
        setMeasure_time(PAheightDate);
        JSONObject dataJSON = new JSONObject();
        dataJSON.put("PAheight", PAheight);
        dataJSON.put("PAheightDate", PAheightDate);
        return dataJSON;
    }

    @Override
    public void mergeData(Quota other) {
        if ( other instanceof HeightQuota ) {
            HeightQuota otherQuota = (HeightQuota) other;
            if ( PAheightDate == null || ( otherQuota.PAheightDate != null && PAheightDate.compareTo(otherQuota.PAheightDate) <= 0) )
            {
                PAheight = otherQuota.PAheight;
                PAheightDate = otherQuota.PAheightDate;
            }
        }
    }

    public double getPAheight() {
        return PAheight;
    }

    public void setPAheight(double PAheight) {
        this.PAheight = PAheight;
    }

    public Timestamp getPAheightDate() {
        return PAheightDate;
    }

    public void setPAheightDate(Timestamp PAheightDate) {
        this.PAheightDate = PAheightDate;
    }
}
