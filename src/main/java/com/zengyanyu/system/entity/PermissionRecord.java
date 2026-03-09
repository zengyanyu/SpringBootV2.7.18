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
 * @since 2026-02-27
 */
@Getter
@Setter
@Entity
@TableName("permission_record")
@ApiModel(value = "PermissionRecord对象", description = "")
public class PermissionRecord implements Serializable {

    @Id
    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("API操作名称")
    private String apiOperationName;

    @ApiModelProperty("方法名称")
    private String methodName;

    @ApiModelProperty("请求地址")
    private String path;

    @ApiModelProperty("请求方法")
    private String requestMethod;

}
