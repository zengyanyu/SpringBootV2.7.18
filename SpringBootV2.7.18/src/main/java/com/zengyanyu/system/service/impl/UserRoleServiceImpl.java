package com.zengyanyu.system.service.impl;

import com.zengyanyu.system.entity.UserRole;
import com.zengyanyu.system.mapper.UserRoleMapper;
import com.zengyanyu.system.service.IUserRoleService;
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
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    /**
     * 保存或更新
     *
     * @param userRole 
     * @return
     */
    @Override
    public ResponseData saveOrUpdateUserRole(UserRole userRole) {
        this.saveOrUpdate(userRole);
        return new ResponseData("保存或更新成功");
    }

}
