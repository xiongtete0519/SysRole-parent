package com.atguigu.system.controller;


import cn.hutool.core.lang.tree.Tree;
import com.atguigu.common.result.Result;
import com.atguigu.model.system.SysDept;
import com.atguigu.system.service.SysDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 组织机构 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-12-29
 */
@Api(tags = "部门管理")
@RestController
@RequestMapping("/admin/system/sysDept")
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;

    @ApiOperation("获取部门")
    @GetMapping("findNodes")
    public Result findNodes(){
       List<Tree<String>> list =sysDeptService.findNodes();
       return Result.ok(list);
    }

    @ApiOperation("新增部门")
    @PostMapping("save")
    public Result save(@RequestBody SysDept sysDept){
        boolean isSuccess = sysDeptService.save(sysDept);
        if(isSuccess){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @ApiOperation("修改部门")
    @PutMapping("update")
    public Result updateById(@RequestBody SysDept sysDept){
        sysDeptService.updateById(sysDept);
        return Result.ok();
    }

    @ApiOperation("删除部门")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable String id){
        sysDeptService.removeById(id);
        return Result.ok();
    }


}

