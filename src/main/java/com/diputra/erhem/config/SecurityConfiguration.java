package com.diputra.erhem.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String SQL_LOGIN = "select user_name, password, active as enabled "
            + "from user where user_name = ?";

    private static final String SQL_PERMISSION
            = "select u.user_name, r.role_name as authority "
            + "from user u join user_role ur on u.user_id = ur.user_id "
            + "join role r on ur.role_id = r.role_id "
            + "where u.user_name = ?";

    @Autowired
    private DataSource ds;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(ds)
                .usersByUsernameQuery(SQL_LOGIN)
                .authoritiesByUsernameQuery(SQL_PERMISSION);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/user/angularjs").hasAnyRole("ADMIN", "STAFF")
                .antMatchers("/user/form").hasRole("ADMIN")
                .antMatchers("/user/delete").hasRole("ADMIN")
                .antMatchers("/user/thymeleaf").hasRole("STAFF")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/user/home", true)
                .and()
                .logout()
                .and()
                .addFilterAfter(new CsrfAttributeToCookieFilter(), CsrfFilter.class)
                .csrf().csrfTokenRepository(csrfTokenRepository());
    }
    
    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }
}
