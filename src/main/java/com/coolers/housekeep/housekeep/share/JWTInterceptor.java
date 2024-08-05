package com.coolers.housekeep.housekeep.share;

import com.coolers.housekeep.housekeep.constant.RetMsgConst;
import com.coolers.housekeep.housekeep.constant.RetTypeConst;
import com.coolers.housekeep.housekeep.dto.BussinessException;
import com.coolers.housekeep.housekeep.util.BaseUtil;
import com.coolers.housekeep.housekeep.util.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;


public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //前端请求应放置token，位置约定为 header.Authorization，特定请求不拦截不走这个逻辑
        String token = request.getHeader("Authorization");
        if (BaseUtil.isNotEmptyObject(token) && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        try {
            TokenUtil.verifyToken(token);
            return true;
        } catch (Exception e) {
            throw new BussinessException(RetTypeConst.BUSSINESS_ERR, RetMsgConst.TOKEN_LOST);
        }
    }

}
