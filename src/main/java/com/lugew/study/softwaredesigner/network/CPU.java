package com.lugew.study.softwaredesigner.network;

/**
 * @author LuGew
 * @since 2020/4/28
 */
public interface CPU extends Hardware {
    /**
     * 程序控制
     */
    void programControl();

    /**
     * 操作控制
     */
    void operationalControl();

    /**
     * 时间控制
     */
    void timeControl();

    /**
     * 数据处理
     */
    void dataProcess();

}
