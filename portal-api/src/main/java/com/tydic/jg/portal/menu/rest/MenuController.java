package com.tydic.jg.portal.menu.rest;

import com.tydic.jg.portal.menu.dto.Menu;
import com.tydic.jg.portal.menu.dto.MenuBuild;
import com.tydic.jg.portal.menu.svc.MenuService;
import com.tydic.jg.portal.utils.Controllers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menus")
@Slf4j
@Api(tags = "菜单接口相关")
public class MenuController {

    private final MenuService menuService;

    @PostMapping
    @ApiOperation(value = "新建菜单", notes = "新建菜单的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "String", name = "menu", value = "菜单")
    })
    public ResponseEntity<Void> create(@RequestBody Menu menu) {
        menuService.create(menu);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除菜单", notes = "删除菜单的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "id", value = "菜单id",required = true)
    })
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        menuService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "修改菜单", notes = "修改菜单的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "id", value = "菜单id",required = true),
            @ApiImplicitParam(paramType = "body", dataType = "String", name = "menu", value = "菜单")
    })
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody Menu menu) {
        menu.setMenuId(id);
        menuService.update(menu);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询菜单", notes = "根据ID查询菜单的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "id", value = "菜单id",required = true),
    })
    public ResponseEntity<Menu> getUser(@PathVariable("id") int id) {
        return ResponseEntity.ok(menuService.getOne(id));
    }

    @GetMapping
    @ApiOperation(value = "查询菜单", notes = "查询菜单的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "search", value = "搜索"),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "page", value = "分页页数"),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "size", value = "分页大小"),
    })
    public ResponseEntity<List<Menu>> getMenus(@RequestParam(required = false) String search,
                                               @RequestParam(required = false) Integer page,
                                               @RequestParam(required = false) Integer size) {
        return ResponseEntity.ok(menuService.findAll(search, Controllers.pageable(page, size)));
    }

    @GetMapping("/build")
    @ApiOperation(value = "查询三级菜单", notes = "查询三级菜单的接口")
    @ApiImplicitParams({
            @ApiImplicitParam()
    })
    public ResponseEntity<List<MenuBuild>> buildMen() {

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        return ResponseEntity.ok(menuService.buildMen(authorities));

    }

    @DeleteMapping
    @ApiOperation(value = "根据ID批量删除菜单", notes = "根据ID批量删除菜单的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "id", value = "菜单id",required = true),
    })
    public ResponseEntity<Void> deleteByIds(@RequestBody List<Integer> ids) {
        menuService.deleteByIds(ids);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/menuTree")
    @ApiOperation(value = "查询多级菜单", notes = "查询多级菜单的接口")
    @ApiImplicitParams({
            @ApiImplicitParam()
    })
    public ResponseEntity<List<Menu>> menuTree() {
        return ResponseEntity.ok(menuService.menuTree());
    }


}
