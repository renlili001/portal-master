package com.tydic.jg.portal.system.rest;

import com.tydic.jg.portal.system.dto.MenuInfo;
import com.tydic.jg.portal.system.dto.MenuRoutes;
import com.tydic.jg.portal.system.svc.MenuInfoService;
import com.tydic.jg.portal.utils.Controllers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Collection;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menu")
@Slf4j
@Api(tags = "菜单信息接口")
public class MenuInfoController {

    private final MenuInfoService menuInfoService;

    @PostMapping
    @ApiOperation(value = "新建菜单信息", notes = "新建菜单信息的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "String", name = "menuInfo", value = "菜单信息参数")
    })
    public ResponseEntity<Void> create(@RequestBody MenuInfo menuInfo, HttpServletRequest request) {
        MenuInfo created = menuInfoService.create(menuInfo);
        String location = request.getRequestURL() + "/" + created.getId();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(URI.create(location))
                .build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除菜单信息", notes = "删除菜单信息的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "id", value = "菜单id",required = true)
    })
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        menuInfoService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "修改菜单信息", notes = "修改菜单信息的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "id", value = "菜单id",required = true),
            @ApiImplicitParam(paramType = "body", dataType = "String", name = "menuInfo", value = "菜单信息")
    })
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody MenuInfo menuInfo) {
        menuInfo.setId(id);
        menuInfoService.update(menuInfo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询菜单信息", notes = "根据ID查询菜单信息的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "id", value = "菜单id",required = true)
    })
    public ResponseEntity<MenuInfo> getUser(@PathVariable("id") int id) {
        return ResponseEntity.ok(menuInfoService.getOne(id));
    }

    @GetMapping
    @ApiOperation(value = "查询菜单信息", notes = "查询菜单信息的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "search", value = "搜索"),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "page", value = "分页页数"),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "size", value = "分页大小"),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "sort", value = "ASC DESC 按照id 排序")
    })
    public ResponseEntity<List<MenuInfo>> getMenus(@RequestParam(required = false) String search,
                                                   @RequestParam(required = false) Integer page,
                                                   @RequestParam(required = false) Integer size,
                                                   @RequestParam(required = false) String sort) {

        if(sort != null){
            Sort _sort = sort.equalsIgnoreCase("desc") ? Sort.by(Sort.Direction.DESC, "id") : Sort.by(Sort.Direction.ASC, "id");
            return ResponseEntity.ok(
                    menuInfoService.findAll(search, Controllers.pageable(page, size, _sort))
            );
        } else {
            return ResponseEntity.ok(
                    menuInfoService.findAll(search, Controllers.pageable(page, size))
            );
        }
    }

    @GetMapping("/routes")
    @ApiOperation(value = "查询当前用户拥有权限的路由", notes = "查询Portal页面路由")
    @ApiImplicitParams({
            @ApiImplicitParam()
    })
    public ResponseEntity<List<MenuRoutes>> routes() {

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        return ResponseEntity.ok(menuInfoService.routes(authorities));
    }

    @DeleteMapping
    @ApiOperation(value = "根据ID批量删除菜单信息", notes = "根据ID批量删除菜单信息的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "int", name = "ids", value = "菜单id",required = true),
    })
    public ResponseEntity<Void> deleteByIds(@RequestBody List<Integer> ids) {
        menuInfoService.deleteByIds(ids);
        return ResponseEntity.ok().build();
    }
}
