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
 * 部门
 *
 * @author zengyanyu
 * @since 2026-03-05
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
