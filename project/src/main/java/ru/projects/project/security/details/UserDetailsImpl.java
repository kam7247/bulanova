package ru.egprojects.sw1_springboot.security.details;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.egprojects.sw1_springboot.model.User;
import ru.egprojects.sw1_springboot.model.AuthData;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {

    private AuthData authData;
    private User user;

    public UserDetailsImpl(AuthData authData) {
        this.authData = authData;
        user = authData.getUser();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(authData.getRole().toString());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return authData.getPasswordHash();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getState() == User.State.CONFIRMED;
    }

    public User getUser() {
        return user;
    }
}
