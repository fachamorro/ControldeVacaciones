package gob.distrito05d03salud.controlvacaciones.util;

import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import gob.distrito05d03salud.controlvacaciones.servicio.UsuarioService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	public static final String[] ENDPOINTS_WHITELIST = {
			"/css/**",
			"/bootstrap-4.3.1/**",
			"/jquery/**",
			"/img/**",
			//"/login/"
	};
	public static final String LOGIN_URL = "/login/";
	public static final String LOGOUT_URL = "/logout/";
	public static final String LOGIN_FAIL_URL = LOGIN_URL + "?error";
	public static final String DEFAULT_SUCCESS_URL = "/home/";

	@Bean
	public UserDetailsService userDetailsService() {
		return new UsuarioService();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests((requests) -> requests
						.requestMatchers(ENDPOINTS_WHITELIST).permitAll()
						.requestMatchers("/login/").anonymous()
						.requestMatchers("/expediente/expedientepersonal/**").hasAnyAuthority("ADMINISTRADOR", "FUNCIONARIO","RESPONSABLETH","RESPONSABLEDPTO")
						.requestMatchers("/expediente/detalles/**").hasAnyAuthority("ADMINISTRADOR", "FUNCIONARIO","RESPONSABLETH","RESPONSABLEDPTO")
						.requestMatchers("/funcionario/perfilusuario/**").hasAnyAuthority("ADMINISTRADOR", "FUNCIONARIO","RESPONSABLETH","RESPONSABLEDPTO")
						.requestMatchers("/funcionario/actualizarcontrasenia/**").hasAnyAuthority("ADMINISTRADOR", "FUNCIONARIO","RESPONSABLETH","RESPONSABLEDPTO")
						.requestMatchers("/funcionario/**").hasAnyAuthority("ADMINISTRADOR")
            			.requestMatchers("/permisosalida/**").hasAnyAuthority("RESPONSABLETH")
						.requestMatchers("/solicitudvacaciones/**").hasAnyAuthority("RESPONSABLETH")
						.requestMatchers("/reporte/**").hasAnyAuthority("RESPONSABLETH")
            			.requestMatchers("/expediente/**").hasAuthority("RESPONSABLETH")
						.requestMatchers("/expediente/expedientefuncionarios/**").hasAuthority("RESPONSABLEDPTO")
						.anyRequest().authenticated())
				
				.formLogin((form) -> form

						//.loginPage("/login/")
						//.usernameParameter("username")
						.loginPage(LOGIN_URL)
                        .loginProcessingUrl(LOGIN_URL)
                        .failureUrl(LOGIN_FAIL_URL)
						.defaultSuccessUrl(DEFAULT_SUCCESS_URL))
						//.defaultSuccessUrl("/home/	")
						//.permitAll())
				.logout(logout -> logout
                        .logoutUrl("/logout/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID","SESSION")
                        .logoutSuccessUrl(LOGIN_URL + "?logout"))
				.sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        .invalidSessionUrl("/invalidSession/")
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true));

		return http.build();
	}

}