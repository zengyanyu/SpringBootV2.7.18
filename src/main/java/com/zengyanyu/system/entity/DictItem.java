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
@TableName("dict_item")
@ApiModel(value = "DictItem对象", description = "")
public class DictItem implements Serializable {

    @Id
    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("字典项编码")
    private String code;

    @ApiModelProperty("字典主键ID")
    private String dictId;

    @ApiModelProperty("字典项名称")
    private String name;

}
