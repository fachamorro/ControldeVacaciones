package gob.distrito05d03salud.controlvacaciones.dominio.controlvacaciones;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import gob.distrito05d03salud.controlvacaciones.dominio.sujeto.Funcionario;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Expediente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer anio;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaInicioExpediente;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaFinExpediente;
    private Integer saldoAnteriorDias;
    private Integer saldoAnteriorHoras;
    private Integer saldoAnteriorMinutos;
    private Integer devengadoActualDias;
    private Integer devengadoActualHoras;
    private Integer devengadoActualMinutos;
    private String observacion;
    private boolean estado;
    
    @ManyToOne
    private Funcionario funcionario;


    public Expediente() {
        this.estado=true;
        this.devengadoActualDias=0;
        this.devengadoActualHoras=0;
        this.devengadoActualMinutos=0;
        this.saldoAnteriorDias=0;
        this.saldoAnteriorHoras=0;
        this.saldoAnteriorMinutos=0;
    }
    public Expediente(Funcionario funcionario) {
        this.estado=true;
        this.funcionario = funcionario;
        this.devengadoActualDias=0;
        this.devengadoActualHoras=0;
        this.devengadoActualMinutos=0;
        this.saldoAnteriorDias=0;
        this.saldoAnteriorHoras=0;
        this.saldoAnteriorMinutos=0;
    }

    
    public Expediente(Integer anio, Funcionario funcionario, Date fechaInicio, Date fechaFin, Integer devengadoActualDias, Integer devengadoActualHoras, Integer devengadoActualMinutos) {
        this.estado=true;
        this.anio = anio;
        this.fechaInicioExpediente=fechaInicio;
        this.fechaFinExpediente= fechaFin;
        this.devengadoActualDias=devengadoActualDias;
        this.devengadoActualHoras=devengadoActualHoras;
        this.devengadoActualMinutos=devengadoActualMinutos;
        this.funcionario = funcionario;
    }
    public Integer getAnio() {
        return anio;
    }
    public void setAnio(Integer anio) {
        this.anio = anio;
    }
    public Date getFechaInicioExpediente() {
        return fechaInicioExpediente;
    }
    public void setFechaInicioExpediente(Date fechaInicioExpediente) {
        this.fechaInicioExpediente = fechaInicioExpediente;
    }
    public Date getFechaFinExpediente() {
        return fechaFinExpediente;
    }
    public void setFechaFinExpediente(Date fechaFinExpediente) {
        this.fechaFinExpediente = fechaFinExpediente;
    }
    public Integer getSaldoAnteriorDias() {
        return saldoAnteriorDias;
    }
    public void setSaldoAnteriorDias(Integer saldoAnteriorDias) {
        this.saldoAnteriorDias = saldoAnteriorDias;
    }
    public Integer getSaldoAnteriorHoras() {
        return saldoAnteriorHoras;
    }
    public void setSaldoAnteriorHoras(Integer saldoAnteriorHoras) {
        this.saldoAnteriorHoras = saldoAnteriorHoras;
    }
    public Integer getSaldoAnteriorMinutos() {
        return saldoAnteriorMinutos;
    }
    public void setSaldoAnteriorMinutos(Integer saldoAnteriorMinutos) {
        this.saldoAnteriorMinutos = saldoAnteriorMinutos;
    }
    public Integer getDevengadoActualDias() {
        return devengadoActualDias;
    }
    public void setDevengadoActualDias(Integer devengadoActualDias) {
        this.devengadoActualDias = devengadoActualDias;
    }
    public Integer getDevengadoActualHoras() {
        return devengadoActualHoras;
    }
    public void setDevengadoActualHoras(Integer devengadoActualHoras) {
        this.devengadoActualHoras = devengadoActualHoras;
    }
    public Integer getDevengadoActualMinutos() {
        return devengadoActualMinutos;
    }
    public void setDevengadoActualMinutos(Integer devengadoActualMinutos) {
        this.devengadoActualMinutos = devengadoActualMinutos;
    }
    public String getObservacion() {
        return observacion;
    }
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    public boolean isEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    public Funcionario getFuncionario() {
        return funcionario;
    }
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "Expediente [id=" + id + ", devengadoActualDias=" + devengadoActualDias + ", devengadoActualHoras=" + devengadoActualHoras
                + ", devengadoActualMinutos=" + devengadoActualMinutos + ", observacion=" + observacion + ", estado="
                + estado + ", funcionario=" + funcionario + "]";
    }

    
}
