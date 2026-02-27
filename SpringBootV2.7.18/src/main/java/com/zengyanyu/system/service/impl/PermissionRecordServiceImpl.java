package com.zengyanyu.system.service.impl;

import com.zengyanyu.system.entity.PermissionRecord;
import com.zengyanyu.system.mapper.PermissionRecordMapper;
import com.zengyanyu.system.service.IPermissionRecordService;
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
public class PermissionRecordServiceImpl extends ServiceImpl<PermissionRecordMapper, PermissionRecord> implements IPermissionRecordService {

    /**
     * 保存或更新
     *
     * @param permissionRecord 
     * @return
     */
    @Override
    public ResponseData saveOrUpdatePermissionRecord(PermissionRecord permissionRecord) {
        this.saveOrUpdate(permissionRecord);
        return new ResponseData("保存或更新成功");
    }

}
