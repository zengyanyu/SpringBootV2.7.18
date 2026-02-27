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
 * 
 *
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
    private String id;

    @Id
    private String apiOperationName;

    private String methodName;

    private String path;

    private String requestMethod;


}
