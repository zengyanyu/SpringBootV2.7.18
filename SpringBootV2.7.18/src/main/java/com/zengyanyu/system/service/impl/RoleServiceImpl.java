package com.zengyanyu.system.service.impl;

import com.zengyanyu.system.entity.Role;
import com.zengyanyu.system.mapper.RoleMapper;
import com.zengyanyu.system.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.zengyanyu.system.commons.ResponseData;

/**
 *  服务实现类
 *
 * @author zengyanyu
 * @since 2026-02-27
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    /**
     * 保存或更新
     *
     * @param role 
     * @return
     */
    @Override
    public ResponseData saveOrUpdateRole(Role role) {
        this.saveOrUpdate(role);
        return new ResponseData("保存或更新成功");
    }

}
