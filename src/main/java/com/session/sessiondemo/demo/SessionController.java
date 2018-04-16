package com.session.sessiondemo.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lizhen on 2018/4/16 0016.
 */
@RestController
public class SessionController {

    @Value("${server.port}")
    private String PORT;

    public static void main(String[] args) {
        SpringApplication.run(SessionController.class, args);
    }

    @RequestMapping("/index")
    public String index() {
        return "index:" + PORT;
    }

    @RequestMapping(value = "/first", method = RequestMethod.GET)
    public Map<String, Object> firstResp (HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        request.getSession().setAttribute("requestUrl", request.getRequestURL());
        map.put("request Url", request.getRequestURL());
        return map;
    }

    @RequestMapping(value = "/sessions", method = RequestMethod.GET)
    public Object sessions (HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", request.getSession().getId());
        map.put("message", request.getSession().getAttribute("requestUrl"));
        return map;
    }

    /**
     *
     * @methodDesc: 功能描述:(往session存放值)
     * sessionKey，sessionValue是通过url穿过来的一个值，然后将其保存在session中
     */
    @RequestMapping("/setSession")
    public String setSession(HttpServletRequest request, String sessionKey, String sessionValue) {
        HttpSession session = request.getSession(true);
        session.setAttribute(sessionKey, sessionValue);
        return "success,port:" + PORT;
    }

    /**
     *
     * @methodDesc: 功能描述:(从Session获取值)
     * 这边只需要穿过sessionkey便可将value的值取出来
     */
    @RequestMapping("/getSession")
    public String getSession(HttpServletRequest request, String sessionKey) {
        HttpSession session =null;
        try {
            session = request.getSession(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String value=null;
        if(session!=null){
            value = (String) session.getAttribute(sessionKey);
        }
        return "sessionValue:" + value + ",port:" + PORT;
    }

}
