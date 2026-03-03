/*
 *  Copyright (c) 2026, 曾衍育 All rights reserved.
 *  自定义License声明
 *  ZENGYANYU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zengyanyu.system.service;

import com.zengyanyu.system.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zengyanyu.system.commons.ResponseData;

/**
 * 服务类
 *
 * @author zengyanyu
 * @since 2026-02-27
 */
public interface IRoleService extends IService<Role> {

    /**
     * 保存或更新
     *
     * @param role
     * @return
     */
    ResponseData saveOrUpdateRole(Role role);

}
