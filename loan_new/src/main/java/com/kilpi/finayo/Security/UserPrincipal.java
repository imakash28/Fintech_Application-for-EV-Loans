package com.kilpi.finayo.Security;

import com.kilpi.finayo.Domain.UserEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * @author anagaraju001
 */
@Data
public class UserPrincipal implements UserDetails {
    private static final long serialVersionUID = -3131384566575449433L;
    private Long id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public UserPrincipal(Long id, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = Collections.unmodifiableCollection(authorities);
    }

    static List<GrantedAuthority> getAuthority(UserEntity user) {
        List<GrantedAuthority> asList = new ArrayList<>();
        asList.add(new SimpleGrantedAuthority(user.getRole()));
        return asList;
    }

    public static UserPrincipal create(UserEntity user) {
        List<GrantedAuthority> authorities = getAuthority(user);

        return new UserPrincipal(Long.parseLong(user.getId() + ""), user.getUsername(), "", authorities);
    }

    public static UserPrincipal create(UserEntity user, Map<String, Object> attributes) {
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
    }


    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}