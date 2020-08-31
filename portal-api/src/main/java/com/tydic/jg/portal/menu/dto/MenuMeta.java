package com.tydic.jg.portal.menu.dto;

import lombok.Data;

@Data
public class MenuMeta{
    private String title;
    private String icon;
    private Boolean noCache;
    private Boolean iFrame;
    private String mount;
    private Boolean subsysShow;
}