package com.tydic.jg.portal.system.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductMenus implements Serializable {

    private int id;
    private String productName;
    private int parentId;
    private int productId;
    private int menuId;
    private int menuOrder;
    private int type;
    private String title;
    private String name;
    private int sysId;
    private String icon;
    private String component;
    private String routerBase;
    private String path;
    private Boolean external;
    private Boolean cache;
    private Boolean hidden;

    private List<ProductMenus> children;
}
