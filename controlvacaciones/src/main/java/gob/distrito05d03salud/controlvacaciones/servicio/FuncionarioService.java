package gob.distrito05d03salud.controlvacaciones.servicio;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gob.distrito05d03salud.controlvacaciones.dominio.administracion.Departamento;
import gob.distrito05d03salud.controlvacaciones.dominio.sujeto.Funcionario;
import gob.distrito05d03salud.controlvacaciones.repositorio.FuncionarioRepository;
import jakarta.transaction.Transactional;

@Service
public class FuncionarioService {
    
    @Autowired
    private FuncionarioRepository repository;
    
  
    public List<Funcionario> listarTodosFuncionarios(){
        List<Funcionario> listaFuncionarios = repository.findAll();
        return null == listaFuncionarios || listaFuncionarios.isEmpty() ? Collections.emptyList() : listaFuncionarios;      
    }

    public List<Funcionario> listarFuncionariosDepartamento(Departamento departamento){
        List<Funcionario> listaFuncionarios = repository.findByDepartamento(departamento);
        return null == listaFuncionarios || listaFuncionarios.isEmpty() ? Collections.emptyList() : listaFuncionarios;      
    }

    public Funcionario buscarPorId(Long id){
        return repository.findById(id).orElseThrow(null);
    }

    public Funcionario buscarFuncionarioCedula(String criterio){
        return repository.findByCedulaLikeIgnoreCase(criterio);
    }

    public Funcionario buscarFuncionarioCedulaNombresApellidos(String criterio){
        return repository.findTop1ByCedulaLikeOrNombresContainingIgnoreCaseOrApellidosContainingIgnoreCase(criterio,criterio,criterio);
    }


    @Transactional
    public Funcionario actualizar(Funcionario funcionario){
        return repository.save(funcionario);
    }

    @Transactional
    public Funcionario guardar(Funcionario funcionario){
        return repository.save(funcionario);
    }

    @Transactional
    public void eliminar(long id){
        //check if object with this id exists
        buscarPorId(id);
        repository.deleteById(id);
    }
}
