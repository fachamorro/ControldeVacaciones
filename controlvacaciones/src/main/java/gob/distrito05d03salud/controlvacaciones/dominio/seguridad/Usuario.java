package gob.distrito05d03salud.controlvacaciones.dominio.seguridad;

import java.util.Date;
import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique=true)
    private String username;
    private String contrasenia;
    private boolean estado;
    @ManyToOne
    private Rol rolUsuario;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro; 

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
            )
    private Set<Rol> roles = new HashSet<>(); 
    public Usuario() {
        this.fechaRegistro= new Date();
        this.estado=true;
    }
    public Usuario(String username, String contrasenia, Rol rolUsuario) {
        this.fechaRegistro= new Date();
        this.estado=true;
        this.username=username;
        this.contrasenia=contrasenia;
        this.rolUsuario=rolUsuario;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getContrasenia() {
        return contrasenia;
    }
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    public boolean isEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    public Rol getRolUsuario() {
        return rolUsuario;
    }
    public void setRolUsuario(Rol rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Date getFechaRegistro() {
        return fechaRegistro;
    }
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    @Override
    public String toString() {
        return "Usuario [id=" + id + ", username=" + username + ", contrasenia=" + contrasenia + ", estado=" + estado
                + ", rolUsuario=" + rolUsuario + "]";
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    
}
