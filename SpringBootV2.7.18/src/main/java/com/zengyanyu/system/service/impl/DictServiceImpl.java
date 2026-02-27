package com.zengyanyu.system.service.impl;

import com.zengyanyu.system.entity.Dict;
import com.zengyanyu.system.mapper.DictMapper;
import com.zengyanyu.system.service.IDictService;
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
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {

    /**
     * 保存或更新
     *
     * @param dict 
     * @return
     */
    @Override
    public ResponseData saveOrUpdateDict(Dict dict) {
        this.saveOrUpdate(dict);
        return new ResponseData("保存或更新成功");
    }

}
