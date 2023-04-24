package gob.distrito05d03salud.controlvacaciones.controlador;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import gob.distrito05d03salud.controlvacaciones.dominio.controlvacaciones.Expediente;
import gob.distrito05d03salud.controlvacaciones.dominio.sujeto.Funcionario;
import gob.distrito05d03salud.controlvacaciones.fachada.AdministracionFachada;
import gob.distrito05d03salud.controlvacaciones.fachada.ControlVacacionesFachada;
import gob.distrito05d03salud.controlvacaciones.fachada.SujetosFachada;
import gob.distrito05d03salud.controlvacaciones.util.FormatoFechas;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

    @Autowired
    private AdministracionFachada administracionFachada;
    @Autowired
    private ControlVacacionesFachada controlVacacionesFachada;
    @Autowired
    private SujetosFachada sujetosFachada;
    @Autowired
    private FormatoFechas formatoFechas;

    @GetMapping("/")
    public String index(@CurrentSecurityContext SecurityContext context, Model model) {
        return home(context,model);
    }

    @GetMapping("/login/")
    public String login() {
        return "login";
    }

    @GetMapping("/invalidSession/")
    public String invalidSession(HttpServletResponse response) {
        return "invalidSession";
    }

    @GetMapping("/home/")
    public String home(@CurrentSecurityContext SecurityContext context, Model model) {
        //Calendar calendario = Calendar.getInstance();
        //calendario.setTime(new Date());
        //SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy");
        //model.addAttribute("titulo", context.getAuthentication().getName());
        Funcionario funcionario = sujetosFachada.buscarFuncionarioCriterio(context.getAuthentication().getName());
        Expediente expediente=administracionFachada.buscarExpedienteFuncionarioAnio(funcionario, formatoFechas.retornarAnioFechaFormatoCalendario(new Date()));
        if(expediente==null){
            expediente=administracionFachada.nuevoExpediente();
        }
        model.addAttribute("expediente", expediente);
        model.addAttribute("anio", formatoFechas.retornarAnioFechaFormatoCalendario(new Date()));
        model.addAttribute("permisoSalida", controlVacacionesFachada.contarPermisosSalidaExpediente(expediente));
        model.addAttribute("solicitudVacaciones", controlVacacionesFachada.contarSolicitudVacacionesExpediente(expediente));
        return "home";
    }

    /*@PostMapping("/validarusuario/")
    public String validarUsuario(Model model, @ModelAttribute("username") String usuario,
            @ModelAttribute("password") String contrasenia,
            BindingResult result, RedirectAttributes redirAttrs) {

        Usuario usuarioRegistrado = seguridadFachada.existeUsuario(usuario, contrasenia);
        if (usuarioRegistrado == null) {
            redirAttrs.addFlashAttribute("error", "Usuario o contraseña incorrectos!");
            return "redirect:/";
        } else {
            redirAttrs.addFlashAttribute("success", "Bienvenido!!");
        }
        return "home";

    }*/
}