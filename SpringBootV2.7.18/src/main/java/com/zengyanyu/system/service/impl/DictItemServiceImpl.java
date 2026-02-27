package com.zengyanyu.system.service.impl;

import com.zengyanyu.system.entity.DictItem;
import com.zengyanyu.system.mapper.DictItemMapper;
import com.zengyanyu.system.service.IDictItemService;
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
