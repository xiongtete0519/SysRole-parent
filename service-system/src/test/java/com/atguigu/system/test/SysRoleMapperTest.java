package com.atguigu.system.test;


import com.atguigu.model.system.SysRole;
import com.atguigu.system.mapper.SysRoleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class SysRoleMapperTest {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Test
    public void findAll(){
        List<SysRole> sysRoles = sysRoleMapper.selectList(null);
        sysRoles.forEach(System.out::println);
    }

    @Test
    public void add(){
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("测试角色");
        sysRole.setRoleCode("testManager");
        sysRole.setDescription("测试角色");
        int rows = sysRoleMapper.insert(sysRole);
        System.out.println(rows);
    }

    @Test
    public void update(){
        SysRole sysRole = sysRoleMapper.selectById(9);
        sysRole.setDescription("测试角色1");
        sysRoleMapper.updateById(sysRole);
    }
    //逻辑删除操作(@TableLogic)
    //逻辑删除 默认效果 0 没有删除 1 已经删除
    @Test
    public void deleteId(){
        int rows = sysRoleMapper.deleteById(9);
    }
    //批量删除
    @Test
    public void deleteBatch(){
        int rows = sysRoleMapper.deleteBatchIds(Arrays.asList(8, 9));
    }
}
