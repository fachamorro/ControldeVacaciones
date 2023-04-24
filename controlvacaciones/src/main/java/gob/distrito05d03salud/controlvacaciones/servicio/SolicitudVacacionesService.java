package gob.distrito05d03salud.controlvacaciones.servicio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gob.distrito05d03salud.controlvacaciones.dominio.controlvacaciones.Expediente;
import gob.distrito05d03salud.controlvacaciones.dominio.controlvacaciones.SolicitudVacaciones;
import gob.distrito05d03salud.controlvacaciones.repositorio.SolicitudVacacionesRepository;
import jakarta.transaction.Transactional;

@Service
public class SolicitudVacacionesService {
    @Autowired
    private SolicitudVacacionesRepository repository;
  
    public List<SolicitudVacaciones> listarTodosSolicitudVacaciones(){
        return repository.findAll();
    }

    public List<SolicitudVacaciones> listarSolicitudVacacionesExpedienteFechainicioFechafin(long id, Date fechaInicioPermiso, Date fechaFinPermiso){
        return repository.findByExpedienteAndFechaInicioVacacionesBetween(id, fechaInicioPermiso, fechaFinPermiso);
    }

    public List<SolicitudVacaciones> listarSolicitudVacacionesExpediente(Expediente expediente){
        return repository.findByExpediente(expediente);
    }
    public SolicitudVacaciones buscarPorId(Long id){
        return repository.findById(id).orElseThrow(null);
    }

    public long contarSolicitudVacacionesExpediente(Expediente expediente){
        return repository.countByExpediente(expediente);
    }

    @Transactional
    public SolicitudVacaciones actualizar(SolicitudVacaciones solicitudVacaciones){
        return repository.save(solicitudVacaciones);
    }

    @Transactional
    public SolicitudVacaciones guardar(SolicitudVacaciones solicitudVacaciones){
        return repository.save(solicitudVacaciones);
    }

    @Transactional
    public void eliminar(long id){
        //check if object with this id exists
        buscarPorId(id);
        repository.deleteById(id);
    }
}
