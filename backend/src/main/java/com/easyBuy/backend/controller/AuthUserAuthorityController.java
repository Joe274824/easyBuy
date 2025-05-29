package com.easyBuy.backend.controller;

import com.easyBuy.backend.service.AuthUserAuthorityService;
import com.easyBuy.backend.entity.AuthUserAuthority;
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
@Tag(name = "AuthUserAuthority API")
@RestController
@RequestMapping("/api/authUserAuthority")
public class AuthUserAuthorityController {

    @Autowired
private AuthUserAuthorityService authUserAuthorityService;

/** 新增 */
@PostMapping
public Result<?> add(@RequestBody AuthUserAuthority body) {
    return Result.success(authUserAuthorityService.save(body));
}

/** 修改 */
@PutMapping
public Result<?> update(@RequestBody AuthUserAuthority body) {
    return Result.success(authUserAuthorityService.updateById(body));
}

/** 删除 */
@DeleteMapping("/{id}")
public Result<?> delete(@PathVariable String id) {
    return Result.success(authUserAuthorityService.removeById(id));
}

/** 详情 */
@GetMapping("/{id}")
public Result<AuthUserAuthority> detail(@PathVariable String id) {
    return Result.success(authUserAuthorityService.getById(id));
}

/** 分页列表 */
@GetMapping
public Result<Page<AuthUserAuthority>> list(
        @RequestParam(defaultValue = "1") long page,
        @RequestParam(defaultValue = "10") long size) {
    return Result.success(
                authUserAuthorityService.page(new Page<>(page, size)));
}
}