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
 * @author zengyanyu
 * @since 2026-03-04
 */
@Getter
@Setter
@Entity
@TableName("role")
@ApiModel(value = "Role对象", description = "")
public class Role implements Serializable {

    @Id
    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("角色编码")
    private String roleCode;

    @ApiModelProperty("角色名称")
    private String roleName;

}
