package com.bookshop.controller.authentication;


import com.bookshop.constant.AppConstants;
import com.bookshop.dto.ListResponse;
import com.bookshop.dto.authentication.RoleRequest;
import com.bookshop.dto.authentication.RoleResponse;
import com.bookshop.dto.product.CategoryRequest;
import com.bookshop.dto.product.CategoryResponse;
import com.bookshop.service.authentication.RoleService;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@AllArgsConstructor
@CrossOrigin(AppConstants.FRONTEND_HOST)
public class RoleController {
    private final RoleService roleService;

    @GetMapping("")
    public ResponseEntity<ListResponse<RoleResponse>> getAllRoles(
           @RequestParam(name = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(name = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(name = "sort", defaultValue = AppConstants.DEFAULT_SORT) String sort,
            @RequestParam(name = "filter", required = false) @Nullable String filter,
            @RequestParam(name = "search", required = false) @Nullable String search,
            @RequestParam(name = "all", required = false) boolean all
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.findAll(page, size, sort, filter, search, all));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleResponse> getRole(@PathVariable("id") Long roleId){
        return ResponseEntity.status(HttpStatus.OK).body(roleService.findById(roleId));
    }

    @PostMapping("")
    public ResponseEntity<RoleResponse> createRole(@RequestBody RoleRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleResponse> updateRole(@PathVariable("id") Long roleId, @RequestBody RoleRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(roleService.save(roleId, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable("id") Long roleId){
        roleService.delete(roleId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteRoles(@RequestBody List<Long> roleIds){
        roleService.delete(roleIds);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}