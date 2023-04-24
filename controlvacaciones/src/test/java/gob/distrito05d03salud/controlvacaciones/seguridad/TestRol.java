package gob.distrito05d03salud.controlvacaciones.seguridad;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import gob.distrito05d03salud.controlvacaciones.dominio.seguridad.Rol;
import gob.distrito05d03salud.controlvacaciones.fachada.SeguridadFachada;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRol{

  @Autowired
  SeguridadFachada seguridadFachada;
   
    @Test
    public void test_Crear_Rol()  {
        Rol rolUsuario = new Rol();
        rolUsuario.setDescripcion("PruebaRol2");
        rolUsuario.setEstado(true);
        Rol rolGuardado = seguridadFachada.guardarRolUsuario(rolUsuario);
        assertEquals(rolUsuario.getDescripcion(), rolGuardado.getDescripcion());
    }
  }