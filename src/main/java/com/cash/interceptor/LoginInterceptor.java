package com.cash.interceptor;

import com.cash.model.DashAlert;
import com.cash.model.DashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Value(value = "${cash.title}")
    private String title;

    @Value(value = "${cash.version}")
    private String version;

    @Autowired
    private List<DashMessage> messages;

    @Autowired
    private List<DashAlert> alerts;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        long startTime = System.currentTimeMillis();
        System.out.println("\n-------- LogInterception.preHandle --- ");
        System.out.println("Request URL: " + request.getRequestURL());
        System.out.println("Start Time: " + System.currentTimeMillis());
        request.setAttribute("startTime", startTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("\n-------- LogInterception.postHandle --- ");
        StringBuffer url = request.getRequestURL();
        System.out.println("Request URL: " + url);

        HttpSession session = request.getSession();
        if(!url.toString().contains("/login") && session.getAttribute("userLoggedIn") == null){
            response.sendRedirect("/login");
        }
        session.setAttribute("title", title);
        session.setAttribute("version", version);
        session.setAttribute("messages", messages);
        session.setAttribute("alerts", alerts);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, //
                                Object handler, Exception ex) throws Exception {
        System.out.println("\n-------- LogInterception.afterCompletion --- ");
        long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        System.out.println("Request URL: " + request.getRequestURL());
        System.out.println("End Time: " + endTime);
        System.out.println("Time Taken: " + (endTime - startTime));
    }

}
