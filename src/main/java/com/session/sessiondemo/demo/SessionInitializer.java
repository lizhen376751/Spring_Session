package com.session.sessiondemo.demo;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

/**
 * Created by Administrator on 2018/4/16 0016.
 */
//初始化Session配置
public class SessionInitializer extends AbstractHttpSessionApplicationInitializer {
    public SessionInitializer() {
        super(SessionConfig.class);
    }
}