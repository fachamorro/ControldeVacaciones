package gob.distrito05d03salud.controlvacaciones.fachada;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gob.distrito05d03salud.controlvacaciones.dominio.administracion.Departamento;
import gob.distrito05d03salud.controlvacaciones.dominio.sujeto.Funcionario;
import gob.distrito05d03salud.controlvacaciones.servicio.FuncionarioService;

@Component
public class SujetosFachada {
    
    @Autowired
    private FuncionarioService funcionarioServicio;
    @Autowired
    private AdministracionFachada administracionFachada;
    
    //****** FUNCIONARIO **************/
    public Funcionario nuevoFuncionario(){
        Funcionario funcionario = new Funcionario();
        return funcionario;
    }

    public Funcionario guardarFuncionario(Funcionario funcionario) throws ParseException{
        Funcionario funcionarioGuardado = funcionarioServicio.guardar(funcionario);
        if(administracionFachada.buscarListaExpedienteFuncionario(funcionarioGuardado).size()==0){
            administracionFachada.nuevoExpedienteFechaIngresoFuncionario(funcionarioGuardado);
        }
        return funcionarioGuardado;
    }
    
    public Funcionario buscarFuncionarioId(long id){
        return funcionarioServicio.buscarPorId(id);

    }

    public Funcionario buscarFuncionarioCriterio(String criterio){
        return funcionarioServicio.buscarFuncionarioCedulaNombresApellidos(criterio);

    }
   
    public List<Funcionario> buscarListaTodosFuncionario(){
        return funcionarioServicio.listarTodosFuncionarios();
    }

    public List<Funcionario> buscarListaFuncionariosDepartamento(Departamento departamento){
        return funcionarioServicio.listarFuncionariosDepartamento(departamento);
    }

    public Funcionario actualizarFuncionario(Funcionario funcionario){
        return funcionarioServicio.guardar(funcionario);
    }
    public void eliminarFuncionario(long id){
        funcionarioServicio.eliminar(id);
    }

    
}
