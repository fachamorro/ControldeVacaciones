package gob.distrito05d03salud.controlvacaciones.dominio.controlvacaciones;

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
public class SolicitudVacaciones {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private TipoVacaciones tipoVacaciones;
    private Integer numeroDias;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaInicioVacaciones;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaFinVacaciones;
    private String observacion;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @ManyToOne
    private Expediente expediente;
 
    
    public SolicitudVacaciones() {
        this.fechaRegistro = new Date();
    }
    public TipoVacaciones getTipoVacaciones() {
        return tipoVacaciones;
    }
    public void setTipoVacaciones(TipoVacaciones tipoVacaciones) {
        this.tipoVacaciones = tipoVacaciones;
    }
    public Integer getNumeroDias() {
        return numeroDias;
    }
    public void setNumeroDias(Integer numeroDias) {
        this.numeroDias = numeroDias;
    }
    public Date getFechaInicioVacaciones() {
        return fechaInicioVacaciones;
    }
    public void setFechaInicioVacaciones(Date fechaInicioVacaciones) {
        this.fechaInicioVacaciones = fechaInicioVacaciones;
    }
    public Date getFechaFinVacaciones() {
        return fechaFinVacaciones;
    }
    public void setFechaFinVacaciones(Date fechaFinVacaciones) {
        this.fechaFinVacaciones = fechaFinVacaciones;
    }
    public String getObservacion() {
        return observacion;
    }
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    public Expediente getExpediente() {
        return expediente;
    }
    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
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
        return "SolicitudVacaciones [id=" + id +  ", tipoVacaciones="
                + tipoVacaciones + ", numeroDias=" + numeroDias + ", fechaInicioVacaciones=" + fechaInicioVacaciones
                + ", fechaFinVacaciones=" + fechaFinVacaciones + ", observacion=" + observacion + ", fechaRegistro="
                + fechaRegistro + ", expediente=" + expediente + "]";
    }

    
}
