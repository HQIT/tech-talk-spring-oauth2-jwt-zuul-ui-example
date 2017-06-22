package com.cloume.ncee.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloume.common.rest.response.RestResponse;

@SpringBootApplication
@EnableResourceServer
@RestController
public class SimpleApiApplication extends ResourceServerConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(SimpleApiApplication.class, args);
	}
	
	@RequestMapping("/test")
	public RestResponse<?> test() {
		return RestResponse.good("hey u");
	}
	
	 @Override
	    public void configure(HttpSecurity http) throws Exception {
	        http.csrf().disable().authorizeRequests().antMatchers("/**").authenticated().antMatchers(HttpMethod.GET, "/test")
	                // 拦截用户，必须具有所列权限
	                .hasAuthority("FOO_READ");
	    }

	    @Override
	    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
	        resources.resourceId("foo").tokenStore(tokenStore());
	    }
	    
	    @Bean
	    protected JwtAccessTokenConverter jwtTokenEnhancer() {
	        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	        converter.setSigningKey("123");
	        return converter;
	    }
	    
	    @Bean
	    public TokenStore tokenStore() {
	        return new JwtTokenStore(jwtTokenEnhancer());
	    }
}
