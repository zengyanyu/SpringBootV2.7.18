/*
 * Copyright (c) 2026, 曾衍育 All rights reserved.
 * 自定义License声明
 * ZENGYANYU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zengyanyu.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 部门
 *
 * @author zengyanyu
 * @since 2026-03-04
 */
@Getter
@Setter
@Entity
@TableName("department")
@ApiModel(value = "Department对象", description = "部门")
public class Department implements Serializable {

    @Id
    /**
     * 主键ID
     */
    @ApiModelProperty("主键ID")
    private String id;

    /**
     * 部门名称
     */
    @ApiModelProperty("部门名称")
    private String deptName;

}
