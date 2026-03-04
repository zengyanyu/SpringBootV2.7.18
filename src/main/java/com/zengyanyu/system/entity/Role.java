/*
 * Copyright (c) 2026, 曾衍育 All rights reserved.
 * 自定义License声明
 * ZENGYANYU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zengyanyu.system.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

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
    private String id;

    private String roleCode;

    private String roleName;


}
