package com.ghofrane.chansons.sercurity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String USERS_API = "http://localhost:8082/users/user/";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Map<String, Object> user = restTemplate.getForObject(USERS_API + username, Map.class);
            if (user == null) throw new UsernameNotFoundException("User not found: " + username);

            String password = (String) user.get("password");
            List<Map<String, String>> roles = (List<Map<String, String>>) user.get("roles");

            List<SimpleGrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.get("role")))
                .collect(Collectors.toList());

            return new org.springframework.security.core.userdetails.User(username, password, authorities);

        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
    }
}