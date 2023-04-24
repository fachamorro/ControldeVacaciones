package gob.distrito05d03salud.controlvacaciones.controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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

import gob.distrito05d03salud.controlvacaciones.dominio.seguridad.Usuario;
import gob.distrito05d03salud.controlvacaciones.dominio.sujeto.Funcionario;
import gob.distrito05d03salud.controlvacaciones.fachada.AdministracionFachada;
import gob.distrito05d03salud.controlvacaciones.fachada.SeguridadFachada;
import gob.distrito05d03salud.controlvacaciones.fachada.SujetosFachada;
import gob.distrito05d03salud.controlvacaciones.util.MensajeInformativo;
import jakarta.websocket.server.PathParam;

@Controller
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private SujetosFachada sujetosFachada;
    @Autowired
    private SeguridadFachada seguridadFachada;
    @Autowired
    private AdministracionFachada administracionFachada;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("titulo", "Administración de Funcionarios");

        model.addAttribute("listaFuncionarios", sujetosFachada.buscarListaTodosFuncionario());
        return "sujeto/funcionario";
    }

    @GetMapping("/nuevo/")
    public String nuevoFuncionario(Model model) {
        model.addAttribute("titulo", "Registrar Nuevo Funcionario");
        Calendar calendario = Calendar.getInstance();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("fechaMaxima", formatoFecha.format(calendario.getTime()));
        model.addAttribute("funcionario", sujetosFachada.nuevoFuncionario());
        model.addAttribute("listaDepartamentos", administracionFachada.buscarListaTodosDepartamento());
        model.addAttribute("listaRolUsuario", seguridadFachada.buscarListaTodosRolesUsuario());
        // model.addAttribute("usuario", seguridadFachada.nuevoUsuario());

        return "sujeto/nuevoFuncionario";
    }

    @PostMapping("/guardar/")
    public String guardarFuncionario(Model model,
            @ModelAttribute("funcionario") Funcionario funcionario) throws ParseException {
        Usuario user = seguridadFachada.nuevoUsuario();
        user = seguridadFachada.guardarUsuario(funcionario.getUsuario());
        funcionario.setUsuario(user);
        sujetosFachada.guardarFuncionario(funcionario);
        model.addAttribute("mensaje", "Guardado Correctamente!!");
        return "redirect:/funcionario/";
    }

    @GetMapping("/editar/{id}")
    public String actualizarFuncionario(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("titulo", "Actualizar funcionario");
        Calendar calendario = Calendar.getInstance();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("fechaMaxima", formatoFecha.format(calendario.getTime()));
        model.addAttribute("funcionario", sujetosFachada.buscarFuncionarioId(id));
        model.addAttribute("listaDepartamentos", administracionFachada.buscarListaTodosDepartamento());
        model.addAttribute("listaRolUsuario", seguridadFachada.buscarListaTodosRolesUsuario());
        return "sujeto/nuevoFuncionario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarFuncionarioId(@PathVariable(value = "id") long id) {
        sujetosFachada.eliminarFuncionario(id);
        return "redirect:/funcionario/";

    }

    @GetMapping("/perfilusuario/")
    public String perfilUsuario(@CurrentSecurityContext SecurityContext context, Model model) {
        Funcionario funcionario = sujetosFachada.buscarFuncionarioCriterio(context.getAuthentication().getName());
        model.addAttribute("funcionario", funcionario);
        model.addAttribute("titulo", "Perfil de Usuario");
        return "sujeto/perfilusuario";
    }

    @PostMapping("/actualizarcontrasenia/")
    public String actualizarContrasenia(@CurrentSecurityContext SecurityContext context, Model model,
            RedirectAttributes redirAttrs,
            @PathParam("contraseniaAnterior") String contraseniaAnterior,
            @PathParam("contraseniaNueva") String contraseniaNueva,
            @PathParam("contraseniaRepetir") String contraseniaRepetir) {

        MensajeInformativo msm = seguridadFachada.actualizarContrasenia(context.getAuthentication().getName(),
                contraseniaAnterior, contraseniaNueva, contraseniaRepetir);
        redirAttrs.addFlashAttribute(msm.getTipo(), msm.getMensaje());
        return "redirect:/funcionario/perfilusuario/";

    }

}
