package gob.distrito05d03salud.controlvacaciones.seguridad;
import static org.junit.Assert.assertEquals;
import java.text.ParseException;
import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import gob.distrito05d03salud.controlvacaciones.dominio.seguridad.Rol;
import gob.distrito05d03salud.controlvacaciones.dominio.seguridad.Usuario;
import gob.distrito05d03salud.controlvacaciones.fachada.SeguridadFachada;
import gob.distrito05d03salud.controlvacaciones.repositorio.RolRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUsuario {

  @Autowired
  SeguridadFachada seguridadFachada;
  @Autowired
  RolRepository repositoryRol;
  
  
  @Test
  public void test_Crear_Usuario() throws ParseException {
    Usuario usuario = new Usuario();
    usuario.setUsername("1105050304");
    usuario.setContrasenia("123466");
    usuario.setFechaRegistro(new Date());
    Rol rolGuardado=seguridadFachada.buscarRolUsuarioId(1);
    usuario.setRolUsuario(rolGuardado);
    Usuario usuarioGuardado = seguridadFachada.guardarUsuario(usuario);
    assertEquals(usuario.getUsername(), usuarioGuardado.getUsername());
  }

  @Test
  public void test_Editar_Usuario() throws ParseException {
    Usuario usuario  = seguridadFachada.buscarUsuarioId(1);
    usuario.setContrasenia("1105050304");
    Usuario usuarioGuardado = seguridadFachada.guardarUsuario(usuario);
    assertEquals(usuario.getUsername(), usuarioGuardado.getUsername());
  }

  @Test
  public void test_Buscar_Usuario(){
    Usuario usuarioBuscadoA = seguridadFachada.buscarUsuarioId(1);
    Usuario usuarioBuscadoB = seguridadFachada.buscarUsuarioId(1);
    assertEquals(usuarioBuscadoA.getUsername(), usuarioBuscadoB.getUsername());
  }

  @Test
  public void test_Eliminar_Usuario(){
    Usuario usuarioBuscado = seguridadFachada.buscarUsuarioId(302);
    seguridadFachada.eliminarUsuario(usuarioBuscado.getId());
  }

}