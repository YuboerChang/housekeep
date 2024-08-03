package com.coolers.housekeep.housekeep.config;

import com.coolers.housekeep.housekeep.constant.RetMessage;
import com.coolers.housekeep.housekeep.constant.RetType;
import com.coolers.housekeep.housekeep.dto.BussinessException;
import com.coolers.housekeep.housekeep.util.Method;
import com.coolers.housekeep.housekeep.util.Token;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //前端请求应放置token，位置约定为 header.Authorization，特定请求不拦截不走这个逻辑
        String token = request.getHeader("Authorization");
        if (Method.isNotEmptyObject(token) && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        try {
            Token.verifyToken(token);
            return true;
        } catch (Exception e) {
            throw new BussinessException(RetType.BUSSINESS_ERR, RetMessage.TOKEN_LOST);
        }
    }

}
