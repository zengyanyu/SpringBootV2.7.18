package com.zengyanyu.system.service;

import com.zengyanyu.system.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zengyanyu.system.commons.ResponseData;

/**
 *  服务类
 *
 * @author zengyanyu
 * @since 2026-02-27
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 保存或更新
     *
     * @param menu 
     * @return
     */
    ResponseData saveOrUpdateMenu(Menu menu);

}
