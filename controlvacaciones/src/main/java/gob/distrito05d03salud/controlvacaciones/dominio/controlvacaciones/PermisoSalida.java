package gob.distrito05d03salud.controlvacaciones.dominio.controlvacaciones;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
@Entity
public class PermisoSalida implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private MotivoPermiso motivoPermiso;
    private TipoPermiso tipoPermiso;
    private Integer numeroDias;
    private Integer numeroHoras;
    private Integer numeroMinutos;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaInicioPermiso;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaFinPermiso;
    private String observacion;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    private boolean cargoVacaciones;
    @ManyToOne
    private Expediente expediente;

    
    public PermisoSalida() {
        this.fechaRegistro = new Date();
    }
    public MotivoPermiso getMotivoPermiso() {
        return motivoPermiso;
    }
    public void setMotivoPermiso(MotivoPermiso motivoPermiso) {
        this.motivoPermiso = motivoPermiso;
    }
    public TipoPermiso getTipoPermiso() {
        return tipoPermiso;
    }
    public void setTipoPermiso(TipoPermiso tipoPermiso) {
        this.tipoPermiso = tipoPermiso;
    }
    public Integer getNumeroDias() {
        return numeroDias;
    }
    public void setNumeroDias(Integer numeroDias) {
        this.numeroDias = numeroDias;
    }
    public Integer getNumeroHoras() {
        return numeroHoras;
    }
    public void setNumeroHoras(Integer numeroHoras) {
        this.numeroHoras = numeroHoras;
    }
    public Integer getNumeroMinutos() {
        return numeroMinutos;
    }
    public void setNumeroMinutos(Integer numeroMinutos) {
        this.numeroMinutos = numeroMinutos;
    }
    public Date getFechaInicioPermiso() {
        return fechaInicioPermiso;
    }
    public void setFechaInicioPermiso(Date fechaInicioPermiso) {
        this.fechaInicioPermiso = fechaInicioPermiso;
    }
    public Date getFechaFinPermiso() {
        return fechaFinPermiso;
    }
    public void setFechaFinPermiso(Date fechaFinPermiso) {
        this.fechaFinPermiso = fechaFinPermiso;
    }
    public Expediente getExpediente() {
        return expediente;
    }
    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }
    public String getObservacion() {
        return observacion;
    }
    public void setObservacion(String observacion) {
        this.observacion = observacion;
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
        return "PermisoSalida [id=" + id + ", motivoPermiso=" + motivoPermiso + ", tipoPermiso=" + tipoPermiso
                + ", numeroDias=" + numeroDias + ", numeroHoras=" + numeroHoras + ", numeroMinutos=" + numeroMinutos
                + ", fechaInicioPermiso=" + fechaInicioPermiso + ", fechaFinPermiso=" + fechaFinPermiso
                + ", observacion=" + observacion + ", fechaRegistro=" + fechaRegistro + ", expediente=" + expediente
                + "]";
    }
    public boolean isCargoVacaciones() {
        return cargoVacaciones;
    }
    public void setCargoVacaciones(boolean cargoVacaciones) {
        this.cargoVacaciones = cargoVacaciones;
    }

    
}
