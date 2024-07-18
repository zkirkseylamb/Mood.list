package com.example.music.services;

import com.example.music.dao.UsersDao;
import com.example.music.models.Users;
import eu.fraho.spring.securityJwt.base.dto.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    private UsersDao usersDao;
    private PasswordEncoder passwordEncoder;

    public CustomUserDetailsService(UsersDao usersDao){
        this.usersDao = usersDao;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersDao.getUser(username);

        if(users == null){
            throw new UsernameNotFoundException("user not found");
        }

        JwtUser jwtUser = new JwtUser();
        jwtUser.setUsername(users.getUsername());
        jwtUser.setPassword(users.getUsername());

        List<String> roles = usersDao.getRolesForUser(users.getUsername());
        List<GrantedAuthority> authorities = new ArrayList<>();

        for(String role:roles){
            authorities.add(new SimpleGrantedAuthority(role));
        }

        jwtUser.setAuthorities(authorities);
        jwtUser.setAccountNonExpired(true);
        jwtUser.setAccountNonLocked(true);
        jwtUser.setApiAccessAllowed(true);
        jwtUser.setCredentialsNonExpired(true);
        jwtUser.setEnabled(true);

        return jwtUser;

    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
