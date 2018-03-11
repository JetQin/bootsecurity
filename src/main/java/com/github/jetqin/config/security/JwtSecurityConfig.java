//package com.github.jetqin.config.security;
//
//import com.github.jetqin.service.security.jwt.JWTAuthenticationFilter;
//import com.github.jetqin.service.security.jwt.JWTAuthorizationFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import static com.github.jetqin.config.security.SecurityConstants.SIGN_UP_URL;
//
//@Configuration
//@EnableWebSecurity
//public class JwtSecurityConfig extends WebSecurityConfigurerAdapter
//{
//    @Autowired
//	@Qualifier("customUserDetailService")
//	private UserDetailsService userDetailService;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception
//    {
//        http.csrf().disable().authorizeRequests()
//                .antMatchers(HttpMethod.POST,SIGN_UP_URL).permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
//                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        super.configure(http);
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception
//    {
//        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
//    }
//
//    @Bean(name = "passwordEncoder")
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//        return source;
//    }
//
//    /*
//        curl -d 'username=jet&password=123456' -i http://localhost:8081/login
//        curl -X POST -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqZXQiLCJleHAiOjE1MjE2MTcwMTB9.VqMYfXPyUVIK-TFcP7BStYj4P3W4UYmbyJISR3Vpo_6wwRhQ9pKiIyDs_D31KSqnEJVHJssVrdTC545xKY2xww" -i http://localhost:8081/hello
//
//     */
//
//}
