package com.linchtech.sso.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linchtech.sso.entity.UserPermission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author: 107
 * @date: 2019/2/21 18:07
 * @description:
 * @Review:
 */
@Repository
@Mapper
public interface UserPermissionMapper extends BaseMapper<UserPermission> {
}
