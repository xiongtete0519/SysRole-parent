package com.atguigu.system.service.impl;


import com.atguigu.model.system.SysPost;
import com.atguigu.model.vo.SysPostQueryVo;
import com.atguigu.system.mapper.SysPostMapper;
import com.atguigu.system.service.SysPostService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 岗位信息表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-12-26
 */
@Service
public class SysPostServiceImpl extends ServiceImpl<SysPostMapper, SysPost> implements SysPostService {

    //岗位分页列表
    @Override
    public IPage<SysPost> selectPage(Page<SysPost> pageParam, SysPostQueryVo sysPostQueryVo) {
        LambdaQueryWrapper<SysPost> wrapper = new LambdaQueryWrapper<>();
        String name = sysPostQueryVo.getName(); //岗位编码
        String postCode = sysPostQueryVo.getPostCode(); //岗位名称
        Boolean status = sysPostQueryVo.getStatus();    //状态
        if(!StringUtils.isEmpty(name)){
            wrapper.like(SysPost::getName,name);
        }
        if(!StringUtils.isEmpty(postCode)){
            wrapper.like(SysPost::getPostCode,postCode);
        }
        if(!StringUtils.isEmpty(status)){
            wrapper.eq(SysPost::getStatus,status);
        }
        wrapper.orderByDesc(SysPost::getId);
        Page<SysPost> sysPostPage = baseMapper.selectPage(pageParam, wrapper);
        return sysPostPage;
    }

    //更新状态
    @Override
    public void updateStatus(Long id, Integer status) {
        //根据id查询
        SysPost sysPost = baseMapper.selectById(id);
        //修改状态
        sysPost.setStatus(status);
        //调用方法修改
        baseMapper.updateById(sysPost);
    }
}
