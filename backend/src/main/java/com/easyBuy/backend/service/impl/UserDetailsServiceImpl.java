package com.easyBuy.backend.service.impl;

import com.easyBuy.backend.entity.AuthUserDetails;
import com.easyBuy.backend.mapper.AuthUserDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AuthUserDetailsMapper userMapper;   // MyBatis-Plus 的 Mapper

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUserDetails user = userMapper.selectByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return User.withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities("ROLE_USER")     // 这里可从 auth_user_authority 里查
                .accountLocked(!user.getEnabled())
                .build();
    }
}
