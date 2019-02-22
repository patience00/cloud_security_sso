package com.linchtech.sso.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linchtech.sso.entity.LoginLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 107
 * @since 2019-02-22
 */
@Mapper
@Repository
public interface LoginLogMapper extends BaseMapper<LoginLog> {

}
