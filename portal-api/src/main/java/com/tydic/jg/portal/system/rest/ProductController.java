package com.tydic.jg.portal.system.rest;

import com.tydic.jg.portal.system.dto.ProductDefine;
import com.tydic.jg.portal.system.dto.ProductInfo;
import com.tydic.jg.portal.system.dto.ProductMenus;
import com.tydic.jg.portal.system.svc.ProductDefineService;
import com.tydic.jg.portal.system.svc.ProductInfoService;
import com.tydic.jg.portal.utils.Controllers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.admin.client.resource.*;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
@Slf4j
@Api(tags = "产品信息接口")
public class ProductController {
    private final ProductInfoService productInfoService;
    private final ProductDefineService productDefineService;
    @Autowired
    private ClientResource productResource;
    @Autowired
    private UsersResource usersResource;
    @Autowired
    private RolesResource rolesResource;

    @PostMapping
    @ApiOperation(value = "新建产品信息", notes = "新建产品信息的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "String", name = "productInfo", value = "分页定义信息")
    })
    public ResponseEntity<Void> create(@RequestBody ProductInfo productInfo, HttpServletRequest request) {
        ProductInfo created = productInfoService.create(productInfo);
        String location = request.getRequestURL() + "/" + created.getId();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(URI.create(location))
                .build();
    }

    @PutMapping("/{id}/users")
    public ResponseEntity<Void> subscribe(@PathVariable("id") int id) {
        productInfoService.subscribe(id);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @DeleteMapping("/{id}/users")
    public ResponseEntity<Void> unSubscribe(@PathVariable("id") int id) {
        productInfoService.unSubscribe(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping("/{id}/users")
    public ResponseEntity<Set<UserRepresentation>> getSubscribeUsers(@PathVariable("id") int id,
                                                                     @RequestParam(required = false) Integer page,
                                                                     @RequestParam(required = false) Integer size) {

        final ProductInfo productInfo = productInfoService.getOne(id);
        String role = productInfo.getRole();
        RoleResource roleResource;
        if(role.startsWith("product.")) {
            role = role.substring("product.".length());
            roleResource = productResource.roles().get(role);
        }else{
            roleResource = rolesResource.get(role);
        }
        int first = page == null ? 0 : (page - 1) * size;
        int max = size == null ? first + 100 : first + size;
        return ResponseEntity
                .ok(roleResource.getRoleUserMembers(first, max));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除产品信息", notes = "删除产品信息的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "id", value = "产品id", required = true)
    })
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        productInfoService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "修改产品信息", notes = "修改产品信息的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "id", value = "产品id", required = true),
            @ApiImplicitParam(paramType = "body", dataType = "String", name = "productInfo", value = "产品信息")
    })
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody ProductInfo productInfo) {
        productInfoService.update(productInfo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询产品信息", notes = "根据ID查询产品信息的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "id", value = "产品id", required = true)
    })
    public ResponseEntity<ProductInfo> getProduct(@PathVariable("id") int id) {
        return ResponseEntity.ok(productInfoService.getOne(id));
    }

    @GetMapping
    @ApiOperation(value = "查询产品信息", notes = "查询产品信息的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "search", value = "搜索"),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "page", value = "分页页数"),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "size", value = "分页大小"),
            @ApiImplicitParam(paramType = "sort", dataType = "String", name = "size", value = "ASC / DESC "),

    })
    public ResponseEntity<List<ProductInfo>> getProduct(@RequestParam(required = false) String search,
                                                        @RequestParam(required = false) Integer page,
                                                        @RequestParam(required = false) Integer size,
                                                        @RequestParam(required = false) String sort) {
        if (sort != null) {
            Sort _sort = sort.equalsIgnoreCase("desc") ? Sort.by(Sort.Direction.DESC, "id") : Sort.by(Sort.Direction.ASC, "id");
            return ResponseEntity.ok(productInfoService.findAll(search, Controllers.pageable(page, size, _sort)));
        } else {
            return ResponseEntity.ok(productInfoService.findAll(search, Controllers.pageable(page, size)));
        }
    }

    @DeleteMapping
    @ApiOperation(value = "根据ID批量删除产品信息", notes = "根据ID批量删除产品信息的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "int", name = "ids", value = "产品id", required = true)
    })
    public ResponseEntity<Void> deleteByIds(@RequestBody List<Integer> ids) {
        productInfoService.deleteByIds(ids);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/my")
    @ApiOperation(value = "获取当前用户所有订购的产品列表", notes = "当前用户所有订购的产品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "Boolean", name = "menus", value = "产品")
    })
    public ResponseEntity<List<ProductInfo>> getByRoles(@RequestParam(required = false) boolean menus) {
        Collection<? extends GrantedAuthority> authorities = getGrantedAuthorities();

        return ResponseEntity.ok(productInfoService.getByRoles(authorities, menus));
    }

    @GetMapping("/my/{infoId}")
    @ApiOperation(value = "用于生成单个产品的菜单树", notes = "用户产品的菜单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "infoId", value = "产品id", required = true)
    })
    public ResponseEntity<List<ProductMenus>> getProductMenu(@PathVariable("infoId") Integer infoId) {

        Collection<? extends GrantedAuthority> authorities = getGrantedAuthorities();

        return ResponseEntity.ok(productInfoService.getProductMenu(infoId, authorities));
    }

    private Collection<? extends GrantedAuthority> getGrantedAuthorities() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        final UserResource currentUser = getCurrentUser();
        final List<RoleRepresentation> roleRepresentations = currentUser.roles().clientLevel("product").listAll();

        return Stream.concat(authentication.getAuthorities().stream(),
                roleRepresentations.stream()
                        .map(roleRepresentation -> (GrantedAuthority) () -> roleRepresentation.getContainerId() + "." + roleRepresentation.getName())
                        .collect(Collectors.toList())
                        .stream()
        ).collect(Collectors.toSet());
    }
    private UserResource getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        final KeycloakPrincipal principal = (KeycloakPrincipal) authentication.getPrincipal();
        final String userId = principal.getKeycloakSecurityContext().getToken().getSubject();
        return usersResource.get(userId);
    }

    @PostMapping("/{infoId}/define")
    @ApiOperation(value = "新建产品定义", notes = "新建产品定义的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "infoId", value = "产品id", required = true),
            @ApiImplicitParam(paramType = "body", dataType = "String", name = "productDefine", value = "产品定义信息")
    })
    public ResponseEntity<Void> createProductDefine(@PathVariable("infoId") Integer infoId, @RequestBody ProductDefine productDefine, HttpServletRequest request) {
        if (infoId != null) {
            productDefine.setProductId(infoId);
        }
        ProductDefine created = productDefineService.create(productDefine);
        String location = request.getRequestURL() + "/" + created.getId();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(URI.create(location))
                .build();
    }

    @DeleteMapping("/{infoId}/define/{defineId}")
    @ApiOperation(value = "删除产品定义", notes = "删除产品定义的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "infoId", value = "产品id", required = true),
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "defineId", value = "产品定义id", required = true)
    })
    public ResponseEntity<Void> deleteProductDefine(@PathVariable("infoId") Integer infoId, @PathVariable("defineId") int defineId) {

        if (infoId != null) {
            productDefineService.delete(defineId);
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{infoId}/define/{defineId}")
    @ApiOperation(value = "修改产品定义", notes = "修改产品定义的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "infoId", value = "产品id", required = true),
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "defineId", value = "产品定义id", required = true),
            @ApiImplicitParam(paramType = "body", dataType = "String", name = "productDefine", value = "产品定义")
    })
    public ResponseEntity<Void> updateProductDefine(@PathVariable("infoId") Integer infoId, @PathVariable("defineId") int defineId, @RequestBody ProductDefine productDefine) {
        productDefine.setId(defineId);
        if (infoId != null) {
            productDefineService.update(productDefine);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{infoId}/define/{defineId}")
    @ApiOperation(value = "根据ID查询产品定义", notes = "根据ID查询产品定义的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "infoId", value = "产品id", required = true),
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "defineId", value = "产品定义id", required = true),
            @ApiImplicitParam(paramType = "body", dataType = "String", name = "productDefine", value = "产品定义")
    })
    public ResponseEntity<ProductDefine> getProductDefine(@PathVariable("infoId") Integer infoId, @PathVariable("defineId") int defineId, @RequestBody ProductDefine productDefine) {
        if (infoId != null) {
            productDefine.setProductId(infoId);
        }
        return ResponseEntity.ok(productDefineService.getOne(defineId));
    }

    @GetMapping("/{infoId}/define")
    @ApiOperation(value = "查询产品定义", notes = "查询产品定义的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "infoId", value = "产品id"),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "search", value = "搜索"),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "page", value = "分页页数"),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "size", value = "分页大小"),
            @ApiImplicitParam(paramType = "query", dataType = "boolean", name = "struct", value = "是否显示结构化，与搜索分页互斥")
    })
    public ResponseEntity<List<ProductDefine>> getProductDefine(@PathVariable("infoId") Integer infoId,
                                                                @RequestParam(required = false) String search,
                                                                @RequestParam(required = false) Integer page,
                                                                @RequestParam(required = false) Integer size,
                                                                @RequestParam(required = false, defaultValue = "true") boolean struct) {

        if (struct) {
            return ResponseEntity.ok(productDefineService.findByProduct(infoId));
        } else {
            return ResponseEntity.ok(productDefineService.findAll(search, Controllers.pageable(page, size))
                    .stream()
                    .filter(productDefine -> productDefine.getProductId() == infoId)
                    .collect(Collectors.toList())
            );
        }
    }

    @DeleteMapping("/{infoId}/define")
    @ApiOperation(value = "根据ID批量删除产品定义", notes = "根据ID批量删除产品定义的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "infoId", value = "产品信息id", required = true),
            @ApiImplicitParam(paramType = "body", dataType = "int", name = "ids", value = "产品ids", required = true),
    })
    public ResponseEntity<Void> deleteDefineByIds(@PathVariable("infoId") Integer infoId, @RequestBody List<Integer> ids) {
        if (infoId != null) {
            productDefineService.deleteByIds(ids);
        }
        return ResponseEntity.ok().build();
    }

}
