package org.bildit.lingua.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/practice").permitAll()
			.antMatchers("/logout").permitAll()
			.antMatchers("/users").permitAll()
			.antMatchers("/registration-page").permitAll()
			.antMatchers("/registration").permitAll()
			.antMatchers("/register").permitAll()
			.antMatchers("/registration-check").permitAll()
			.antMatchers("/register-admin").permitAll()
			.antMatchers("/ticket-all").permitAll()
			.antMatchers("/ticket-active").permitAll()
			.antMatchers("/ticket-deleted").permitAll()
			.antMatchers("/ticket-moderated").permitAll()
			.antMatchers("/set-foreign-language").permitAll()
			.antMatchers("/create-ticket").permitAll()
			.antMatchers("/delete-ticket").permitAll()
			.antMatchers("/delete-ticket/**").permitAll()
			.antMatchers("/edit-ticket").permitAll()
			.antMatchers("/get-all-tickets").permitAll()
			.antMatchers("/existusername").permitAll()
			.antMatchers("/test").permitAll()
			.antMatchers("/add-like").permitAll()
			.antMatchers("/add-dislike").permitAll()
			.antMatchers("/fragments/get-tickets.html").permitAll()
			.antMatchers("/fragments/overview-practice.html").permitAll()
			.antMatchers("/fragments/flipcard-practice.html").permitAll()
			.antMatchers("/login-success").authenticated()
			.anyRequest().denyAll()
		.and()
			.formLogin()
			.failureUrl("/?error=true").permitAll()
			.loginPage("/login").permitAll()
			.defaultSuccessUrl("/")
		.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/?logout=true").permitAll()
		.and()
			.csrf()
			.ignoringAntMatchers("/fragments/**")
		.and()
			.rememberMe()
			.key("remember-me")
			.rememberMeParameter("remember-me")
			.rememberMeCookieName("remember-me-cookie");
	}
	
	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
        	.usersByUsernameQuery("SELECT username, password, enabled FROM base_users WHERE BINARY username=?")
        	.authoritiesByUsernameQuery("SELECT username, authority FROM base_users WHERE BINARY username=?");
    }

}
