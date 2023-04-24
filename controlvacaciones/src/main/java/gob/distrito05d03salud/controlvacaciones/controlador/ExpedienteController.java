package gob.distrito05d03salud.controlvacaciones.controlador;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import gob.distrito05d03salud.controlvacaciones.dominio.controlvacaciones.Expediente;
import gob.distrito05d03salud.controlvacaciones.dominio.sujeto.Funcionario;
import gob.distrito05d03salud.controlvacaciones.fachada.AdministracionFachada;
import gob.distrito05d03salud.controlvacaciones.fachada.ControlVacacionesFachada;
import gob.distrito05d03salud.controlvacaciones.fachada.SujetosFachada;
import gob.distrito05d03salud.controlvacaciones.util.FormatoFechas;
import jakarta.websocket.server.PathParam;

@Controller
@RequestMapping("/expediente")
public class ExpedienteController {

    @Autowired
    private SujetosFachada sujetosFachada;
    @Autowired
    private AdministracionFachada administracionFachada;
    @Autowired
    private ControlVacacionesFachada controlVacacionesFachada;
    @Autowired
    private FormatoFechas formatosFecha;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("titulo", "Administración de Expedientes");

        model.addAttribute("listaExpedientes", administracionFachada.buscarListaTodosExpediente());
        return "expediente/expediente";
    }

    @GetMapping("/nuevo/")
    public String nuevoFuncionario(Model model) {
        model.addAttribute("titulo", "Registrar Nuevo Expediente");
        Expediente expediente = administracionFachada.nuevoExpediente();
        expediente.setFuncionario(sujetosFachada.nuevoFuncionario());
        model.addAttribute("expediente", administracionFachada.nuevoExpediente());
        return "expediente/nuevoExpediente";
    }

    @PostMapping("/guardar/")
    public String guardarExpediente(Model model,
            @ModelAttribute("expediente") Expediente expediente,
            RedirectAttributes redirAttrs) {
        if (expediente.getFuncionario().getCedula().equals("")) {
            model.addAttribute("error", "No ha seleccionado un funcionario!!");
            return "expediente/nuevoExpediente";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(expediente.getFechaInicioExpediente());
        expediente.setAnio(calendar.get(Calendar.YEAR));
        Expediente expedienteExiste = administracionFachada.buscarExpedienteFuncionarioAnio(
                expediente.getFuncionario(),
                formatosFecha.retornarAnioFechaFormatoCalendario(expediente.getFechaInicioExpediente()));
        if (expedienteExiste!=null) {
            model.addAttribute("error", "El expediente ya existe!!");
            return "expediente/nuevoExpediente";
        }
        if (expediente.getId() > 0) {
            redirAttrs.addFlashAttribute("info", "Actualizado correctamente");
        } else {
            redirAttrs.addFlashAttribute("success", "Guardado Correctamente!!");
        }
        administracionFachada.guardarExpediente(expediente);

        return "redirect:/expediente/";

    }

    @GetMapping("/editar/{id}")
    public String actualizarExpediente(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("titulo", "Actualizar Expediente");
        model.addAttribute("expediente", administracionFachada.buscarExpedienteId(id));
        return "expediente/nuevoExpediente";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarExpedienteId(@PathVariable(value = "id") long id) {
        administracionFachada.eliminarExpediente(id);
        return "redirect:/expediente/";

    }

    @PostMapping("/buscarCriterio/")
    public String buscarCriterio(Model model, @PathParam("criterio") String criterio, 
    @ModelAttribute("expediente") Expediente expediente) {

        //Expediente expediente = administracionFachada.nuevoExpediente();
        Funcionario funcionario = sujetosFachada.buscarFuncionarioCriterio(criterio);
        if (funcionario != null) {
            expediente.setFuncionario(funcionario);
            model.addAttribute("success", "Funcionario encontrado!!");
        } else {
            model.addAttribute("error", "Funcionario NO encontrado!!");
            //expediente.setFuncionario(sujetosFachada.nuevoFuncionario());
        }
        model.addAttribute("expediente", expediente);
        model.addAttribute("titulo", "Registrar Nuevo Expediente");
        return "expediente/nuevoExpediente";
    }

    @GetMapping("/expedientepersonal/")
    public String ExpedientePersonal(@CurrentSecurityContext SecurityContext context, Model model) {
        model.addAttribute("titulo", "Expedientes Personales");
        Funcionario funcionario = sujetosFachada.buscarFuncionarioCriterio(context.getAuthentication().getName());
        model.addAttribute("listaExpedientes", administracionFachada.buscarListaExpedienteFuncionario(funcionario));
        return "expediente/expedientePersonal";
    }

    @GetMapping("/expedientefuncionarios/")
    public String ExpedienteFuncionarios(@CurrentSecurityContext SecurityContext context, Model model) {
        model.addAttribute("titulo", "Expedientes de Funcionarios");
        Funcionario funcionario = sujetosFachada.buscarFuncionarioCriterio(context.getAuthentication().getName());
        if (funcionario.getUsuario().getRolUsuario().getDescripcion().equals("RESPONSABLETH")) {
            model.addAttribute("listaExpedientes", administracionFachada
                    .buscarListaExpedienteAnio(formatosFecha.retornarAnioFechaFormatoCalendario(new Date())));

        } else {
            List<Funcionario> listaFuncionarios = sujetosFachada
                    .buscarListaFuncionariosDepartamento(funcionario.getDepartamento());
            model.addAttribute("listaExpedientes",
                    administracionFachada.buscarListaExpedientesDepartamento(listaFuncionarios));

        }
        return "expediente/expedienteFuncionarios";
    }

    @PostMapping("/buscarExpedienteAnio/")
    public String buscarExpedienteAnio(Model model, @PathParam("criterio") String criterio,
            @PathParam("anioExpediente") Integer anioExpediente,
            RedirectAttributes redirAttrs) {

        Expediente expediente;
        Funcionario funcionario = sujetosFachada.buscarFuncionarioCriterio(criterio);
        if (funcionario != null) {
            expediente = administracionFachada.buscarExpedienteFuncionarioAnio(funcionario, anioExpediente);
            if (expediente != null) {
                // this.viewHomePage(model, redirAttrs);
                model.addAttribute("titulo", "Expedientes Personales");
                List<Expediente> listaExpedientes = new ArrayList<Expediente>();
                listaExpedientes.add(expediente);
                model.addAttribute("success", "Expediente encontrado!!");
                model.addAttribute("listaExpedientes", listaExpedientes);
                return "expediente/expedientePersonal";
                // return "redirect:/expediente/expedientepersonal/";
            } else {
                redirAttrs.addFlashAttribute("error", "Expediente no encontrado!!");
                return "redirect:/expediente/expedientepersonal/";
            }
        } else {
            redirAttrs.addFlashAttribute("error", "Funcionario no encontrado!!");
            return "redirect:/expediente/expedientepersonal/";
        }

    }

    @GetMapping("/detalles/{id}")
    public String ExpedienteDetalles(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("titulo", "Detalles del Expediente");
        Expediente expediente = administracionFachada.buscarExpedienteId(id);
        model.addAttribute("expediente", expediente);
        model.addAttribute("listaPermisosSalida",
                controlVacacionesFachada.buscarListaPermisosSalidaExpediente(expediente));
        model.addAttribute("listaSolicitudVacaciones",
                controlVacacionesFachada.buscarListaSolicitudVacacionesExpediente(expediente));
        return "expediente/detallesExpediente";
    }

}
