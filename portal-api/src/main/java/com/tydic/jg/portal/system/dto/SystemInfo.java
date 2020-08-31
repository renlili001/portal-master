package com.tydic.jg.portal.system.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class SystemInfo {
    private int id;
    private Integer type;
    private String title;
    private String icon;
    private String path;
    private String routerBase;
    private Boolean used;
    private String createBy;
    private String updateBy;
    private Timestamp createTime;
    private Timestamp updateTime;
}
