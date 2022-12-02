package com.atguigu.system.controller;

import com.atguigu.common.result.Result;
import com.atguigu.model.system.SysRole;
import com.atguigu.model.vo.SysRoleQueryVo;
import com.atguigu.system.exception.GuiguException;
import com.atguigu.system.service.SysRoleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(tags = "角色管理接口")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    //查询所有记录
    @ApiOperation("查询所有角色")
    @GetMapping("findAll")
    public Result findAll() {
        // 模拟异常效果
//        try {
//            int i =9/0;
//        } catch (Exception e) {
//            //手动抛出异常
//            throw new GuiguException(20001,"执行了自定义异常处理");
//        }
        List<SysRole> roleList = sysRoleService.list();
        return Result.ok(roleList);
    }

    //逻辑删除接口
    @ApiOperation("逻辑删除接口")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable String id){
        boolean isSuccess = sysRoleService.removeById(id);
        if(isSuccess){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }
    //条件分页查询
    @ApiOperation("条件分页查询")
    @GetMapping("{page}/{limit}")
    public Result findPageQueryRole(@PathVariable Long page,
                                    @PathVariable Long limit,
                                    SysRoleQueryVo sysRoleQueryVo){
        //创建page对象
        Page<SysRole> pageParam=new Page<>(page,limit);
        //调用service方法
        IPage<SysRole> pageModel=sysRoleService.selectPage(pageParam,sysRoleQueryVo);
        return Result.ok(pageModel);
    }

    //添加
    //@RequestBody 不能使用get提交方式
    //传递json格式数据，把json格式数据封装到对象里面
    @ApiOperation("添加角色")
    @PostMapping("save")
    public Result saveRole(@RequestBody SysRole sysRole){
        boolean isSuccess = sysRoleService.save(sysRole);
        if(isSuccess){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }
    //根据id查询
    @ApiOperation("根据id查询")
    @PostMapping("findRoleById/{id}")
    public Result findRoleById(@PathVariable Long id){
        SysRole sysRole = sysRoleService.getById(id);
        return Result.ok(sysRole);
    }
    //最终修改
    @ApiOperation("最终修改")
    @PostMapping("update")
    public Result updateRole(@RequestBody SysRole sysRole){
        boolean isSuccess = sysRoleService.updateById(sysRole);
        if(isSuccess){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }
    //批量删除[1,2,3]
    //json数组格式对应Java的list集合
    @ApiOperation("批量删除")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> ids){
        sysRoleService.removeByIds(ids);
        return Result.ok();
    }
}
