package gob.distrito05d03salud.controlvacaciones.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import gob.distrito05d03salud.controlvacaciones.dominio.controlvacaciones.Expediente;
import gob.distrito05d03salud.controlvacaciones.dominio.controlvacaciones.SolicitudVacaciones;

public interface SolicitudVacacionesRepository extends JpaRepository<SolicitudVacaciones, Long> {

    List<SolicitudVacaciones> findByExpedienteAndFechaInicioVacacionesBetween(@PathVariable long id,
    @Param("fechaInicioVacaciones") Date fechaInicioPermiso, @Param("fechaFinVacaciones") Date fechaFinPermiso);
  
    List<SolicitudVacaciones> findByExpediente(@Param("fechaInicioVacaciones") Expediente expediente);
  
    long countByExpediente(@Param("expediente") Expediente expediente);
}