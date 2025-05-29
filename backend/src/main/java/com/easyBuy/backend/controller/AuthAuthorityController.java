package com.easyBuy.backend.controller;

import com.easyBuy.backend.service.AuthAuthorityService;
import com.easyBuy.backend.entity.AuthAuthority;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easyBuy.backend.common.Result;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
            import io.swagger.v3.oas.annotations.tags.Tag;

/**
 *  Controller
 * @author Joe Xuanqiao Zhang
 * @since 29-05-2025
 */
@Tag(name = "AuthAuthority API")
@RestController
@RequestMapping("/api/authAuthority")
public class AuthAuthorityController {

    @Autowired
private AuthAuthorityService authAuthorityService;

/** 新增 */
@PostMapping
public Result<?> add(@RequestBody AuthAuthority body) {
    return Result.success(authAuthorityService.save(body));
}

/** 修改 */
@PutMapping
public Result<?> update(@RequestBody AuthAuthority body) {
    return Result.success(authAuthorityService.updateById(body));
}

/** 删除 */
@DeleteMapping("/{id}")
public Result<?> delete(@PathVariable String id) {
    return Result.success(authAuthorityService.removeById(id));
}

/** 详情 */
@GetMapping("/{id}")
public Result<AuthAuthority> detail(@PathVariable String id) {
    return Result.success(authAuthorityService.getById(id));
}

/** 分页列表 */
@GetMapping
public Result<Page<AuthAuthority>> list(
        @RequestParam(defaultValue = "1") long page,
        @RequestParam(defaultValue = "10") long size) {
    return Result.success(
                authAuthorityService.page(new Page<>(page, size)));
}
}