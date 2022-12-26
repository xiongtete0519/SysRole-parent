package com.atguigu.system.controller;


import com.atguigu.common.result.Result;
import com.atguigu.model.system.SysPost;
import com.atguigu.model.vo.SysPostQueryVo;
import com.atguigu.system.annotation.Log;
import com.atguigu.system.enums.BusinessType;
import com.atguigu.system.service.SysPostService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 岗位信息表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-12-26
 */
@Api(tags = "岗位管理")
@RestController
@RequestMapping("/admin/system/sysPost")
public class SysPostController {

    @Autowired
    private SysPostService sysPostService;

    @Log(title = "获取全部岗位列表")
    @ApiOperation("获取全部岗位列表")
    @GetMapping("findAll")
    public Result findAll(){
        LambdaQueryWrapper<SysPost> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysPost::getStatus,1);
        List<SysPost> list = sysPostService.list(wrapper);
        return Result.ok(list);
    }

    @ApiOperation("获取岗位分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "postQueryVo", value = "查询对象", required = false)
            SysPostQueryVo sysPostQueryVo){
        Page<SysPost> pageParam = new Page<>(page, limit);
        IPage<SysPost> pageModel=sysPostService.selectPage(pageParam,sysPostQueryVo);
        return Result.ok(pageModel);
    }

    @PreAuthorize("hasAuthority('bnt.sysPost.list')")
    @ApiOperation("根据id查询岗位")
    @GetMapping("/get/{id}")
    public Result getPost(@PathVariable Long id){
        SysPost sysPost = sysPostService.getById(id);
        return Result.ok(sysPost);
    }

    @Log(title = "新增岗位",businessType = BusinessType.INSERT)
    @PreAuthorize("hasAuthority('bnt.sysPost.add')")
    @ApiOperation("新增岗位")
    @PostMapping("/save")
    public Result save(@RequestBody SysPost sysPost){
        boolean isSuccess = sysPostService.save(sysPost);
        if(isSuccess){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @Log(title = "修改岗位",businessType = BusinessType.UPDATE)
    @PreAuthorize("hasAuthority('bnt.sysPost.update')")
    @ApiOperation("修改岗位")
    @PutMapping("/update")
    public Result updateById(@RequestBody SysPost post){
        sysPostService.updateById(post);
        return Result.ok();
    }

    @Log(title = "删除岗位",businessType = BusinessType.DELETE)
    @PreAuthorize("hasAuthority('bnt.sysPost.remove')")
    @ApiOperation("删除岗位")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id){
        sysPostService.removeById(id);
        return Result.ok();
    }

    @Log(title = "批量删除岗位",businessType = BusinessType.DELETE)
    @PreAuthorize("hasAuthority('bnt.sysPost.remove')")
    @ApiOperation("批量删除岗位")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList){
        sysPostService.removeByIds(idList);
        return Result.ok();
    }

    @Log(title ="更新状态",businessType =BusinessType.UPDATE)
    @GetMapping("/updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable Long id,
                               @PathVariable Integer status){
        sysPostService.updateStatus(id,status);
        return Result.ok();
    }



}

