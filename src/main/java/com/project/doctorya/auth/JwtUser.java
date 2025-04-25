package com.project.doctorya.auth;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.project.doctorya.models.User;

public class JwtUser implements UserDetails {

    private final User user;

    public JwtUser(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Si tienes roles, puedes mapearlos aquí
        return List.of(new SimpleGrantedAuthority(user.getRol().name()));
    }

    @Override
    public String getPassword() {
        return user.getPassword(); // Si no manejas password, puedes retornar null
    }

    @Override
    public String getUsername() {
        return user.getIdentification(); // Este será el username usado en el contexto de Spring Security
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
        return true;
    }

    public User getUser() {
        return user; // útil si luego quieres acceder a todos los datos del usuario
    }

}
