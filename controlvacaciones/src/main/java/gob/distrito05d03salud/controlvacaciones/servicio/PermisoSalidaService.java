package gob.distrito05d03salud.controlvacaciones.servicio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gob.distrito05d03salud.controlvacaciones.dominio.controlvacaciones.Expediente;
import gob.distrito05d03salud.controlvacaciones.dominio.controlvacaciones.PermisoSalida;
import gob.distrito05d03salud.controlvacaciones.repositorio.PermisoSalidaRepository;
import jakarta.transaction.Transactional;

@Service
public class PermisoSalidaService {
    @Autowired
    private PermisoSalidaRepository repository;
    
    /*public PermisoSalida buscarPermisoSalidaId(long id) {
        return repository.findById(id);
    }*/
  
    public List<PermisoSalida> listarTodosPermisos(){
        return repository.findAll();
    }

    public List<PermisoSalida> listarPermisosExpedienteFechainicioFechafin(long id, Date fechaInicioPermiso, Date fechaFinPermiso){
        return repository.findByExpedienteAndFechaInicioPermisoBetween(id, fechaInicioPermiso, fechaFinPermiso);
    }

    public List<PermisoSalida> listarPermisosExpediente(Expediente expediente){
        return repository.findByExpediente(expediente);
    }

    public long contarPermisosExpediente(Expediente expediente){
        return repository.countByExpediente(expediente);
    }

    public PermisoSalida buscarPorId(Long id){
        return repository.findById(id).orElseThrow(null);
    }

    @Transactional
    public PermisoSalida actualizar(PermisoSalida permisoSalida){
        return repository.save(permisoSalida);
    }

    @Transactional
    public PermisoSalida guardar(PermisoSalida permisoSalida){
        return repository.save(permisoSalida);
    }

    @Transactional
    public void eliminar(long id){
        //check if object with this id exists
        buscarPorId(id);
        repository.deleteById(id);
    }
}
