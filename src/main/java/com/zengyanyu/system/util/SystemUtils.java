/*
 * Copyright (c) 2026, 曾衍育 All rights reserved.
 * 自定义License声明
 * ZENGYANYU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zengyanyu.system.util;

/**
 * @author zengyanyu
 */
public class SystemUtils {

    private SystemUtils() {
    }

    /**
     * 系统跟目录
     */
    public static final String ROOT_DIR = System.getProperty("user.dir");

    public static final String SYS_NAME = System.getProperty("os.name");
}
