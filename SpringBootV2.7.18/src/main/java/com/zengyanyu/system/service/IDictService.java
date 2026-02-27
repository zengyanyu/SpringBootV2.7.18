package com.zengyanyu.system.service;

import com.zengyanyu.system.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zengyanyu.system.commons.ResponseData;

/**
 * 服务类
 *
 * @author zengyanyu
 * @since 2026-02-27
 */
public interface IDictService extends IService<Dict> {

    /**
     * 保存或更新
     *
     * @param dict
     * @return
     */
    ResponseData saveOrUpdateDict(Dict dict);

}
