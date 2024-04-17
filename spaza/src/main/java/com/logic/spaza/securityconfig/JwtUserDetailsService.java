package com.logic.spaza.securityconfig;

import com.logic.spaza.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private AuthService authService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.logic.spaza.model.User user = null;
        
        try {
            user = authService.getUser(username);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        List<GrantedAuthority> listRole = new ArrayList<GrantedAuthority>();

        return new User(user.getUserName(), user.getPassword(), listRole);
    }

}
