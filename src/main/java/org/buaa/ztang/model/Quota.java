package org.buaa.ztang.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by qixiang on 1/4/17.
 */
public class Quota implements java.io.Serializable, RowMapper<Quota>, Comparable<Quota> {

    private static final long serialVersionUID = 1L;

    private int id;
    private int uid;
    private String domain;
    private JSONObject data;
    private Timestamp measure_time;
    private Timestamp modify_time;
    private String status;

    public Quota()
    {
        id = -1;
        uid = -1;
        domain = null;
        data = null;
        measure_time = null;
        modify_time = null;
        status = null;
    }

    @Override
    public Quota mapRow(ResultSet resultSet, int nRow) throws SQLException
    {
        Quota quota = new Quota();

        Field[] fields = this.getClass().getDeclaredFields();

        for ( int i = 0; i < fields.length; ++ i)
        {
            try {

                fields[i].setAccessible(true);

                if (Modifier.isStatic(fields[i].getModifiers())) continue;

                if ( fields[i].getType() == int.class )
                    fields[i].set(quota,resultSet.getInt(fields[i].getName()));
                else if ( fields[i].getType() == String.class )
                    fields[i].set(quota,resultSet.getString(fields[i].getName()));
                else if ( fields[i].getType() == Timestamp.class )
                    fields[i].set(quota,resultSet.getTimestamp(fields[i].getName()));
                else if ( fields[i].getType() == JSONObject.class )
                    fields[i].set(quota, JSON.parse(resultSet.getString(fields[i].getName())));

            } catch ( Exception e) {
                continue;
            }
        }

        return quota;
    }

    public void toQuota()
    {
        this.data = dumpData();
    }

    public void fromQuota(Quota quota)
    {
        this.id = quota.id;
        this.uid = quota.uid;
        this.domain = quota.domain;
        this.data = quota.data;
        this.measure_time = quota.measure_time;
        this.modify_time = quota.modify_time;
        this.status = quota.status;
        parseData(this.data);
    }

    public int parseData(JSONObject data) { this.data = data; return 1; }

    public JSONObject dumpData() { return data;}

    public void mergeData(Quota other) {}

    @Override
    public int compareTo(Quota o) {
        return 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public Timestamp getMeasure_time() {
        return measure_time;
    }

    public void setMeasure_time(Timestamp measure_time) {
        this.measure_time = measure_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Timestamp getModify_time() {
        return modify_time;
    }

    public void setModify_time(Timestamp modify_time) {
        this.modify_time = modify_time;
    }

    @Override
    public String toString() {
        return dumpData().toString();
    }

    public static Quota GetQuota(String domain)
    {
        if ( domain.compareToIgnoreCase(GlucoseQuota.domain_name) == 0 )
            return new GlucoseQuota();
        else if ( domain.compareToIgnoreCase(PressureQuota.domain_name) == 0 )
            return new PressureQuota();
        else if ( domain.compareToIgnoreCase(WeightQuota.domain_name) == 0 )
            return new WeightQuota();
        else if ( domain.compareToIgnoreCase(DietQuota.domain_name) == 0 )
            return new DietQuota();
        else if ( domain.compareToIgnoreCase(ExerQuota.domain_name) == 0 )
            return new ExerQuota();
        else if ( domain.compareToIgnoreCase(Suggestion.domain_name) == 0 )
            return new Suggestion();
        else return null;
    }
}
