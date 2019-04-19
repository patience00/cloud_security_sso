package com.linchtech.sso.entity.vo;

import lombok.Getter;

/**
 * @author: 107
 * @date: 2019/2/25 11:33
 * @description:
 * @Review:
 */
@Getter
public enum HttpResult {

    /**
     * 请求成功返回码.
     */
    SUCCESS(0, "请求成功"),

    /**
     * 请求失败返回描述及返回码.
     */
    FAIL(-1, "请求失败");

    private int code;

    private String message;

    HttpResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
