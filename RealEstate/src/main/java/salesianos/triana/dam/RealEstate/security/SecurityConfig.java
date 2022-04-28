package salesianos.triana.dam.RealEstate.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import salesianos.triana.dam.RealEstate.security.jwt.JwtAuthorizationFilter;


@Configuration
@EnableMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final JwtAuthorizationFilter filter;
    private final AccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
/*
    @Override
    public void configure(WebSecurity web) throws Exception {
        //Nos permite ignorar la seguridad para un conjunto de peticiones
        //Por ahora son todas las peticiones para hacer pruebas pero esto lo cambiaremos más adelante
        web.ignoring().anyRequest();
    }
*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                    .exceptionHandling()
                        .authenticationEntryPoint(authenticationEntryPoint)
                    .accessDeniedHandler(accessDeniedHandler)
                .and()
                    .sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                    .antMatchers("/h2-console/**").permitAll()
                    .antMatchers(HttpMethod.POST, "/auth/register/gestor").hasRole("ADMIN")
                    .antMatchers(HttpMethod.POST, "/auth/register/admin").hasRole("ADMIN")
                    .antMatchers(HttpMethod.POST, "/inmobiliaria/").hasRole("ADMIN")
                    .antMatchers(HttpMethod.GET, "/interesado/").hasRole("ADMIN")
                    .antMatchers(HttpMethod.GET, "/vivienda/top?n=10/").permitAll()
                    .antMatchers(HttpMethod.POST, "/vivienda/").hasRole("PROPIETARIO")
                    .antMatchers(HttpMethod.POST, "/vivienda/{id}/meinteresa").hasRole("PROPIETARIO")
                    .antMatchers(HttpMethod.POST, "/auth/**").permitAll()
                    .anyRequest().authenticated();

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        // Dar paso a h2

        http.headers().frameOptions().disable();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
}
