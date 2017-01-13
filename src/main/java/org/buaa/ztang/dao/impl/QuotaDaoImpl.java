package org.buaa.ztang.dao.impl;

import org.buaa.ztang.dao.iface.QuotaDao;
import org.buaa.ztang.model.Quota;
import org.buaa.ztang.utils.TimeUtils;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by qixiang on 1/4/17.
 */

@Repository
public class QuotaDaoImpl extends JdbcDaoSupport implements QuotaDao {

    private final String table_name = "Quota";

    @Resource
    private DataSource dataSource;

    @PostConstruct
    private void initialize()
    {
        setDataSource(dataSource);
    }

    @Override
    public Quota get(int id) throws Exception {
        String sql = String.format("SELECT * FROM %s WHERE id = ?", table_name);
        return getJdbcTemplate().queryForObject(sql,new Quota(), id);

    }

    @Override
    public List<Quota> get(int uid, String domain, String status) throws Exception {
        String sql;
        List<Quota> quotaList;

        if ( status == null ) {
            sql = String.format("SELECT * FROM %s WHERE uid=? AND domain=? ORDER BY measure_time DESC",table_name);
            quotaList = this.getJdbcTemplate().query(sql,new Quota(),uid,domain);
        }
        else {
            sql = String.format("SELECT * FROM %s WHERE uid=? AND domain=? AND status=? ORDER BY measure_time DESC", table_name);
            quotaList = this.getJdbcTemplate().query(sql,new Quota(),uid,domain,status);
        }

        return quotaList;
    }

    @Override
    public List<Quota> get(int uid, String domain, String status, Timestamp timestamp) throws Exception {
        if ( timestamp == null )
            return get(uid, domain, status);
        else {
            if ( status == null ) {
                String sql = String.format("SELECT * FROM %s WHERE uid=? AND domain=? AND measure_time<? ORDER BY measure_date DESC", table_name);
                return this.getJdbcTemplate().query(sql,new Quota(), uid, domain, timestamp);
            } else {
                String sql = String.format("SELECT * FROM %s WHERE uid=? AND domain=? AND status=? AND measure_time<? ORDER BY measure_date DESC", table_name);
                return this.getJdbcTemplate().query(sql,new Quota(), uid, domain, status, timestamp);
            }
        }
    }

    @Override
    public List<Quota> get(int uid, String domain, String status, Timestamp timestamp, int size) throws Exception {
        if ( size == 0 ) {
            return get(uid,domain,status,timestamp);
        } else {
            if ( status == null ) {
                String sql = String.format("SELECT * FROM %s WHERE uid=? AND domain=? AND measure_time<? ORDER BY measure_date DESC LIMIT %s", table_name, size);
                return this.getJdbcTemplate().query(sql,new Quota(), uid, domain, timestamp);
            } else {
                String sql = String.format("SELECT * FROM %s WHERE uid=? AND domain=? AND status=? AND measure_time<? ORDER BY measure_date DESC LIMIT %s", table_name, size);
                return this.getJdbcTemplate().query(sql,new Quota(), uid, domain, status, timestamp);
            }
        }
    }

    @Override
    public int add(Quota quota) throws Exception {
        String sql = String.format("INSERT INTO %s (uid, domain, data, status, measure_time) VALUES(?,?,?,?,?)",table_name);
        return this.getJdbcTemplate().update(sql,quota.getUid(),quota.getDomain(),quota.getData().toString(),quota.getStatus(),quota.getMeasure_time());
    }

    @Override
    public int update(Quota quota) throws Exception {
        String sql = String.format("UPDATE %s SET data=?, measure_time=? WHERE id=?",table_name);
        return this.getJdbcTemplate().update(sql,quota.getData().toString(), quota.getMeasure_time(),quota.getId());
    }
}
