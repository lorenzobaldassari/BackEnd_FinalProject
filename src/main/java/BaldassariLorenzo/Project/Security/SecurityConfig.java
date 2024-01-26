package BaldassariLorenzo.Project.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JWTAAutfilther jwtAuthFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // Disabilitiamo alcuni comportamenti di default
        httpSecurity.formLogin(AbstractHttpConfigurer::disable);
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.cors(Customizer.withDefaults());
//         Aggiungiamo filtri custom
        httpSecurity.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        // Aggiungere/Rimuovere regole di protezione su singoli endpoint
        // in maniera che venga/non venga richiesta l'autenticazione per accedervi
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/**").permitAll());

        return httpSecurity.build();
    }


    @Bean
    PasswordEncoder getPWEncoder() {
        return new BCryptPasswordEncoder(11);
        // 11 è il numero di ROUNDS, ovvero quante volte viene eseguito l'algoritmo. Ci serve per impostare la velocità di esecuzione
        // di BCrypt. Più è alto il numero, più lento sarà l'algoritmo, più sicure saranno le password. Di contro più è lento l'algoritmo
        // e peggiore sarà la User Experience. Bisogna trovare il giusto bilanciamento tra le 2.
        // 11 significa che l'algoritmo verrà eseguito 2^11 volte 2048 volte
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "https://www.mywonderfulfe.com"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        // Registro la configurazione CORS fatta su tutti gli endpoint della mia applicazione
        return source;
    }

}
