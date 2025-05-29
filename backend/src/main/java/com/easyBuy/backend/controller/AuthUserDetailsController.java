package com.easyBuy.backend.controller;

import com.easyBuy.backend.service.AuthUserDetailsService;
import com.easyBuy.backend.entity.AuthUserDetails;
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
@Tag(name = "AuthUserDetails API")
@RestController
@RequestMapping("/api/authUserDetails")
public class AuthUserDetailsController {

    @Autowired
private AuthUserDetailsService authUserDetailsService;

/** 新增 */
@PostMapping
public Result<?> add(@RequestBody AuthUserDetails body) {
    return Result.success(authUserDetailsService.save(body));
}

/** 修改 */
@PutMapping
public Result<?> update(@RequestBody AuthUserDetails body) {
    return Result.success(authUserDetailsService.updateById(body));
}

/** 删除 */
@DeleteMapping("/{id}")
public Result<?> delete(@PathVariable String id) {
    return Result.success(authUserDetailsService.removeById(id));
}

/** 详情 */
@GetMapping("/{id}")
public Result<AuthUserDetails> detail(@PathVariable String id) {
    return Result.success(authUserDetailsService.getById(id));
}

/** 分页列表 */
@GetMapping
public Result<Page<AuthUserDetails>> list(
        @RequestParam(defaultValue = "1") long page,
        @RequestParam(defaultValue = "10") long size) {
    return Result.success(
                authUserDetailsService.page(new Page<>(page, size)));
}
}