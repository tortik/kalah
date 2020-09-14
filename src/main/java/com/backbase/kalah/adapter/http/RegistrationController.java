package com.backbase.kalah.adapter.http;

import com.backbase.kalah.core.auth.RegistrationService;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Map;

@RestController
public class RegistrationController {

    RegistrationService userService;

    public RegistrationController(RegistrationService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/v1/registration")
    public ResponseEntity<Map<String, Object>> register(@RequestBody UserJson user) {

        boolean result = userService.register(user.getLogin(), user.getPassword());
        if (!result) throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Login is already taken");

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    static class UserJson {
        private String login;
        private String password;

        @JsonCreator
        public UserJson(String login, String password) {
            this.login = login;
            this.password = password;
        }

        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }
    }

}
