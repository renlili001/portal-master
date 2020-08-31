package com.tydic.jg.portal.system.rest;

import com.tydic.jg.portal.system.dto.PageDefine;
import com.tydic.jg.portal.system.svc.PageDefineService;
import com.tydic.jg.portal.utils.Controllers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/page")
@Slf4j
@Api(tags = "页面定义接口")
public class PageDefineController {
    private final PageDefineService pageDefineService;

    @PostMapping
    @ApiOperation(value = "新建分页定义", notes = "新建分页定义的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "String", name = "pageDefine", value = "分页定义信息")
    })
    public ResponseEntity<Void> create(@RequestBody PageDefine pageDefine, HttpServletRequest request) {
        PageDefine created = pageDefineService.create(pageDefine);
        String location = request.getRequestURL() + "/" + created.getId();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(URI.create(location))
                .build();

    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除分页定义", notes = "删除分页定义的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "id", value = "分页id",required = true)
    })
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        pageDefineService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "修改分页定义", notes = "修改分页定义的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "id", value = "分页id",required = true),
            @ApiImplicitParam(paramType = "body", dataType = "String", name = "pageDefine", value = "分页信息")
    })
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody PageDefine pageDefine) {
        pageDefine.setId(id);
        pageDefineService.update(pageDefine);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询分页定义信息", notes = "根据ID查询分页定义的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "id", value = "分页id",required = true)
    })
    public ResponseEntity<PageDefine> getUser(@PathVariable("id") int id) {
        return ResponseEntity.ok(pageDefineService.getOne(id));
    }

    @GetMapping
    @ApiOperation(value = "查询分页定义", notes = "查询分页定义的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "search", value = "搜索"),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "page", value = "分页页数"),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "size", value = "分页大小"),
    })
    public ResponseEntity<List<PageDefine>> getPage(@RequestParam(required = false) String search,
                                                    @RequestParam(required = false) Integer page,
                                                    @RequestParam(required = false) Integer size) {
        return ResponseEntity.ok(pageDefineService.findAll(search, Controllers.pageable(page, size)));
    }

    @DeleteMapping
    @ApiOperation(value = "根据ID批量删除分页定义", notes = "根据ID批量删除分页定义的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "int", name = "ids", value = "分页id",required = true)
    })
    public ResponseEntity<Void> deleteByIds(@RequestBody List<Integer> ids) {
        pageDefineService.deleteByIds(ids);
        return ResponseEntity.ok().build();
    }
}
