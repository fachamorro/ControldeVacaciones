package gob.distrito05d03salud.controlvacaciones.controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import gob.distrito05d03salud.controlvacaciones.dominio.controlvacaciones.PermisoSalida;
import gob.distrito05d03salud.controlvacaciones.dominio.sujeto.Funcionario;
import gob.distrito05d03salud.controlvacaciones.fachada.AdministracionFachada;
import gob.distrito05d03salud.controlvacaciones.fachada.ControlVacacionesFachada;
import gob.distrito05d03salud.controlvacaciones.fachada.SujetosFachada;
import jakarta.websocket.server.PathParam;

@Controller
@RequestMapping("/permisosalida")
public class PermisoSalidaController {

    @Autowired
    private ControlVacacionesFachada controlVacacionesFachada;
    @Autowired
    private SujetosFachada sujetosFachada;
    @Autowired
    private AdministracionFachada administracionFachada;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("titulo", "Administración de Permisos de Salida");
        model.addAttribute("listaPermisosSalida", controlVacacionesFachada.buscarListaTodosPermisosSalida());
        return "permisosalida/permisosalida";
    }

    @GetMapping("/nuevo/")
    public String nuevoPermisoSalida(Model model) {
        PermisoSalida permisoSalida = controlVacacionesFachada.nuevoPermisoSalida();
        permisoSalida.setExpediente(administracionFachada.nuevoExpediente(sujetosFachada.nuevoFuncionario()));
        model.addAttribute("permisoSalida", permisoSalida);
        Calendar calendario = Calendar.getInstance();
        calendario.add(Calendar.MONTH, -2);
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("fechaMinima", formatoFecha.format(calendario.getTime()));
        calendario.add(Calendar.MONTH, 3);
        model.addAttribute("fechaMaxima", formatoFecha.format(calendario.getTime()));
        model.addAttribute("titulo", "Registrar Nuevo Permiso Salida");
        return "permisosalida/nuevoPermisoSalida";
    }

    @PostMapping("/guardar/")
    public String guardarPermisoSalida(Model model, @ModelAttribute("permisoSalida") PermisoSalida permisoSalida,
            BindingResult result, RedirectAttributes redirAttrs) throws ParseException {
        if (permisoSalida.getExpediente().getFuncionario().getCedula().equals("")) {
            model.addAttribute("error", "No ha seleccionado un funcionario!!");
            return "permisosalida/nuevoPermisoSalida";
        }
        boolean respuesta = controlVacacionesFachada.guardarPermisoSalida(permisoSalida);
        if (respuesta) {

            redirAttrs.addFlashAttribute("success", "Permiso de Salida Guardado Correctamente!!");
        } else {
            redirAttrs.addFlashAttribute("error", "Error al guardar Permiso de Salida!!");
        }
        return "redirect:/permisosalida/";
    }

    @GetMapping("/editar/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("titulo", "Actualizar Permiso Salida");
        Calendar calendario = Calendar.getInstance();
        calendario.add(Calendar.MONTH, -2);
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("fechaMinima", formatoFecha.format(calendario.getTime()));
        calendario.add(Calendar.MONTH, 3);
        model.addAttribute("fechaMaxima", formatoFecha.format(calendario.getTime()));
        model.addAttribute("permisoSalida", controlVacacionesFachada.buscarPermisoSalidaId(id));
        return "permisosalida/nuevoPermisoSalida";
    }

    @GetMapping("/eliminar/{id}")
    public String emilinarPermisoSalidaId(@PathVariable(value = "id") long id, RedirectAttributes redirAttrs) {
        controlVacacionesFachada.eliminarPermisoSalida(id);
        redirAttrs.addFlashAttribute("success", "Permiso de Salida eliminado Correctamente!!");
        return "redirect:/permisosalida/";

    }

    // Buscar funcionarios por criterio
    @PostMapping("/buscarCriterio/")
    public String buscarFuncionarioCriterio(Model model, @PathParam("criterio") String criterio,
            RedirectAttributes redirAttrs,  @ModelAttribute("permisoSalida") PermisoSalida permisoSalida) {
        Funcionario funcionario = sujetosFachada.buscarFuncionarioCriterio(criterio);
        if (funcionario != null) {
            permisoSalida.setExpediente(administracionFachada.nuevoExpediente(funcionario));
            model.addAttribute("success", "Funcionario encontrado!!");

        } else {
            //permisoSalida.setExpediente(administracionFachada.nuevoExpediente(sujetosFachada.nuevoFuncionario()));
            model.addAttribute("error", "Funcionario NO encontrado!!");

        }
        //PermisoSalida permisoSalida = controlVacacionesFachada.nuevoPermisoSalida();
        Calendar calendario = Calendar.getInstance();
        calendario.add(Calendar.MONTH, -2);
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("fechaMinima", formatoFecha.format(calendario.getTime()));
        calendario.add(Calendar.MONTH, 3);
        model.addAttribute("fechaMaxima", formatoFecha.format(calendario.getTime()));
        
        model.addAttribute("permisoSalida", permisoSalida);
        model.addAttribute("titulo", "Registrar Nuevo Permiso Salida");
        return "permisosalida/nuevoPermisoSalida";
    }

}
