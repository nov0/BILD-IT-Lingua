package org.bildit.lingua.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/new-entry-ban").permitAll()
			.antMatchers("/login-ban").permitAll()
			.antMatchers("/vote-ban").permitAll()
			.antMatchers("/registration").permitAll()
			.antMatchers("/registration-check").permitAll()
			.antMatchers("/users").permitAll()
			.antMatchers("/existusername").permitAll()
			.antMatchers("/practice").authenticated()
			.antMatchers("/logout").authenticated()
			.antMatchers("/register").authenticated()
			.antMatchers("/register-admin").authenticated()
			.antMatchers("/fragments/register-new-admin.html").authenticated()
			.antMatchers("/registration-page").authenticated()
			.antMatchers("/ticket-all").authenticated()
			.antMatchers("/ticket-active").authenticated()
			.antMatchers("/delete-confirmation").authenticated()
			.antMatchers("/ticket-deleted").authenticated()
			.antMatchers("/ticket-moderated").authenticated()
			.antMatchers("/set-foreign-language").authenticated()
			.antMatchers("/create-ticket").authenticated()
			.antMatchers("/delete-ticket").authenticated()
			.antMatchers("/edit-ticket").authenticated()
			.antMatchers("/edit").authenticated()
			.antMatchers("/get-all-tickets").authenticated()
			.antMatchers("/add-like").authenticated()
			.antMatchers("/add-dislike").authenticated()
			.antMatchers("/user-has-tickets").authenticated()
			.antMatchers("/reset-practice").authenticated()
			.antMatchers("/user-search").authenticated()
			.antMatchers("/ban-user-request").authenticated()
			.antMatchers("/ban-submit").authenticated()
			.antMatchers("/disable-ticket").hasAuthority("ADMIN")
			.antMatchers("/enable-ticket").hasAuthority("ADMIN")
			.antMatchers("/download").authenticated()
			.antMatchers("/fragments/get-tickets.html").authenticated()
			.antMatchers("/fragments/overview-practice.html").authenticated()
			.antMatchers("/fragments/flipcard-practice.html").authenticated()
			.antMatchers("/fragments/edit-lingua-modal.html").authenticated()
			.antMatchers("/fragments/get-tickets-admin").authenticated()
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
			.logoutSuccessUrl("/").permitAll()
		.and()
			.csrf()
			.ignoringAntMatchers("/fragments/**")
			.ignoringAntMatchers("/add-like")
			.ignoringAntMatchers("/add-dislike")
			.ignoringAntMatchers("/set-foreign-language")
			.ignoringAntMatchers("/new-entry-ban")
			.ignoringAntMatchers("/login-ban")
			.ignoringAntMatchers("/vote-ban")
			.ignoringAntMatchers("/ban-submit")
		.and()
			.rememberMe()
			.key("remember-me")
			.rememberMeParameter("remember-me")
			.rememberMeCookieName("remember-me-cookie");
	}
	
	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(passwordEncoder()).dataSource(dataSource)
        	.usersByUsernameQuery("SELECT username, password, enabled FROM base_users WHERE BINARY username=?")
        	.authoritiesByUsernameQuery("SELECT username, authority FROM base_users WHERE BINARY username=?");
    }
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
