package com.zb.api.fuelrod.controller;

import com.alibaba.fastjson.JSONObject;
import com.zb.api.commons.core.auth.Access;
import com.zb.api.commons.core.context.RequestContext;
import com.zb.api.fuelrod.service.FuelRodService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * PluginController
 *
 * @author zj
 * @date 2016/8/18
 */
@Controller
@RequestMapping("/v1/plugins/fuelrod")
public class FuelRodController {

    @Resource
    private FuelRodService fuelRodService;

    /**
     * 加油棒 点赞
     * @param roomId    房间ID
     * @param liveId
     * @param count
     * @return
     */
    @RequestMapping(path = "/client/clicklikes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Access(type = Access.AccessType.COMMON)
    public
    @ResponseBody String fuelRod(@RequestParam(name = "roomId", required = true) int roomId,
                                 @RequestParam(name = "liveId", required = true) long liveId,
                                 @RequestParam(name = "count", required = true) int count){
        JSONObject result = new JSONObject();
        RequestContext rc = RequestContext.getRequestContext();
        boolean isSend = fuelRodService.fuelRod(rc.getUid(), roomId, liveId, count);
        if (!isSend){
            RequestContext.getRequestContext().getResult().setApistatus(0);
        }
        return result.toString();
    }
}
