package gob.distrito05d03salud.controlvacaciones.administracion;
import static org.junit.Assert.assertEquals;
import java.text.ParseException;
import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import gob.distrito05d03salud.controlvacaciones.dominio.administracion.Departamento;
import gob.distrito05d03salud.controlvacaciones.fachada.AdministracionFachada;
import gob.distrito05d03salud.controlvacaciones.repositorio.DepartamentoRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDepartamento {

  @Autowired
  AdministracionFachada administracionFachada;
  @Autowired
  DepartamentoRepository repository;
  
  @Test
  public void test_Crear_Departamento() throws ParseException {
    Departamento departamento = new Departamento();
    departamento.setCodigo("001");
    departamento.setDescripcion("SOPORTE TECNICO");
    departamento.setEstado(true);
    departamento.setFechaRegistro(new Date());
    Departamento dptoGuardado = administracionFachada.guardarDepartamento(departamento);
    assertEquals(departamento.getCodigo(), dptoGuardado.getCodigo());
  }

  @Test
  public void test_Editar_Departamento() throws ParseException {
    Departamento dpto = administracionFachada.buscarDepartamentoId(1);
    dpto.setDescripcion("FINANCIERO");
    Departamento dptoGuardado = administracionFachada.guardarDepartamento(dpto);
    assertEquals(dpto.getDescripcion(), dptoGuardado.getDescripcion());
  }

  @Test
  public void test_Buscar_Departamento(){
    Departamento dptoBuscadoId = administracionFachada.buscarDepartamentoId(1);
    Departamento dptoBuscadoDescripcion = administracionFachada.buscarDepartamentoId(1);
    assertEquals(dptoBuscadoId.getCodigo(), dptoBuscadoDescripcion.getCodigo());
  }

  @Test
  public void test_Eliminar_Departamento(){
    Departamento dptoBuscado = administracionFachada.buscarDepartamentoId(302);
    administracionFachada.eliminarDepartamento(dptoBuscado.getId());
  }

}