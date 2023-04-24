package gob.distrito05d03salud.controlvacaciones.sujetos;
import static org.junit.Assert.assertEquals;
import java.text.ParseException;
import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import gob.distrito05d03salud.controlvacaciones.dominio.administracion.Departamento;
import gob.distrito05d03salud.controlvacaciones.dominio.seguridad.Rol;
import gob.distrito05d03salud.controlvacaciones.dominio.seguridad.Usuario;
import gob.distrito05d03salud.controlvacaciones.dominio.sujeto.Funcionario;
import gob.distrito05d03salud.controlvacaciones.fachada.AdministracionFachada;
import gob.distrito05d03salud.controlvacaciones.fachada.SujetosFachada;
import gob.distrito05d03salud.controlvacaciones.repositorio.RolRepository;
import gob.distrito05d03salud.controlvacaciones.repositorio.UsuarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestFuncionario {

  @Autowired
  SujetosFachada sujetoFachada;
  @Autowired
  RolRepository repositoryRol;
  @Autowired
  UsuarioRepository repositoryUsuario;
  @Autowired
  AdministracionFachada administracionFachada;
  
  @Test
  public void test_Crear_Funcionario() throws ParseException {
    Funcionario funcionario = new Funcionario();
    funcionario.setCedula("1105050304");
    funcionario.setApellidos("CHAMORRO ENCALADA");
    funcionario.setNombres("FREDDY ALEXANDER");
    funcionario.setFechaIngreso(new Date());
    funcionario.setFechaRegistro(new Date());
    Rol rol=new Rol();
    rol.setDescripcion("ADMINISTRADOR");
    rol.setEstado(true);
    Rol rolGuardado=repositoryRol.save(rol);
    Usuario user= repositoryUsuario.save(new Usuario(funcionario.getCedula(), funcionario.getCedula(), rolGuardado));
    funcionario.setUsuario(user);
    Departamento dpto=administracionFachada.buscarDepartamentoId(1);
    funcionario.setDepartamento(dpto);
    Funcionario funcionarioGuardado = sujetoFachada.guardarFuncionario(funcionario);
    assertEquals(funcionario.getCedula(), funcionarioGuardado.getCedula());
  }

  @Test
  public void test_Editar_Funcionario() throws ParseException {
    Funcionario funcionario = sujetoFachada.buscarFuncionarioCriterio("1105050304");
    funcionario.setCedula("1711980282");
    funcionario.setApellidos("Encalada Gomez");
    Funcionario funcionarioGuardado = sujetoFachada.guardarFuncionario(funcionario);
    assertEquals(funcionario.getCedula(), funcionarioGuardado.getCedula());
  }

  @Test
  public void test_Buscar_Funcionario(){
    Funcionario funcionarioBuscadoCedula = sujetoFachada.buscarFuncionarioCriterio("1105050304");
    Funcionario funcionarioBuscadoApellido = sujetoFachada.buscarFuncionarioCriterio("CHAMORRO");
    assertEquals(funcionarioBuscadoCedula.getCedula(), funcionarioBuscadoApellido.getCedula());
  }

  @Test
  public void test_Eliminar_Funcionario(){
    Funcionario funcionarioBuscado = sujetoFachada.buscarFuncionarioId(302);
    sujetoFachada.eliminarFuncionario(funcionarioBuscado.getId());
  }

}