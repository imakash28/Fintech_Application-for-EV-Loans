package com.kilpi.finayo.Security.service;

import com.kilpi.finayo.Domain.UserEntity;
import com.kilpi.finayo.Repository.UserRepository;
import com.kilpi.finayo.Security.TokenProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author anagaraju001
 */
@Service(value = "AuthUserDetailsServiceImpl")
public class AuthUserDetailsServiceImpl implements UserDetailsService {

    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenProvider tokenProvider;

    @Override
    public UserDetails loadUserByUsername(String guid) {

        return new org.springframework.security.core.userdetails.User(guid, "", getAuthority());
    }

    private List<SimpleGrantedAuthority> getAuthority() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    private List<SimpleGrantedAuthority> getAuthority(UserEntity user) {
        List<SimpleGrantedAuthority> asList = new ArrayList<>();
        asList.add(new SimpleGrantedAuthority(user.getRole()));
        return asList;
    }

    public UserDetails loadUserById(Integer id) {
        Optional<UserEntity> result = userRepository.findById(id);
        UserEntity user = result.orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid user");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), "", getAuthority(user));
    }

}