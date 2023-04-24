package gob.distrito05d03salud.controlvacaciones.dominio.administracion;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Departamento implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique=true)
    private String codigo;
    private String descripcion;
    private boolean estado;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;

    
    
    public Departamento() {
        this.estado=true;
        this.fechaRegistro= new Date();
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public boolean isEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
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
        return "Departamento [id=" + id + ", codigo=" + codigo + ", descripcion=" + descripcion + ", estado=" + estado
                + ", fechaRegistro=" + fechaRegistro + "]";
    }

    
}
