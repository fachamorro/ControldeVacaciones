package gob.distrito05d03salud.controlvacaciones.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import gob.distrito05d03salud.controlvacaciones.dominio.controlvacaciones.Expediente;
import gob.distrito05d03salud.controlvacaciones.dominio.controlvacaciones.PermisoSalida;

public interface PermisoSalidaRepository extends JpaRepository<PermisoSalida, Long> {

  List<PermisoSalida> findByExpedienteAndFechaInicioPermisoBetween(@PathVariable long id,
  @Param("fechaInicioPermiso") Date fechaInicioPermiso, @Param("fechaFinPermiso") Date fechaFinPermiso);

  List<PermisoSalida> findByExpediente(@Param("expediente") Expediente expediente);

  List<PermisoSalida> findAll();

  PermisoSalida findById(@PathVariable long id);

  long countByExpediente(@Param("expediente") Expediente expediente);

  // findFirstByCommonNameIgnoreCaseAndAge(name, age)

}