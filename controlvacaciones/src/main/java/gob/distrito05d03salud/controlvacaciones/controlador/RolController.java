package gob.distrito05d03salud.controlvacaciones.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gob.distrito05d03salud.controlvacaciones.dominio.seguridad.Rol;
import gob.distrito05d03salud.controlvacaciones.fachada.SeguridadFachada;

@Controller
@RequestMapping("/rolusuario")
public class RolController {

    @Autowired
    private SeguridadFachada seguridadFachada;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("titulo", "Administración de Roles de Usuario");        
        model.addAttribute("listaRolesusuario", seguridadFachada.buscarListaTodosRolesUsuario());
        return "rolusuario/rolusuario";
    }

    @GetMapping("/nuevo/")
    public String nuevoRolUsuario(Model model) {
        model.addAttribute("titulo","Registrar Nuevo Rol de Usuario");
        model.addAttribute("rolusuario", seguridadFachada.nuevoRolUsuario());
        return "rolusuario/nuevoRolusuario";
    }

    @PostMapping("/guardar/")
    public String guardarRolUsuario(Model model, @ModelAttribute("rolusuario") Rol rolusuario) {
        seguridadFachada.guardarRolUsuario(rolusuario);
        model.addAttribute("mensaje","Guardado Correctamente!!");
        return "redirect:/rolusuario/";
    }
 
    @GetMapping("/editar/{id}")
    public String actualizarRolusuario(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("titulo","Actualizar Rol de Usuario");
        model.addAttribute("rolusuario", seguridadFachada.buscarRolUsuarioId(id));
        return "rolusuario/nuevoRolusuario";
    }
 
    @GetMapping("/eliminar/{id}")
    public String emilinarRolusuarioId(@PathVariable(value = "id") long id) {
        seguridadFachada.eliminarRolUsuario(id);
        return "redirect:/rolusuario/";
 
    }
    
}
