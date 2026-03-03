/*
 *  Copyright (c) 2026, 曾衍育 All rights reserved.
 *  自定义License声明
 *  ZENGYANYU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zengyanyu.system.mapper;

import com.zengyanyu.system.entity.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 部门 Mapper 接口
 *
 * @author zengyanyu
 * @since 2026-02-27
 */
@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {

}
