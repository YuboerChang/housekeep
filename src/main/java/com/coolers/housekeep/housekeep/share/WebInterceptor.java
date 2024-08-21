package com.coolers.housekeep.housekeep.share;

import com.alibaba.fastjson.JSONObject;
import com.coolers.housekeep.housekeep.constant.BaseConst;
import com.coolers.housekeep.housekeep.dao.WebLogMapper;
import com.coolers.housekeep.housekeep.po.WebLogWithBLOBs;
import com.coolers.housekeep.housekeep.util.BaseUtil;
import com.coolers.housekeep.housekeep.util.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.nio.charset.StandardCharsets;
import java.util.Date;


public class WebInterceptor implements HandlerInterceptor {
    private final static String USERID_STRING = "userId";
    private final static String TYPE_STRING = "type";
    private final static String MSG_STRING = "msg";
    @Autowired
    WebLogMapper webLogMapper;

    /**
     * 请求预处理
     * 1.安全校验
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //前端请求应放置token，位置约定为 header.Authorization，特定请求不拦截不走这个逻辑
        String token = request.getHeader("Authorization");
        if (BaseUtil.isNotEmptyObject(token) && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        try {
            TokenUtil.verifyToken(token);
            return true;
        } catch (Exception e) {
//            throw new BussinessException(RetTypeConst.BUSSINESS_ERR, RetMsgConst.TOKEN_LOST);
            return true;
        }
    }

    /**
     * 响应返回前处理
     * 1.日志记录
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        ContentCachingRequestWrapper reqWrapper = (ContentCachingRequestWrapper) request;
        ContentCachingResponseWrapper resWrapper = (ContentCachingResponseWrapper) response;
        WebLogWithBLOBs webLog = new WebLogWithBLOBs();
        webLog.setIp(reqWrapper.getRemoteAddr());
        webLog.setReqParams(reqWrapper.getContentAsString());
        webLog.setUserId(getUserId(reqWrapper.getContentAsString()));
        webLog.setApiId(reqWrapper.getRequestURI());
        webLog.setCreateTime(new Date());
        resolveTypeAndMsg(new String(resWrapper.getContentAsByteArray(), StandardCharsets.UTF_8), webLog);
        webLogMapper.insert(webLog);
    }

    /**
     * 通过上送的req_body截取出userId
     */
    protected static String getUserId(String params) {
        String[] ps = params.split("&");
        for (String p : ps) {
            String[] sArr = p.split("=");
            if (sArr.length > 1 && USERID_STRING.equals(sArr[0])) {
                return sArr[1];
            }
        }
        return "";
    }

    /**
     * 日志res值需分开处理，异常不用再记录params，直接记录msg即可
     */
    private void resolveTypeAndMsg(String body, WebLogWithBLOBs webLog) {
        JSONObject jsonObject = JSONObject.parseObject(body);
        webLog.setResType(jsonObject.getString(TYPE_STRING));
        if (BaseConst.SUCCESS.equals(jsonObject.get(TYPE_STRING))) {
            webLog.setResParams(body);
        } else {
            webLog.setResParams(jsonObject.getString(MSG_STRING));
        }
    }
}
