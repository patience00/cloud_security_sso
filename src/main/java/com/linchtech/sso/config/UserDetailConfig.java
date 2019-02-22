package com.linchtech.sso.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linchtech.sso.entity.BlogUser;
import com.linchtech.sso.entity.Permission;
import com.linchtech.sso.entity.UserPermission;
import com.linchtech.sso.mapper.PermissionMapper;
import com.linchtech.sso.mapper.UserMapper;
import com.linchtech.sso.mapper.UserPermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author: 107
 * @date: 2019/2/21 17:30
 * @description:
 * @Review:
 */
@Service("userDetailsService")
public class UserDetailConfig implements UserDetailsService {

    private final UserMapper userMapper;
    private final UserPermissionMapper userPermissionMapper;
    private final PermissionMapper permissionMapper;

    @Autowired
    public UserDetailConfig(UserMapper userMapper,
                            UserPermissionMapper userPermissionMapper,
                            PermissionMapper permissionMapper) {
        this.userMapper = userMapper;
        this.userPermissionMapper = userPermissionMapper;
        this.permissionMapper = permissionMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        QueryWrapper<BlogUser> wrapper = new QueryWrapper<>();
        wrapper.eq("name", s);
        BlogUser blogUser = userMapper.selectOne(wrapper);
        boolean accountNonLocked = true;
        if (Objects.isNull(blogUser)) {
            throw new UsernameNotFoundException("账号不存在");
        }

        if (blogUser.getBlock()) {
            accountNonLocked = false;
        }
        // 用户的权限列表
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        // spring security 框架的权限需要以 ROLE_ 开头，不然会抛出异常
        final String prefix = "ROLE_";
        UserPermission userPermission = userPermissionMapper.selectOne(new QueryWrapper<>());
        QueryWrapper<Permission> permissionQueryWrapper = new QueryWrapper<>();
        permissionQueryWrapper.eq("id", userPermission.getPermissionId());

        Permission permission = permissionMapper.selectOne(permissionQueryWrapper);
        grantedAuthorityList.add(new SimpleGrantedAuthority(prefix + permission.getCode()));

        return new User(blogUser.getId().toString(), blogUser.getPassword(), true, true, true, accountNonLocked, grantedAuthorityList);
    }
}
