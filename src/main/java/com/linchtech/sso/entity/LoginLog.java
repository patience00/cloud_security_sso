package com.linchtech.sso.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 107
 * @since 2019-02-22
 */
@Data
@Accessors(chain = true)
@TableName("login_log")
public class LoginLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * IP地址
     */
    private String ip;
    /**
     * ip归属地
     */
    private String location;
    private String phone;
    /**
     * 用户名
     */
    private String username;
    /**
     * 登陆时间
     */
    @TableField("login_time")
    private Date loginTime;
    /**
     * 退出登陆时间
     */
    @TableField("logout_time")
    private Date logoutTime;


}
