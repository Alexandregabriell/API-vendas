package io.github.Alexandregabriell.config;

import io.github.Alexandregabriell.security.jwt.JwtAuthFilter;
import io.github.Alexandregabriell.security.jwt.JwtService;
import io.github.Alexandregabriell.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private JwtService jwtService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); // algoritimo de autenticação(gera hash diferente a cada chamada)
    }

    @Bean
    public OncePerRequestFilter jwtFilter(){
        return new JwtAuthFilter(jwtService, usuarioService);
    }

    @Override //autenticação
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                userDetailsService(usuarioService)
                .passwordEncoder(passwordEncoder());
    }

    /**
     * Como ficaria a configuração:
     * Tela de Login persolnalizada
     * <form method="post">
     *      <input type="text" name="username">
     *      <input type="text" name="password">
     *          <buttom type="sbmit"...
     *          </form>
     */

    @Override //autorização
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()// modo STATELESS
                .authorizeRequests()
                    .antMatchers("/api/clientes/**") //configuração URL
                        .hasAnyRole("USER", "ADMIN") //Configurção de acesso(ROLES)
                    .antMatchers("/api/produtos/**") // ** - representa parametros
                        .hasAnyRole("USER", "ADMIN")
                    .antMatchers("/api/pedidos/**")
                        .hasAnyRole("ADMIN")
                    .antMatchers(HttpMethod.POST, "/api/usuarios/**")
                        .permitAll()
                    .anyRequest().authenticated()
                .and() //Volta para a Raiz do Objeto "HttpSecurity"
                    //.formLogin(); //Cria formulario de login
                    //.httpBasic(); //Passado as credenciais no HEADER
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
        ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs", // recursos que sao carregados pelo swagger, ignore: nao passa pelo filtro de segurança
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }
}
