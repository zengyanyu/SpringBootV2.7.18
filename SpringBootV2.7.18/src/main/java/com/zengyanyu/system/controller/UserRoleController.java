package com.zengyanyu.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.config.LogRecord;
import com.zengyanyu.system.entity.UserRole;
import com.zengyanyu.system.query.UserRoleQueryObject;
import com.zengyanyu.system.service.IUserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zengyanyu
 * @since 2026-02-27
 */
@RestController
@Api(tags = "控制器")
@RequestMapping("/system/user-role")
@Slf4j
public class UserRoleController extends BaseController {

    @Resource
    private IUserRoleService userRoleService;

    @LogRecord("保存或更新")
    @ApiOperation("保存或更新")
    @PostMapping("/save")
    public ResponseData save(@RequestBody UserRole userRole) {
        return userRoleService.saveOrUpdateUserRole(userRole);
    }

    @LogRecord("删除")
    @ApiOperation("删除")
    @GetMapping("/{id}")
    public ResponseData delete(@PathVariable String id) {
        userRoleService.removeById(id);
        return new ResponseData("删除成功");
    }

    @LogRecord("批量删除")
    @ApiOperation("批量删除")
    @PostMapping("/del/batch")
    public ResponseData deleteBatch(@RequestBody List<String> ids) {
        userRoleService.removeByIds(ids);
        return new ResponseData("批量删除成功");
    }

    @LogRecord("查询所有数据")
    @ApiOperation("查询所有数据")
    @GetMapping("/findAll")
    public ResponseData<List<UserRole>> findAll() {
        return new ResponseData("查询所有数据", userRoleService.list());
    }

    @LogRecord("根据ID查询指定数据")
    @ApiOperation("根据ID查询指定数据")
    @GetMapping("/get/{id}")
    public ResponseData<UserRole> get(@PathVariable String id) {
        return new ResponseData("根据ID查询指定数据", userRoleService.getById(id));
    }

    @LogRecord("分页查询数据")
    @ApiOperation("分页查询数据")
    @GetMapping("/page")
    public Page<UserRole> page(UserRoleQueryObject queryObject) {
        QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
        return userRoleService.page(new Page<>(queryObject.getPageNum(), queryObject.getPageSize()), wrapper);
    }
}

