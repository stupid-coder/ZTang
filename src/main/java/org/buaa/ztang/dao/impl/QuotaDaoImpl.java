package org.buaa.ztang.dao.impl;

import org.buaa.ztang.dao.iface.QuotaDao;
import org.buaa.ztang.model.Quota;
import org.buaa.ztang.utils.TimeUtils;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
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
            sql = String.format("SELECT * FROM %s WHERE uid=? AND domain=?",table_name);
            quotaList = this.getJdbcTemplate().query(sql,new Quota(),uid,domain);
        }
        else {
            sql = String.format("SELECT * FROM %s WHERE uid=? AND domain=? AND status=?", table_name);
            quotaList = this.getJdbcTemplate().query(sql,new Quota(),uid,domain,status);
        }

        return quotaList;
    }

    @Override
    public int add(Quota quota) throws Exception {
        String sql = String.format("INSERT INTO %s (uid,domain,data,status) VALUES(?,?,?,?)",table_name);
        return this.getJdbcTemplate().update(sql,quota.getUid(),quota.getDomain(),quota.getData().toString(),quota.getStatus());
    }

    @Override
    public int update(Quota quota) throws Exception {
        String sql = String.format("UPDATE %s SET data=?, add_time=? WHERE id=?",table_name);
        return this.getJdbcTemplate().update(sql,quota.getData().toString(), TimeUtils.currentTime(),quota.getId());
    }
}