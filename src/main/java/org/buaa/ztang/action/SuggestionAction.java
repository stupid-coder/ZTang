package org.buaa.ztang.action;

import com.alibaba.fastjson.JSONObject;
import org.buaa.ztang.model.ProfileQuota;
import org.buaa.ztang.model.Quota;
import org.buaa.ztang.service.iface.QuotaService;
import org.buaa.ztang.service.iface.SuggestionService;
import org.buaa.ztang.utils.HttpResponseWrapperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.List;

/**
 * Created by qixiang on 1/7/17.
 */

@RestController
public class SuggestionAction {

    private static Logger logger = LoggerFactory.getLogger(QuotaAction.class);

    @Resource
    QuotaService quotaService;
    @Resource
    SuggestionService suggestionService;

    @RequestMapping(value = "/suggestion/{domain}/{uid}/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpResponseWrapperUtils get(ServletRequest request, ServletResponse response,
                                        @PathVariable("domain") String domain,
                                        @PathVariable("uid") int uid)
    {
        try {
            List<ProfileQuota> profileQuotaList = quotaService.get(uid, ProfileQuota.domain_name, null, ProfileQuota.class);
            if ( profileQuotaList.size() == 0 ) {
                logger.warn("failure to get ProfileQuota - uid:[{}]",uid);
                return new HttpResponseWrapperUtils(null,-1,"failure to get suggestion with no quota");
            } else {
                ProfileQuota profileQuota = profileQuotaList.get(0);
                JSONObject ret = new JSONObject();
                JSONObject data = profileQuota.getQuotaData(domain);

                if ( data == null ) {
                    logger.warn("failure to get quota data - uid:[{}] domain:[{}]",uid,domain);
                    return new HttpResponseWrapperUtils(null,-1,"failure to get suggestion with no quota");
                }

                ret.put("data",data);
                ret.put("suggestion",suggestionService.suggestion(domain,profileQuota));
                return new HttpResponseWrapperUtils(data);
            }
        } catch (Exception e) {
            logger.warn("failure to get suggestion - uid:[{}] domain:[{}]",uid,domain);
            return new HttpResponseWrapperUtils(null,-1,"failure to get suggestion");
        }
    }

    @RequestMapping(value = "/suggestion/{id}/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpResponseWrapperUtils get(ServletRequest request, ServletResponse response,
                                        @PathVariable("id") int id)
    {
        /*
        try {
            Quota quota = quotaService.get(id);

            if ( quota == null ) {
                logger.warn("failure to get ProfileQuota - id:[{}]",id);
                return new HttpResponseWrapperUtils(null,-1,"failure to get suggestion with no quota");
            } else {
                JSONObject data = quota.getData();
                if ( data == null ) {
                    logger.warn("failure to get quota's data - id:[{}]",id);
                    return new HttpResponseWrapperUtils(null,-1,"failure to get suggestion with no quota");
                } else {
                    String suggestion = suggestionService.suggestion(quota.getDomain(),data);
                    data.put("suggestion",suggestion);
                    return new HttpResponseWrapperUtils(data);
                }
            }
        } catch (Exception e) {
            logger.warn("failure to get suggestion - id:[{}]",id);
            return new HttpResponseWrapperUtils(null,-1,"failure to get suggestion");
        }
        */
        return new HttpResponseWrapperUtils(null);
    }



}
