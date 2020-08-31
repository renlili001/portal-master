package com.tydic.jg.portal.system.dto;

import lombok.Data;

@Data
public class PageDefine {
    private int id;
    private int parentId;
    private Integer pageId;
    private Integer menuId;
    private String menuName;
    private Integer menuOrder;
    private String mountPoint;
    private Boolean cache;
    private Boolean hidden;

}
