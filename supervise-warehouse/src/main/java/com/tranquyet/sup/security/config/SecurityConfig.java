package com.tranquyet.sup.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tranquyet.sup.enums.RolePermission;
import com.tranquyet.sup.security.jwt.JwtAuthenticationEntryPoint;
import com.tranquyet.sup.security.jwt.JwtAuthenticationFilter;
import com.tranquyet.sup.security.service.AuthService;
import com.tranquyet.sup.security.service.CustomUserDetailService;

import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private CustomUserDetailService CustomUserDetailService;
	private JwtAuthenticationEntryPoint unauthorizedHandler;
	@Autowired
	private AuthService authService;

	@Autowired
	public SecurityConfig(CustomUserDetailService CustomUserDetailService,
			JwtAuthenticationEntryPoint unauthorizedHandler, AuthService authService) {
		this.CustomUserDetailService = CustomUserDetailService;
		this.unauthorizedHandler = unauthorizedHandler;
	}

	public SecurityConfig(boolean disableDefaults, CustomUserDetailService CustomUserDetailService,
			JwtAuthenticationEntryPoint unauthorizedHandler) {
		super(disableDefaults);
		this.CustomUserDetailService = CustomUserDetailService;
		this.unauthorizedHandler = unauthorizedHandler;
	}

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter(HttpSecurity http) {
		return new JwtAuthenticationFilter();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(CustomUserDetailService).passwordEncoder(passwordEncoder());
		log.info("Check");
	}

	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		List<Matcher> matchers = matchersService.getAll();
		http.cors().and().csrf().disable().headers().frameOptions().disable().and().exceptionHandling()
				.authenticationEntryPoint(unauthorizedHandler).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers("/", "/favicon.ico", "/**/*.png", "/**/*.gif", "/**/*.svg", "/**/*.jpg", "/**/*.html",
						"/**/*.css", "/**/*.js")
				.permitAll().antMatchers(RolePermission.DOWNLOAD_ORDER_SCHEDULES.getUrl()).permitAll()
				.antMatchers("/api/csv/**").permitAll().antMatchers("/send-message").permitAll()
				.antMatchers(RolePermission.LOGIN.getUrl()).permitAll().antMatchers(RolePermission.USER_LOG.getUrl())
				.permitAll().antMatchers(RolePermission.ROLE_MANAGEMENT.getUrl()).hasAuthority("ROLE_ADMIN")
				.antMatchers(RolePermission.LOGOUT.getUrl()).permitAll()
				.antMatchers(RolePermission.REGISTER_CUSTOMER.getUrl()).permitAll()
				.antMatchers(RolePermission.REGISTER.getUrl()).authenticated()
				.antMatchers(RolePermission.CURRENT_USER_INFOR.getUrl()).authenticated()
				.antMatchers(RolePermission.ORDER_SCHEDULES.getUrl()).hasAuthority("ROLE_ADMIN")
				.antMatchers(RolePermission.POD_MANAGEMENT.getUrl()).authenticated().anyRequest().authenticated();

		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

	}

}