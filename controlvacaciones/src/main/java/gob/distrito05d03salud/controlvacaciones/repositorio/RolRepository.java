package gob.distrito05d03salud.controlvacaciones.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;


import gob.distrito05d03salud.controlvacaciones.dominio.seguridad.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {


}