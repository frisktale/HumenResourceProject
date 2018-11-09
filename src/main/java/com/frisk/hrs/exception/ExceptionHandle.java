package com.frisk.hrs.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author frisktale
 * @date 2018/10/8
 */
public class ExceptionHandle implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        HashMap<String, Object> map = new HashMap<>(16);
        map.put("ex", ex);
        if (NumberFormatException.class.isInstance(ex)) {
            return new ModelAndView("error/typeError", map);
        } else {
            return null;
        }
    }
}
