package gob.distrito05d03salud.controlvacaciones.servicio;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gob.distrito05d03salud.controlvacaciones.dominio.administracion.Departamento;
import gob.distrito05d03salud.controlvacaciones.repositorio.DepartamentoRepository;
import jakarta.transaction.Transactional;

@Service
public class DepartamentoService {
    
    @Autowired
    private DepartamentoRepository repository;
    
    public List<Departamento> listarTodosDepartamento(){
        List<Departamento> listaDepartamentos = repository.findAll();
        return null == listaDepartamentos || listaDepartamentos.isEmpty() ? Collections.emptyList() : listaDepartamentos;
    }

    public Departamento buscarPorId(Long id){
        return repository.findById(id).orElseThrow(null);
    }

    @Transactional
    public Departamento actualizar(Departamento departamento){
        return repository.save(departamento);
    }

    @Transactional
    public Departamento guardar(Departamento departamento){
        return repository.save(departamento);
    }

    @Transactional
    public void eliminar(long id){
        //check if object with this id exists
        buscarPorId(id);
        repository.deleteById(id);
    }
}
