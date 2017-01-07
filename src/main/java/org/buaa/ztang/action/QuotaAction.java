package org.buaa.ztang.action;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.buaa.ztang.model.Quota;
import org.buaa.ztang.service.iface.QuotaService;
import org.buaa.ztang.utils.HttpResponseWrapperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by qixiang on 1/4/17.
 */
@RestController
public class QuotaAction {

    private static Logger logger = LoggerFactory.getLogger(QuotaAction.class);

    @Resource
    QuotaService quotaService;

    @RequestMapping(value = "/quota/{domain}/{uid}/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpResponseWrapperUtils get(HttpServletRequest request, HttpServletResponse response,
                                        @PathVariable("domain") String domain,
                                        @PathVariable("uid") int uid)
    {
        try {
            JSONArray quotaList = quotaService.getData(uid,domain,null);
            logger.info("success to get quota - domain:[{}] uid:[{}] - quotaList:[{}]",quotaList.toString());
            return new HttpResponseWrapperUtils(quotaList);
        } catch (Exception e) {
            logger.warn("failure to get quota - domain:[{}] uid:[{}] expcetion:[{}]", domain, uid, e);
            return new HttpResponseWrapperUtils(null, -1, "failure to get quota");
        }
    }

    @RequestMapping(value = "/quota/{domain}/{uid}/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpResponseWrapperUtils add(HttpServletRequest request, HttpServletResponse response,
                                        @PathVariable("domain") String domain,
                                        @PathVariable("uid") int uid,
                                        @RequestBody JSONObject requestBody)

    {
        try {
            Quota quota;
            if ((quota = Quota.GetQuota(domain)) == null ) {
                logger.warn("failure to get quota factory - domain:[{}]", domain);
                return new HttpResponseWrapperUtils(null,-1, String.format("failure to add the quota with unsupported domain:[%s]",domain));
            } else {
                quota.setUid(uid);
                if ( quota.parseData(requestBody) != 0 && quotaService.add(quota) == 1) {
                    quotaService.profileUpdate(quota);
                    logger.info("success to add the quota:[{}]",quota);
                    return new HttpResponseWrapperUtils(null);
                } else {
                    logger.warn("failure to add quota:[{}]",quota);
                    return new HttpResponseWrapperUtils(null,-1,"failure to add glucose");
                }
            }
        } catch (Exception e) {
            logger.warn("failure to parse the quota - domain:[{}] uid:[{}]",domain,uid);
            return new HttpResponseWrapperUtils(null,-1,"failure to parse the quota");
        }
    }
}
