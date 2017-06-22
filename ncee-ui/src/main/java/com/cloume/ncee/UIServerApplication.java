package com.cloume.ncee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableOAuth2Sso
@EnableZuulProxy
@Controller
public class UIServerApplication extends WebSecurityConfigurerAdapter
{
	public static void main(String[] args) {
		SpringApplication.run(UIServerApplication.class, args);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/login", "/api/**").permitAll()
		.anyRequest().authenticated()
		.and().csrf().disable();
	}
	
	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}
}
