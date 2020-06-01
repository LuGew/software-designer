/**
 * Copyright (c) 2005-2016, cnfuelteam (fuelteam@163.com)"
 * <p>
 * Licensed under The MIT License (MIT)
 */
package com.lugew.study.softwaredesigner.csv.entity;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import lombok.Data;

import java.util.Date;

@Data
public class BusinessLogForCsv {

    /**
     * 日志编号,自增主键
     */
    @CsvBindByPosition(position = 0)
    private Long id;

    /**
     * 用户编号
     */
    @CsvBindByPosition(position = 1)
    private Long empNo;

    /**
     * 操作模块
     */
    @CsvBindByPosition(position = 2)
    private String module;

    /**
     * 操作内容
     */
    @CsvBindByPosition(position = 3)
    private String content;

    /**
     * 操作类型
     */
    @CsvBindByPosition(position = 4)
    private String operationType;

    /**
     * 操作时间
     */
    @CsvBindByPosition(position = 5)
    @CsvDate("yyyy-MM-dd HH:mm:ss")
    private Date time;

    /**
     * 访问IP
     */
    @CsvBindByPosition(position = 6)
    private String ip;

    /**
     * 入参
     */
    @CsvBindByPosition(position = 7)
    private String inputParam;

    /**
     * 操作状态  1成功；2失败
     */
    @CsvBindByPosition(position = 8)
    private Integer operationStatus;

    /**
     * 失败原因
     */
    @CsvBindByPosition(position = 9)
    private String failCause;

    public BusinessLogForCsv() {
    }

    public BusinessLogForCsv(Long id, Date time, String failCause) {
        this.id = id;
        this.time = time;
        this.failCause = failCause;
    }

    public BusinessLogForCsv(Long id, Long empNo, String module, String content, String operationType, Date time, String ip, String inputParam, Integer operationStatus, String failCause) {
        this.id = id;
        this.empNo = empNo;
        this.module = module;
        this.content = content;
        this.operationType = operationType;
        this.time = time;
        this.ip = ip;
        this.inputParam = inputParam;
        this.operationStatus = operationStatus;
        this.failCause = failCause;
    }
}