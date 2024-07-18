package com.coolers.housekeep.housekeep.config;

import com.coolers.housekeep.housekeep.constant.FormatConst;
import com.coolers.housekeep.housekeep.constant.RetMessage;
import com.coolers.housekeep.housekeep.constant.RetType;
import com.coolers.housekeep.housekeep.dto.BussinessException;
import com.coolers.housekeep.housekeep.vo.BaseRes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;

import java.io.IOException;

@Component
public class GlobalExceptionResolver extends DefaultErrorAttributes {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {
        BaseRes res = buildRes(ex);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(FormatConst.UTF8);
        try {
            response.getWriter().write(JSON.toJSONString(res));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ModelAndView();
    }

    private BaseRes buildRes(Exception ex) {
        BaseRes res = new BaseRes();
        if (ex instanceof BussinessException) {
            res.setType(((BussinessException) ex).getErrorType());
            res.setMsg(((BussinessException) ex).getErrorMessage());
        } else {
            res.setType(RetType.SYSTEM_ERR);
            res.setMsg(RetMessage.SYS_ERR);
        }
        return res;
    }

}
