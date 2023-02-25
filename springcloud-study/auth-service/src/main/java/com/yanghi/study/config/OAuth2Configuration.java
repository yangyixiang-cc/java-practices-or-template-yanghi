package com.yanghi.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@EnableAuthorizationServer   //开启验证服务器
@Configuration
public class OAuth2Configuration extends AuthorizationServerConfigurerAdapter {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private JwtTokenEnhancer jwtTokenEnhancer;

    @Resource
    private ClientOAuth2DataConfiguration clientOAuth2DataConfiguration;


    /**
     * 这个方法是对客户端进行配置，一个验证服务器可以预设很多个客户端，
     * 之后这些指定的客户端就可以按照下面指定的方式进行验证
     *
     * @param clients 客户端配置工具
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient(clientOAuth2DataConfiguration.getClientId()) // 客户端标识 ID
                .secret(passwordEncoder.encode(clientOAuth2DataConfiguration.getSecret())) // 客户端安全码
                .authorizedGrantTypes(clientOAuth2DataConfiguration.getGrantTypes()) // 授权类型
                .accessTokenValiditySeconds(clientOAuth2DataConfiguration.getTokenValidityTime()) // token 有效期
                .refreshTokenValiditySeconds(clientOAuth2DataConfiguration.getRefreshTokenValidityTime()) // 刷新 token 的有效期
                .scopes(clientOAuth2DataConfiguration.getScopes()); // 客户端访问范围
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.allowFormAuthenticationForClients();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> delegates = new ArrayList<>();
        delegates.add(jwtTokenEnhancer);
        delegates.add(accessTokenConverter());
        enhancerChain.setTokenEnhancers(delegates);
        endpoints
                .authenticationManager(authenticationManager)
                .reuseRefreshTokens(true)
                .tokenStore(jwtTokenStore())
                .accessTokenConverter(accessTokenConverter())
                .tokenEnhancer(enhancerChain);
    }

    private AuthorizationServerTokenServices serverTokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setTokenStore(jwtTokenStore());
        return defaultTokenServices;
    }


    @Bean
    public JwtTokenStore jwtTokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(clientOAuth2DataConfiguration.getSigningKey());
        return jwtAccessTokenConverter;
    }

}