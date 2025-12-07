package com.example.wbm.security;

import com.example.wbm.model.entity.Perfil;
import com.example.wbm.model.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {

    private Usuario usuario;

    @Override
    public String getPassword() {
        return usuario.getPassword();
    }

    @Override
    public String getUsername() {
        return usuario.getCorreo();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Perfil> perfils = usuario.getPerfiles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Perfil perfil : perfils) {
            if (perfil.getRol() != null) {
                String roleName = "ROLE_" + perfil.getRol().getNombre().toUpperCase();
                authorities.add(new SimpleGrantedAuthority(roleName));
            }
        }
        return authorities;
    }




    public boolean hasRole(String idRol) {

        return this.usuario.hasRole(idRol);
    }
}
