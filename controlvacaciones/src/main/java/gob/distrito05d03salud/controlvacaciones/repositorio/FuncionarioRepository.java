package gob.distrito05d03salud.controlvacaciones.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import gob.distrito05d03salud.controlvacaciones.dominio.administracion.Departamento;
import gob.distrito05d03salud.controlvacaciones.dominio.sujeto.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

  //@EntityGraph(attributePaths = { "id", "nombres", "apellidos", "cedula", "fechaIngreso", "telefono", "usuario.rolUsuario.descripcion" })
  @EntityGraph(value = "Funcionario.listaFuncionarios")
  List<Funcionario> findAll();

  @EntityGraph(attributePaths = { "id", "nombres", "apellidos", "cedula", "fechaIngreso", "telefono", "fechaIngreso","usuario.rolUsuario.descripcion" })
  Funcionario findByCedulaLikeIgnoreCase(@Param("cedula") String cedula);
  
  @EntityGraph(attributePaths = { "id", "nombres", "apellidos", "cedula", "fechaIngreso", "telefono", "fechaIngreso","usuario.rolUsuario.descripcion" })
  Funcionario findTop1ByCedulaLikeOrNombresContainingIgnoreCaseOrApellidosContainingIgnoreCase(@Param("cedula") String cedula, @Param("nombres") String nombres, @Param("apellidos") String apellidos);

  @EntityGraph(attributePaths = { "id", "departamento" })
  List<Funcionario> findByDepartamento(@Param("departamento") Departamento departamento);

}