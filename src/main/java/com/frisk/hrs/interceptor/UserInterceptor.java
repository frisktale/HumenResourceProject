package com.frisk.hrs.interceptor;

import com.frisk.hrs.controller.UserController;
import com.frisk.hrs.pojo.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * @author frisktale
 * @date 2018/10/21
 */
@Component
public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            request.getRequestDispatcher("/WEB-INF/page/error/permissionError.jsp").forward(request, response);
            return false;
        }
        if (UserController.USER.compareTo(user.getType()) <= 0) {
            return true;
        }
        request.getRequestDispatcher("/WEB-INF/page/error/permissionError.jsp").forward(request, response);
        return false;

    }
}
