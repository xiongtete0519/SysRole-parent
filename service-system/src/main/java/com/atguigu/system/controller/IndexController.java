package com.atguigu.system.controller;

import com.atguigu.common.result.Result;
import com.atguigu.common.utils.JwtHelper;
import com.atguigu.common.utils.MD5;
import com.atguigu.model.system.SysUser;
import com.atguigu.model.vo.LoginVo;
import com.atguigu.system.exception.GuiguException;
import com.atguigu.system.service.SysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "用户登录接口")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {

    @Autowired
    private SysUserService sysUserService;

    //login
    //{code: 20000, data: {token: "admin-token"}}
    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo){
        //根据用户名称查询数据库
        SysUser sysUser=sysUserService.getUserInfoByUserName(loginVo.getUsername());
        //如果查询为空
        if(sysUser==null){
            throw new GuiguException(20001,"用户不存在!");
        }
        //判断密码是否一致
        String password = loginVo.getPassword();
        String md5Password = MD5.encrypt(password);
        if(!sysUser.getPassword().equals(md5Password)){
            throw new GuiguException(20001,"密码不正确!");
        }
        //判断用户是否可用
        if(sysUser.getStatus() ==0){
            throw new GuiguException(20001,"用户已经被禁用!");
        }
        //根据userid和username生成token字符串，通过map返回
        String token = JwtHelper.createToken(sysUser.getId(), sysUser.getUsername());
        Map<String, Object> map = new HashMap<>();
        map.put("token",token);
        return Result.ok(map);
    }

    //info
    @GetMapping("info")
    public Result info(){
        Map<String, Object> map = new HashMap<>();
        map.put("roles","[admin]");
        map.put("introduction","I am s super administrator");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("name","Super Admin atguigu");
        return Result.ok(map);
    }
}
