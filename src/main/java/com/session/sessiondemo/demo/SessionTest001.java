package com.session.sessiondemo.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.HttpCookie;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lizhen on 2018/4/16 0016.
 */
@RestController
public class SessionTest001 {
    @RequestMapping("/getsession")
    public String getSession(HttpSession session, HttpServletRequest httpServletRequest){
        String sessionId = session.getId();
        System.out.println("SessionID="+sessionId);
        System.out.println("SessionID="+session.getAttribute("requestUrl"));
        Map<String, Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies = httpServletRequest.getCookies();
        if(cookies != null){
            for (Cookie cookie : cookies){
                cookieMap.put(cookie.getName(),cookie);
                System.out.println(cookie.getName()+":"+cookie.getValue());

            }
        }

        return sessionId;
    }

}
