package org.buaa.ztang.model;

import com.alibaba.fastjson.JSONObject;

import java.sql.Timestamp;

/**
 * Created by qixiang on 1/5/17.
 */
public class DietQuota extends Quota {

    public static final String domain_name = "diet";

    private int diet;
    private Timestamp dietDate;

    public DietQuota()
    {
        setDomain(domain_name);
        diet = -1;
        dietDate = null;
    }

    @Override
    public int parseData(JSONObject dataJSON) {
        if ( dataJSON.containsKey("dietd") && dataJSON.containsKey("dietDate")) {
            diet = dataJSON.getIntValue("dietd");
            dietDate = dataJSON.getTimestamp("dietDate");
            return 1;
        } else
            return 0;
    }

    @Override
    public JSONObject dumpData() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dietd",diet);
        return jsonObject;
    }

    @Override
    public int compareTo(Quota o) {
        if ( o instanceof DietQuota )
            return ((DietQuota) o).dietDate.compareTo(dietDate);
        else return 0;
    }

    /*
    @Override
    public void mergeData(Quota other) {
        if ( other instanceof DietQuota ) {
            DietQuota otherQuota = (DietQuota) other;
            if ( dietDate.compareTo(otherQuota.dietDate) < 0 )
            {
                diet = otherQuota.diet;
                dietDate = otherQuota.dietDate;
            }
        }
    }
    */

    public int getDiet() {
        return diet;
    }

    public void setDiet(int diet) {
        this.diet = diet;
    }

    public Timestamp getDietDate() {
        return dietDate;
    }

    public void setDietDate(Timestamp dietDate) {
        this.dietDate = dietDate;
    }

}
