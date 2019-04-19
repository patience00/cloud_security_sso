package com.linchtech.sso.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: 107
 * @date: 2019/2/25 11:29
 * @description:
 * @Review:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO<T> {

    @ApiModelProperty("返回码")
    private Integer code;

    @ApiModelProperty("返回描述")
    private String message;

    @ApiModelProperty("返回数据")
    private T data;

    /**
     * 请求成功
     *
     * @return
     */
    public static ResultVO ok() {
        return ResultVO.builder()
                .code(HttpResult.SUCCESS.getCode())
                .message(HttpResult.SUCCESS.getMessage())
                .build();
    }

    public static <T> ResultVO<T> ok(HttpResult httpResult) {
        return ok(httpResult);
    }

    public static <T> ResultVO<T> ok(T data) {
        return ok(HttpResult.SUCCESS, data);
    }

    public static <T> ResultVO<T> ok(HttpResult httpResult, T data) {
        return ResultVO.<T>builder()
                .code(httpResult.getCode())
                .message(httpResult.getMessage())
                .data(data)
                .build();
    }
}
