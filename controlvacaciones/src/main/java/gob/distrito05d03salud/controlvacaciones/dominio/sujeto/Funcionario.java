package gob.distrito05d03salud.controlvacaciones.dominio.sujeto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import gob.distrito05d03salud.controlvacaciones.dominio.administracion.Departamento;
import gob.distrito05d03salud.controlvacaciones.dominio.seguridad.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;

@Entity
public class Funcionario extends Sujeto {
    // @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    // private long id;
    @Email
    @Column(unique = true)
    private String email;
    private String telefono;
    private String titulo;
    private String puesto;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaIngreso;
    private TipoFuncionario tipoFuncionario;
    private boolean estado;
    @ManyToOne
    private Departamento departamento;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @OneToOne
    private Usuario usuario;

    public Funcionario() {
        this.estado = true;
        this.fechaRegistro = new Date();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public TipoFuncionario getTipoFuncionario() {
        return tipoFuncionario;
    }

    public void setTipoFuncionario(TipoFuncionario tipoFuncionario) {
        this.tipoFuncionario = tipoFuncionario;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Funcionario [email=" + email + ", telefono=" + telefono + ", titulo=" + titulo + ", puesto=" + puesto
                + ", fechaIngreso=" + fechaIngreso + ", tipoFuncionario=" + tipoFuncionario + ", estado=" + estado
                + ", departamento=" + departamento + ", fechaRegistro=" + fechaRegistro + ", usuario=" + usuario + "]";
    }

}
