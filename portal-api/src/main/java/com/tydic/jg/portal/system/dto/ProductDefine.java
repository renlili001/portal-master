package com.tydic.jg.portal.system.dto;

import lombok.Data;

import java.util.List;


@Data
public class ProductDefine {
    private int id;
    private int parentId;
    private int productId;
    private Integer menuId;
    private String menuName;
    private Integer menuOrder;
    private Boolean cache;
    private Boolean hidden;
    private String icon;

    private List<ProductDefine> children;


}
