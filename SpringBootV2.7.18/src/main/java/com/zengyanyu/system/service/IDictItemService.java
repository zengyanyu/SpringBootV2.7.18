package com.zengyanyu.system.service;

import com.zengyanyu.system.entity.DictItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zengyanyu.system.commons.ResponseData;

/**
 * 服务类
 *
 * @author zengyanyu
 * @since 2026-02-27
 */
public interface IDictItemService extends IService<DictItem> {

    /**
     * 保存或更新
     *
     * @param dictItem
     * @return
     */
    ResponseData saveOrUpdateDictItem(DictItem dictItem);

}
