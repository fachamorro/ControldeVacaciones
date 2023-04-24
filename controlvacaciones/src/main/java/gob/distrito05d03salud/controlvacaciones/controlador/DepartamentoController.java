package gob.distrito05d03salud.controlvacaciones.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gob.distrito05d03salud.controlvacaciones.dominio.administracion.Departamento;
import gob.distrito05d03salud.controlvacaciones.fachada.AdministracionFachada;

@Controller
@RequestMapping("/departamento")
public class DepartamentoController {

    @Autowired
    private AdministracionFachada administracionFachada;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("titulo", "Administración de Departamentos");        
        model.addAttribute("listaDepartamentos", administracionFachada.buscarListaTodosDepartamento());
        return "departamento/departamento";
    }

    @GetMapping("/nuevo/")
    public String nuevoDepartamento(Model model) {
        model.addAttribute("titulo","Registrar Nuevo Departamento");
        model.addAttribute("departamento", administracionFachada.nuevoDepartamento());
        return "departamento/nuevoDepartamento";
    }

    @PostMapping("/guardar/")
    public String guardarDepartamento(Model model, @ModelAttribute("departamento") Departamento departamento) {
        administracionFachada.guardarDepartamento(departamento);
        model.addAttribute("mensaje","Guardado Correctamente!!");
        return "redirect:/departamento/";
    }
 
    @GetMapping("/editar/{id}")
    public String actualizarDepartamento(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("titulo","Actualizar Departamento");
        model.addAttribute("departamento", administracionFachada.buscarDepartamentoId(id));
        return "departamento/nuevoDepartamento";
    }
 
    @GetMapping("/eliminar/{id}")
    public String emilinarPermisoSalidaId(@PathVariable(value = "id") long id, RedirectAttributes redirAttrs) {
        administracionFachada.inactivarDepartamento(id);
        redirAttrs.addFlashAttribute("success", "Departamento desactivado con éxito!!");
        return "redirect:/departamento/";
 
    }
    
}
