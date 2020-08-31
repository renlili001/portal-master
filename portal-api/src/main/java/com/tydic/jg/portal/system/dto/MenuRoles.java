package com.tydic.jg.portal.system.dto;

import lombok.Data;

@Data
public class MenuRoles {
    private int id;
    private String role;
    private int menuId;
    private Boolean disabled;

}
