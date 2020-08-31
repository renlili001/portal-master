package com.tydic.jg.portal.system.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductInfo {
    private int id;
    private String name;
    private String icon;
    private String description;
    private String role;
    private Boolean sider;

    private List<ProductMenus> menus;
}
