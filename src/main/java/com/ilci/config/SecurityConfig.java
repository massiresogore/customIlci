package com.ilci.config;

import com.ilci.config.custom.CustomUserDetailSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    CustomUserDetailSerice customUserDetailSerice;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
//        http.csrf(Customizer.withDefaults())
//                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry)
//                .requestMatchers("/register").permitAll()
//                .requestMatchers("/").permitAll()
//                .and()
//                .formLogin("/login")
//                .loginProcessingUrl("/login")
//
//

        return http
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
                        .requestMatchers(HttpMethod.GET,"/user").permitAll()
                        .requestMatchers(HttpMethod.GET,"/user/add").permitAll()
                        .requestMatchers(HttpMethod.POST,"/user/insert").permitAll()
                        .requestMatchers(HttpMethod.GET,"/register").permitAll()
                        /*.requestMatchers(HttpMethod.GET,this.baseUrl+"/users/**").hasAuthority("ROLE_admin")//protecting endpoint
                        //.requestMatchers(HttpMethod.POST,this.baseUrl+"/users").authenticated()//protecting endpoint
                        .requestMatchers(HttpMethod.POST,this.baseUrl+"/users").hasAuthority("ROLE_admin")//protecting endpoint
                        .requestMatchers(HttpMethod.PUT,this.baseUrl+"/users/**").hasAuthority("ROLE_admin")//protecting endpoint
                        .requestMatchers(HttpMethod.DELETE,this.baseUrl+"/users/**").hasAuthority("ROLE_admin")//protecting endpoint
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()//autorise h2-console
                        //allow everything authenticated*/
                        .anyRequest().authenticated()//Recommand to put this at last

                )
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults())
//                .headers(AbstractHttpConfigurer::disable)//autorise h2-console
//                .csrf(AbstractHttpConfigurer::disable)
//                //.cors(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults())
                //.httpBasic(Customizer.withDefaults())
                //.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                /*.oauth2ResourceServer(oauth2ResourceServer-> oauth2ResourceServer
                        .jwt(Customizer.withDefaults())
                        .authenticationEntryPoint(this.customBearerTokenAuthenticationEntryPoint)
                        .accessDeniedHandler(this.customBearerTokenAccessDeniedHandler)
                )*/
                //.sessionManagement(sessionManagement->sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(customUserDetailSerice).passwordEncoder(passwordEncoder());
    }
}
