package com.easyBuy.backend.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用 API 响应包装
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    /** 业务码：0=成功，其它=失败 */
    private Integer code;
    /** 提示信息 */
    private String  message;
    /** 载荷数据 */
    private T       data;

    /* ---------- 工具方法 ---------- */

    /** 成功（无数据） */
    public static Result<Void> success() {
        return new Result<Void>(0, "OK", null);  // 显式 <Void>
    }

    /** 成功（带数据） */
    public static <T> Result<T> success(T data) {
        return new Result<T>(0, "OK", data);     // 由 data 推断 T
    }

    /** 自定义成功消息 */
    public static <T> Result<T> success(String message, T data) {
        return new Result<T>(0, message, data);
    }

    /** 失败（默认业务码 = -1） */
    public static Result<Void> fail(String message) {
        return new Result<Void>(-1, message, null); // 显式 <Void>
    }

    /** 自定义业务码 + 失败消息 */
    public static Result<Void> fail(int code, String message) {
        return new Result<Void>(code, message, null);
    }
}
