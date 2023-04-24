package gob.distrito05d03salud.controlvacaciones.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import gob.distrito05d03salud.controlvacaciones.dominio.administracion.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {


}