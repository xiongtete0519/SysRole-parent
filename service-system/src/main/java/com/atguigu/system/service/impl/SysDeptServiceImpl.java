package com.atguigu.system.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.atguigu.model.system.SysDept;
import com.atguigu.system.mapper.SysDeptMapper;
import com.atguigu.system.service.SysDeptService;
import com.atguigu.system.utils.TreeUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 组织机构 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-12-29
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {


    //获取部门
    @Override
    public List<Tree<String>> findNodes() {
        //全部部门列表
        List<SysDept> sysDeptList = this.list(
                new QueryWrapper<SysDept>()
                        .lambda()
                        .eq(SysDept::getStatus, 1)
        );
        if(CollectionUtils.isEmpty(sysDeptList)){
            return Collections.emptyList();
        }
        TreeNodeConfig treeNodeConfig = TreeUtils.getTreeNodeConfig();
        // “0”-最顶层父id值  一般为0之类 sysDeptList -源数据集合
        //treeNodeConfig - 配置
        return TreeUtil.build(sysDeptList,"0",treeNodeConfig,
                    //treeNode-源数据实体
                    //tree-树节点实体
                    (treeNode,tree)->{
                        tree.setId(treeNode.getId());
                        tree.setParentId(treeNode.getParentId().toString());
                        tree.setWeight(treeNode.getSortValue());
                        tree.setName(treeNode.getName());
                        //扩展属性
                        tree.putExtra("status",treeNode.getStatus());
                        tree.putExtra("phone",treeNode.getPhone());
                        tree.putExtra("createTime",treeNode.getCreateTime());
                        tree.putExtra("leader",treeNode.getLeader());
                        tree.putExtra("sortValue",treeNode.getSortValue());
                        tree.putExtra("origin",treeNode);
                    }
                );

    }
}
