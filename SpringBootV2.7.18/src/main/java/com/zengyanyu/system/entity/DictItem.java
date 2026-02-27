package com.zengyanyu.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

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
    private String id;

    private String code;

    private String dictId;

    private String name;


}
