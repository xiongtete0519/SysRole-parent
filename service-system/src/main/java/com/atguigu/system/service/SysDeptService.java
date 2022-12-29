package com.atguigu.system.service;


import cn.hutool.core.lang.tree.Tree;
import com.atguigu.model.system.SysDept;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 组织机构 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-12-29
 */
public interface SysDeptService extends IService<SysDept> {

    //获取部门
    List<Tree<String>> findNodes();
}
