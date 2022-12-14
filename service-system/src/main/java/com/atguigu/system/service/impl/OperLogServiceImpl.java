package com.atguigu.system.service.impl;

import com.atguigu.model.system.SysOperLog;
import com.atguigu.system.mapper.OperLogMapper;
import com.atguigu.system.service.OperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperLogServiceImpl implements OperLogService {

    @Autowired
    private OperLogMapper operLogMapper;

    @Override
    public void saveSysLog(SysOperLog sysOperLog) {
        operLogMapper.insert(sysOperLog);
    }
}
