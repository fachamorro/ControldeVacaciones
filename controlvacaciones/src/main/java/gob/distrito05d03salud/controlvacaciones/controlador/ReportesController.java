package gob.distrito05d03salud.controlvacaciones.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import gob.distrito05d03salud.controlvacaciones.fachada.AdministracionFachada;

@Controller
@RequestMapping("/reportes")
public class ReportesController {

    @Autowired
    private AdministracionFachada administracionFachada;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("titulo", "Generar Reportes"); 
        model.addAttribute("departamento", administracionFachada.nuevoDepartamento());
       
        model.addAttribute("listaDepartamentos", administracionFachada.buscarListaTodosDepartamento());
        model.addAttribute("listaExpedientes", administracionFachada.buscarListaTodosExpediente());
        
        return "reporte/reportes";
    }

    @GetMapping("/funcionario/{id}")
    public String reporteFuncionario(Model model, @PathVariable(value = "id") long id) {
        model.addAttribute("titulo","Registrar Nuevo Departamento");
        model.addAttribute("departamento", administracionFachada.nuevoDepartamento());
        return "departamento/nuevoDepartamento";
    }

    @GetMapping("/departamento/{id}")
    public String reporteDepartamento(Model model, @PathVariable(value = "id") long id) {
        //administracionFachada.guardarDepartamento(departamento);
        model.addAttribute("mensaje","Guardado Correctamente!!");
        return "redirect:/departamento/";
    }
 
    @GetMapping("/general/")
    public String actualizarDepartamento(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("titulo","Actualizar Departamento");
        model.addAttribute("departamento", administracionFachada.buscarDepartamentoId(id));
        return "departamento/nuevoDepartamento";
    }
 
    /*@GetMapping("/eliminar/{id}")
    public String emilinarPermisoSalidaId(@PathVariable(value = "id") long id, RedirectAttributes redirAttrs) {
        administracionFachada.inactivarDepartamento(id);
        redirAttrs.addFlashAttribute("success", "Departamento desactivado con éxito!!");
        return "redirect:/departamento/";
 
    }*/
    
}
