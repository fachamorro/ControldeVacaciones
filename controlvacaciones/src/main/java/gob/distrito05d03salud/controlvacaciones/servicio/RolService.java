package gob.distrito05d03salud.controlvacaciones.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gob.distrito05d03salud.controlvacaciones.dominio.seguridad.Rol;
import gob.distrito05d03salud.controlvacaciones.repositorio.RolRepository;
import jakarta.transaction.Transactional;

@Service
public class RolService {
    @Autowired
    private RolRepository repository;
    
  
    public List<Rol> listarTodosRoles(){
        return repository.findAll();
    }

    public Rol buscarPorId(Long id){
        return repository.findById(id).orElseThrow(null);
    }

    @Transactional
    public Rol actualizar(Rol rolUsuario){
        return repository.save(rolUsuario);
    }

    @Transactional
    public Rol guardar(Rol rolUsuario){
        return repository.save(rolUsuario);
    }

    @Transactional
    public void eliminar(long id){
        //check if object with this id exists
        buscarPorId(id);
        repository.deleteById(id);
    }
}
