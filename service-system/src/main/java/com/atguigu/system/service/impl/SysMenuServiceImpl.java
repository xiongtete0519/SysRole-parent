package com.atguigu.system.service.impl;

import com.atguigu.model.system.SysMenu;
import com.atguigu.system.exception.GuiguException;
import com.atguigu.system.mapper.SysMenuMapper;
import com.atguigu.system.service.SysMenuService;
import com.atguigu.system.utils.MenuHelper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-12-02
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    //菜单列表
    @Override
    public List<SysMenu> findNodes() {
        //获取所有菜单
        List<SysMenu> sysMenuList = baseMapper.selectList(null);
        //所有菜单数据转换成要求的数据格式
        List<SysMenu> resultList= MenuHelper.buildTree(sysMenuList);

        return resultList;
    }

    //删除菜单
    @Override
    public void removeMenuById(String id) {
        //查询当前删除菜单下面是否有子菜单
        //根据id=parentid
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        Integer count = baseMapper.selectCount(wrapper);
        if(count>0){//有子菜单
            throw new GuiguException(201,"请先删除子菜单");
        }
        //调用删除
        baseMapper.deleteById(id);
    }
}
