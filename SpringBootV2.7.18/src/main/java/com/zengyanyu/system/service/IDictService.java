package com.zengyanyu.system.service;

import com.zengyanyu.system.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zengyanyu.system.commons.ResponseData;

import java.io.InputStream;
import java.util.List;

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

    /**
     * 导入 Excel 文件
     *
     * @param inputStream 文件输入流
     */
    void importExcel(InputStream inputStream);

    /**
     * 批量保存
     *
     * @param dictList
     */
    void batchSave(List<Dict> dictList);

    /**
     * @param code
     * @return
     */
    Boolean selectDataByCondition(String code);
}
