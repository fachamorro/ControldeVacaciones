package gob.distrito05d03salud.controlvacaciones.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gob.distrito05d03salud.controlvacaciones.dominio.controlvacaciones.Expediente;
import gob.distrito05d03salud.controlvacaciones.dominio.sujeto.Funcionario;
import gob.distrito05d03salud.controlvacaciones.repositorio.ExpedienteRepository;
import jakarta.transaction.Transactional;

@Service
public class ExpedienteService {
    
    @Autowired
    private ExpedienteRepository repository;
    
  
    public List<Expediente> listarTodosExpedientes(){
        return repository.findAll();
    }

    public Expediente buscarPorId(Long id){
        return repository.findById(id).orElseThrow(null);
    }

    public List<Expediente> buscarListaExpedienteFuncionario(Funcionario funcionario){
        return repository.findByFuncionario(funcionario);
    }

    public List<Expediente> buscarListaExpedientesAnio(int anio){
        return repository.findByAnio(anio);
    }

    public Expediente buscarExpedienteFuncionarioAnio(Funcionario funcionario, Integer anio ){
        return repository.findByFuncionarioAndAnio(funcionario, anio);
    }

    @Transactional
    public Expediente actualizar(Expediente expediente){
        return repository.save(expediente);
    }

    @Transactional
    public Expediente guardar(Expediente expediente){
        return repository.save(expediente);
    }

    @Transactional
    public void eliminar(long id){
        //check if object with this id exists
        buscarPorId(id);
        repository.deleteById(id);
    }
}
