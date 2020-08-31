package com.tydic.jg.portal.system.rest;

import com.tydic.jg.portal.system.dto.SystemInfo;
import com.tydic.jg.portal.system.svc.SystemInfoService;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/system")
@Slf4j
@Api(tags = "系统信息接口")
public class SystemInfoController {
    private final SystemInfoService systemInfoService;

    @PostMapping
    @ApiOperation(value = "新建系统信息", notes = "新建系统信息的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "String", name = "systemInfo", value = "系统信息")
    })
    public ResponseEntity<Void> create(@RequestBody SystemInfo systemInfo, HttpServletRequest request) {
        SystemInfo created = systemInfoService.create(systemInfo);
        String location = request.getRequestURL() + "/" + created.getId();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(URI.create(location))
                .build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除系统信息", notes = "删除系统信息的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "id", value = "系统信息id",required = true)
    })
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {

        systemInfoService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "修改系统信息", notes = "修改系统信息的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "id", value = "系统信息id",required = true),
            @ApiImplicitParam(paramType = "body", dataType = "String", name = "systemInfo", value = "系统信息")
    })
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody SystemInfo systemInfo) {
        systemInfo.setId(id);
        systemInfoService.update(systemInfo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询系统信息", notes = "根据ID查询系统信息的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "id", value = "系统信息id",required = true)
    })
    public ResponseEntity<SystemInfo> getUser(@PathVariable("id") int id) {
        return ResponseEntity.ok(systemInfoService.getOne(id));
    }

    @GetMapping
    @ApiOperation(value = "查询系统信息", notes = "查询系统信息的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "search", value = "搜索"),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "page", value = "分页页数"),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "size", value = "分页大小"),
            @ApiImplicitParam(paramType = "sort", dataType = "String", name = "sort", value = "ASC / DESC"),
    })
    public ResponseEntity<List<SystemInfo>> getSystems(@RequestParam(required = false) String search,
                                                       @RequestParam(required = false) Integer page,
                                                       @RequestParam(required = false) Integer size,
                                                       @RequestParam(required = false) String sort) {
        if(sort != null){
            Sort _sort = sort.equalsIgnoreCase("desc") ? Sort.by(Sort.Direction.DESC, "id") : Sort.by(Sort.Direction.ASC, "id");
            return ResponseEntity.ok(systemInfoService.findAll(search, Controllers.pageable(page, size, _sort)));
        } else {
            return ResponseEntity.ok(systemInfoService.findAll(search, Controllers.pageable(page, size)));
        }
    }

    @DeleteMapping
    @ApiOperation(value = "根据ID批量删除系统信息", notes = "根据ID批量删除系统信息的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "int", name = "ids", value = "系统信息id"),
    })
    public ResponseEntity<Void> deleteByIds(@RequestBody List<Integer> ids) {
        systemInfoService.deleteByIds(ids);
        return ResponseEntity.ok().build();
    }
}
