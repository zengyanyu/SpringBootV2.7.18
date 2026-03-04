/*
 * Copyright (c) 2026, 曾衍育 All rights reserved.
 * 自定义License声明
 * ZENGYANYU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zengyanyu.system.service.impl;

import com.zengyanyu.system.entity.DictItem;
import com.zengyanyu.system.mapper.DictItemMapper;
import com.zengyanyu.system.service.IDictItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.zengyanyu.system.commons.ResponseData;

/**
 *  服务实现类
 * @author zengyanyu
 */
@Service
public class DictItemServiceImpl extends ServiceImpl<DictItemMapper, DictItem> implements IDictItemService {

    /**
     * 保存或更新
     *
     * @param dictItem 
     * @return
     */
    @Override
    public ResponseData saveOrUpdateDictItem(DictItem dictItem) {
        this.saveOrUpdate(dictItem);
        return new ResponseData("保存或更新成功");
    }

}
