package gob.distrito05d03salud.controlvacaciones.util;
 
import java.util.*;
 
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import gob.distrito05d03salud.controlvacaciones.dominio.seguridad.Usuario;
 
public class MyUserDetails implements UserDetails {
 
    private Usuario user;
     
    public MyUserDetails(Usuario user) {
        this.user = user;
    }

    public MyUserDetails() {;
    }
 
    /*@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Rol> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
         
        for (Rol role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getDescripcion()));
        }
         
        return authorities;
    }*/

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
         
        authorities.add(new SimpleGrantedAuthority(user.getRolUsuario().getDescripcion()));
         
        return authorities;
    }
 
    @Override
    public String getPassword() {
        return user.getContrasenia();
    }
 
    @Override
    public String getUsername() {
        return user.getUsername();
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
 
    /*@Override
    public boolean isEnabled() {
        return user.isEstado();
    }*/
    @Override
    public boolean isEnabled() {
        return true;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
 
}