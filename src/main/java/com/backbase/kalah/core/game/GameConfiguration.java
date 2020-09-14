package com.backbase.kalah.core.game;

import com.backbase.kalah.core.auth.AuthorizationService;
import com.backbase.kalah.core.auth.BasicAuthorizationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameConfiguration {

    @Bean
    AuthorizationService authorizationService(){
        return new BasicAuthorizationService();
    }
}
