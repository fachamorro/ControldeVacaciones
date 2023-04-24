package gob.distrito05d03salud.controlvacaciones.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import gob.distrito05d03salud.controlvacaciones.dominio.controlvacaciones.Expediente;
import gob.distrito05d03salud.controlvacaciones.dominio.sujeto.Funcionario;

public interface ExpedienteRepository extends JpaRepository<Expediente, Long> 
{
    List<Expediente> findByFuncionario(@Param("funcionario") Funcionario funcionario);
    List<Expediente> findByAnio(@Param("fanio") int anio);
    
    Expediente findByFuncionarioAndAnio(@Param("funcionario") Funcionario funcionario,@Param("anio") Integer anio);
}