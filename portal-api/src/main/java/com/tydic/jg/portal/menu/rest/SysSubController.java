package com.tydic.jg.portal.menu.rest;


import com.tydic.jg.portal.menu.dto.SysSub;
import com.tydic.jg.portal.menu.svc.SysSubService;
import com.tydic.jg.portal.utils.Controllers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subsys")
@Slf4j
@Api(tags = "子系统接口相关")
public class SysSubController {

    private final SysSubService subsysService;

    @PostMapping
    @ApiOperation(value = "新建子系统", notes = "新建子系统的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "String", name = "sysSub", value = "子系统")
    })
    public ResponseEntity<Void> create(@RequestBody SysSub sysSub) {
        subsysService.create(sysSub);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除子系统", notes = "删除子系统的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "id", value = "子系统id",required = true)
    })
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        subsysService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "修改子系统", notes = "修改子系统的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "id", value = "子系统id",required = true),
            @ApiImplicitParam(paramType = "body", dataType = "String", name = "sysSub", value = "子系统")
    })
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody SysSub sysSub) {
        sysSub.setSubsysId(id);
        subsysService.update(sysSub);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @ApiOperation(value = "查询子系统", notes = "查询子系统的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "search", value = "搜索"),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "page", value = "分页页数"),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "size", value = "分页大小"),
    })
    public ResponseEntity<List<SysSub>> getUsers(@RequestParam(required = false) String search,
                                                 @RequestParam(required = false) Integer page,
                                                 @RequestParam(required = false) Integer size) {

        return ResponseEntity.ok(subsysService.findAll(search, Controllers.pageable(page, size)));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询子系统", notes = "根据ID查询子系统的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "id", value = "子系统id",required = true)
    })
    public ResponseEntity<SysSub> getOne(@PathVariable("id") int id) {
        return ResponseEntity.ok(subsysService.getOne(id));

    }

    @GetMapping("/menus")
    @ApiOperation(value = "查询子系统的菜单", notes = "查询子系统的菜单接口")
    @ApiImplicitParams({
            @ApiImplicitParam()
    })
    public ResponseEntity<List<SysSub>> queryMenu() {
        return ResponseEntity.ok(subsysService.queryMenu());

    }
}
