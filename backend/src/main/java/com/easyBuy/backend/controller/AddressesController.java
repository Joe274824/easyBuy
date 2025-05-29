package com.easyBuy.backend.controller;

import com.easyBuy.backend.service.AddressesService;
import com.easyBuy.backend.entity.Addresses;
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
@Tag(name = "Addresses API")
@RestController
@RequestMapping("/api/addresses")
public class AddressesController {

    @Autowired
    private AddressesService addressesService;

/** 新增 */
@PostMapping
public Result<?> add(@RequestBody Addresses body) {
    return Result.success(addressesService.save(body));
}

/** 修改 */
@PutMapping
public Result<?> update(@RequestBody Addresses body) {
    return Result.success(addressesService.updateById(body));
}

/** 删除 */
@DeleteMapping("/{id}")
public Result<?> delete(@PathVariable String id) {
    return Result.success(addressesService.removeById(id));
}

/** 详情 */
@GetMapping("/{id}")
public Result<Addresses> detail(@PathVariable String id) {
    return Result.success(addressesService.getById(id));
}

/** 分页列表 */
@GetMapping
public Result<Page<Addresses>> list(
        @RequestParam(defaultValue = "1") long page,
        @RequestParam(defaultValue = "10") long size) {
    return Result.success(
                addressesService.page(new Page<>(page, size)));
}
}