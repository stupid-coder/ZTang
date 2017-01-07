package org.buaa.ztang.service.iface;

import com.alibaba.fastjson.JSONArray;
import org.buaa.ztang.model.Quota;

import java.util.List;

/**
 * Created by qixiang on 1/5/17.
 */
public interface QuotaService {

    Quota get(int id) throws Exception;

    List<Quota> get(int uid, String domain, String status) throws Exception;

    <T extends Quota> List<T> get(int uid, String domain, String status, Class<T> clazz) throws Exception;

    JSONArray getData(int uid, String domain, String status) throws Exception;

    int add(Quota quota) throws Exception;

    int update(Quota quota) throws Exception;

    int profileUpdate(Quota quota) throws Exception;
}
