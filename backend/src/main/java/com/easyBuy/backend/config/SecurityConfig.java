package com.easyBuy.backend.config;

import com.easyBuy.backend.service.impl.UserDetailsServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 全局安全配置
 *
 * @author Joe Xuanqiao Zhang
 */
@Configuration
@EnableMethodSecurity // 支持 @PreAuthorize 等注解
@RequiredArgsConstructor
public class SecurityConfig {

    // ======== 你自己的 UserDetailsService =========
    //
    // 这里用 @Lazy 是为了避免循环依赖；后面你可以用
    //@Service + Mapper 查询数据库实现自定义 UserDetailsService
    //
    private final @Lazy UserDetailsServiceImpl userDetailsService;

    /**
     * 密码加密器 – 全项目统一使用 BCrypt
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 基于 Dao 的认证提供者
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * AuthenticationManager（有需要时再注入）
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration cfg) throws Exception {
        return cfg.getAuthenticationManager();
    }

    /**
     * 核心安全过滤链
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                // 禁用 CSRF（前后端分离 + Token 场景）————如果你要用表单登录可以删掉这一行
                .csrf(csrf -> csrf.disable())

                // 统一异常处理：未登录返回 401 JSON
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(
                                (req, res, e) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized"))
                )

                // 会话管理（如果后期要做 JWT 可以改成无状态）
                .sessionManagement(Customizer.withDefaults())

                // 路由授权规则
                .authorizeHttpRequests(auth -> auth
                        // ① Swagger / OpenAPI 允许匿名
                        .requestMatchers(
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-resources/**").permitAll()

                        // ② 静态资源允许匿名
                        .requestMatchers(
                                "/", "/index.html",
                                "/favicon.ico",
                                "/css/**", "/js/**", "/img/**").permitAll()

                        // ③ 登录 / 注册接口允许匿名（示例）
                        .requestMatchers("/api/auth/**").permitAll()

                        // ④ 其它所有请求 -> 需要认证
                        .anyRequest().authenticated()
                )

                // 如果你想要表单登录 / 登出，在这里链式开启
                //.formLogin(Customizer.withDefaults())
                //.logout(Customizer.withDefaults())

                // 用户认证方式（Dao / JWT Filter 等）
                .authenticationProvider(daoAuthenticationProvider());

        return http.build();
    }
}
