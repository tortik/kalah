package com.backbase.kalah.adapter.inmemory;

import com.backbase.kalah.core.GameService;
import com.backbase.kalah.core.auth.AuthorizationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class InMemoryConfiguration {

    @Bean
    GameService gameService(AuthorizationService authorizationService){
        return new InMemoryGameService(authorizationService);
    }

    @Bean
    InMemoryUserService userService(PasswordEncoder passwordEncoder){
        return new InMemoryUserService(passwordEncoder);
    }

}
