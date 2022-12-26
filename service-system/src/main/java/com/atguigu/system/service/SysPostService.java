package com.atguigu.system.service;


import com.atguigu.model.system.SysPost;
import com.atguigu.model.vo.SysPostQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 岗位信息表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-12-26
 */
public interface SysPostService extends IService<SysPost> {

    //岗位分页列表
    IPage<SysPost> selectPage(Page<SysPost> pageParam, SysPostQueryVo sysPostQueryVo);

    //更新状态
    void updateStatus(Long id, Integer status);
}
