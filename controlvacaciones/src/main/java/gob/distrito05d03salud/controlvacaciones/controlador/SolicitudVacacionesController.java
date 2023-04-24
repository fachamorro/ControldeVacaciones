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
import gob.distrito05d03salud.controlvacaciones.dominio.controlvacaciones.SolicitudVacaciones;
import gob.distrito05d03salud.controlvacaciones.dominio.sujeto.Funcionario;
import gob.distrito05d03salud.controlvacaciones.fachada.AdministracionFachada;
import gob.distrito05d03salud.controlvacaciones.fachada.ControlVacacionesFachada;
import gob.distrito05d03salud.controlvacaciones.fachada.SujetosFachada;
import jakarta.websocket.server.PathParam;

@Controller
@RequestMapping("/solicitudvacaciones")
public class SolicitudVacacionesController {

    @Autowired
    private ControlVacacionesFachada controlVacacionesFachada;
    @Autowired
    private SujetosFachada sujetosFachada;
    @Autowired
    private AdministracionFachada administracionFachada;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("titulo", "Administración de Solicitudes de Vacaciones");
        model.addAttribute("listaSolicitudVacaciones", controlVacacionesFachada.buscarListaTodosSolicitudVacaciones());
        return "solicitudvacaciones/solicitudvacaciones";
    }

    @GetMapping("/nuevo/")
    public String nuevoSolicitudVacaciones(Model model) {
        SolicitudVacaciones solicitudVacaciones = controlVacacionesFachada.nuevoSolicitudVacaciones();
        solicitudVacaciones.setExpediente(administracionFachada.nuevoExpediente(sujetosFachada.nuevoFuncionario()));
        model.addAttribute("solicitudVacaciones", solicitudVacaciones);
        Calendar calendario = Calendar.getInstance();
        calendario.add(Calendar.MONTH, -2);
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("fechaMinima", formatoFecha.format(calendario.getTime()));
        calendario.add(Calendar.MONTH, 5);
        model.addAttribute("fechaMaxima", formatoFecha.format(calendario.getTime()));
        model.addAttribute("titulo", "Registrar Nueva Solicitud Vacaciones");
        return "solicitudvacaciones/nuevoSolicitudVacaciones";
    }

    @PostMapping("/guardar/")
    public String guardarSolicitudVacaciones(Model model,
            @ModelAttribute("solicitudVacaciones") SolicitudVacaciones solicitudVacaciones,
            BindingResult result, RedirectAttributes redirAttrs) throws ParseException {

        if (solicitudVacaciones.getExpediente().getFuncionario().getCedula().equals("")) {
            model.addAttribute("error", "No ha seleccionado un funcionario!!");
            return "solicitudvacaciones/nuevoSolicitudVacaciones";
        }
        boolean respuesta = controlVacacionesFachada.guardarSolicitudVacaciones(solicitudVacaciones);
        if (respuesta) {

            redirAttrs.addFlashAttribute("success", "Solicitud de Vacaciones Guardado Correctamente!!");
        } else {
            redirAttrs.addFlashAttribute("error", "Error al guardar Solicitud de Vacaciones!!");
        }
        return "redirect:/solicitudvacaciones/";
    }

    @GetMapping("/editar/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("titulo", "Actualizar Solicitud de Vacaciones");
        model.addAttribute("solicitudVacaciones", controlVacacionesFachada.buscarSolicitudVacacionesId(id));
        return "solicitudvacaciones/nuevoSolicitudVacaciones";
    }

    @GetMapping("/eliminar/{id}")
    public String emilinarSolicitudVacacionesId(@PathVariable(value = "id") long id, RedirectAttributes redirAttrs) {
        controlVacacionesFachada.eliminarSolicitudVacaciones(id);
        redirAttrs.addFlashAttribute("success", "Solicitud de Vacaciones eliminado Correctamente!!");
        return "redirect:/solicitudvacaciones/";

    }

    // Buscar funcionarios por criterio
    @PostMapping("/buscarCriterio/")
    public String buscarFuncionarioCriterio(Model model, @PathParam("criterio") String criterio,
    @ModelAttribute("solicitudVacaciones") SolicitudVacaciones solicitudVacaciones,
            RedirectAttributes redirAttrs) {
        Funcionario funcionario = sujetosFachada.buscarFuncionarioCriterio(criterio);
        //SolicitudVacaciones solicitudVacaciones = controlVacacionesFachada.nuevoSolicitudVacaciones();
        Calendar calendario = Calendar.getInstance();
        calendario.add(Calendar.MONTH, -2);
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("fechaMinima", formatoFecha.format(calendario.getTime()));
        calendario.add(Calendar.MONTH, 3);
        model.addAttribute("fechaMaxima", formatoFecha.format(calendario.getTime()));
        if (funcionario != null) {
            solicitudVacaciones.setExpediente(administracionFachada.nuevoExpediente(funcionario));
            model.addAttribute("success", "Funcionario encontrado!!");
        } else {
            //solicitudVacaciones.setExpediente(administracionFachada.nuevoExpediente(sujetosFachada.nuevoFuncionario()));
            model.addAttribute("error", "Funcionario NO encontrado!!");
        }
        model.addAttribute("solicitudVacaciones", solicitudVacaciones);
        model.addAttribute("titulo", "Registrar Nueva Solicitud de Vacaciones");
        return "solicitudvacaciones/nuevoSolicitudVacaciones";
    }

}
