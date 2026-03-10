/*
 * Copyright (c) 2026, 曾衍育 All rights reserved.
 * 自定义License声明
 * ZENGYANYU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zengyanyu.system.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.config.LogRecord;
import com.zengyanyu.system.dto.DepartmentExportExcelDto;
import com.zengyanyu.system.entity.Department;
import com.zengyanyu.system.framework.strategy.CustomColumnWidthStyleStrategy;
import com.zengyanyu.system.query.DepartmentQueryObject;
import com.zengyanyu.system.service.IDepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zengyanyu
 * @since 2026-03-05
 */
@Slf4j
@RestController
@Api(tags = "部门控制器")
@RequestMapping("/department")
public class DepartmentController extends BaseController {

    public DepartmentController() {
        System.out.println("创建departmentController的bean对象,项目启动时创建");
    }

    @Resource
    private IDepartmentService departmentService;

    @LogRecord("保存或更新部门")
    @ApiOperation("保存或更新部门")
    @PostMapping("/save")
    public ResponseData save(@RequestBody Department department) {
        return departmentService.saveOrUpdateDepartment(department);
    }

    @LogRecord("删除部门")
    @ApiOperation("删除部门")
    @GetMapping("/{id}")
    public ResponseData delete(@PathVariable String id) {
        departmentService.removeById(id);
        return new ResponseData("删除成功");
    }

    @LogRecord("批量删除部门")
    @ApiOperation("批量删除部门")
    @PostMapping("/del/batch")
    public ResponseData deleteBatch(@RequestBody List<String> ids) {
        departmentService.removeByIds(ids);
        return new ResponseData("批量删除成功");
    }

    @LogRecord("查询所有数据")
    @ApiOperation("查询所有数据")
    @GetMapping("/findAll")
    public ResponseData<List<Department>> findAll() {
        return new ResponseData("查询所有数据", departmentService.list());
    }

    @LogRecord("根据ID查询指定数据")
    @ApiOperation("根据ID查询指定数据")
    @GetMapping("/get/{id}")
    public ResponseData<Department> get(@PathVariable String id) {
        return new ResponseData("根据ID查询指定数据", departmentService.getById(id));
    }

    @LogRecord("部门分页查询数据")
    @ApiOperation("部门分页查询数据")
    @GetMapping("/page")
    public Page<Department> page(DepartmentQueryObject queryObject) {
        QueryWrapper<Department> wrapper = new QueryWrapper<>();
        return departmentService.page(new Page<>(queryObject.getPageNum(), queryObject.getPageSize()), wrapper);
    }

    @LogRecord("导出Excel文件")
    @ApiOperation("导出Excel文件")
    @PostMapping("/exportExcel")
    public void exportExcel() throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String fileName = URLEncoder.encode("部门列表", StandardCharsets.UTF_8.name());
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName + ".xlsx");

        // 模拟测试数据
        List<DepartmentExportExcelDto> dtoList = new ArrayList<>();
        List<Department> departmentList = departmentService.list();
        for (Department department : departmentList) {
            // 创建对象
            DepartmentExportExcelDto dto = new DepartmentExportExcelDto();
            BeanUtils.copyProperties(department, dto);
            dtoList.add(dto);
        }
        EasyExcel.write(response.getOutputStream(), DepartmentExportExcelDto.class)
                .registerWriteHandler(new CustomColumnWidthStyleStrategy())
                .sheet("部门列表").doWrite(dtoList);
    }
}
