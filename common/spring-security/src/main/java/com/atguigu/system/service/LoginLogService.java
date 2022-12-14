package com.atguigu.system.service;

public interface LoginLogService {

    //登录日志
    public void recordLoginLog(String username,Integer status,
                               String ipaddr,String message);
}
