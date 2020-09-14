package com.backbase.kalah.adapter.inmemory;

import com.backbase.kalah.core.auth.RegistrationService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUserService implements UserDetailsService, RegistrationService {
    private Map<String, UserDetails> users;

    private PasswordEncoder passwordEncoder;
    public InMemoryUserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        users = new ConcurrentHashMap<>();
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return users.get(login);
    }

    @Override
    public boolean register(String userName, String password) {
        return users.putIfAbsent(userName, new User(userName, passwordEncoder.encode(password), Collections.singleton(new SimpleGrantedAuthority("USER")))) == null;
    }
}
