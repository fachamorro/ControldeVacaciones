package gob.distrito05d03salud.controlvacaciones.fachada;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import gob.distrito05d03salud.controlvacaciones.dominio.seguridad.Rol;
import gob.distrito05d03salud.controlvacaciones.dominio.seguridad.Usuario;
import gob.distrito05d03salud.controlvacaciones.servicio.RolService;
import gob.distrito05d03salud.controlvacaciones.servicio.UsuarioService;
import gob.distrito05d03salud.controlvacaciones.util.MensajeInformativo;

@Component
public class SeguridadFachada {
    @Autowired
    private UsuarioService usuarioServicio;
    @Autowired
    private RolService rolServicio;

    // ****** Usuario **************/

    public Usuario nuevoUsuario() {
        return new Usuario();
    }

    public Usuario nuevoUsuario(Usuario usuario) {
        return new Usuario(usuario.getUsername(), usuario.getContrasenia(), usuario.getRolUsuario());
    }

    public Usuario guardarUsuario(Usuario Usuario) {
        return usuarioServicio.guardar(Usuario);
    }

    public Usuario buscarUsuarioId(long id) {
        return usuarioServicio.buscarPorId(id);
    }

    public MensajeInformativo actualizarContrasenia(String username, String contraseniaAnterior,
            String contraseniaNueva, String contraseniaRepetir) {
        Usuario usuario = usuarioServicio.buscarPorUsername(username);
        if (!BCrypt.checkpw(contraseniaAnterior, usuario.getContrasenia())) {
            return new MensajeInformativo("error", "La contraseña actual no coincide!");
        }
        if (!contraseniaNueva.equals(contraseniaRepetir)) {
            return new MensajeInformativo("error", "La Nueva Contraseña NO coincide con Repetir Nueva Contraseña!");
        }
        usuario.setContrasenia(contraseniaNueva);
        usuarioServicio.guardar(usuario);
        return new MensajeInformativo("success", "Contrasena cambiada correctamente!");
    }

    public List<Usuario> buscarListaTodosUsuario() {
        return usuarioServicio.listarTodosUsuarios();
    }

    public Usuario existeUsuario(String usuario, String contrasenia) {
        return usuarioServicio.buscarPorUsuarioContrasenia(usuario, contrasenia);
    }

    public Usuario actualizarUsuario(Usuario Usuario) {
        return usuarioServicio.guardar(Usuario);
    }

    public void eliminarUsuario(long id) {
        usuarioServicio.eliminar(id);
    }

    // ****** Rol **************/
    public Rol nuevoRolUsuario() {
        Rol rol = new Rol();
        return rol;
    }

    public Rol guardarRolUsuario(Rol rolUsuario) {
        return rolServicio.guardar(rolUsuario);
    }

    public Rol buscarRolUsuarioId(long id) {
        return rolServicio.buscarPorId(id);

    }

    public List<Rol> buscarListaTodosRolesUsuario() {
        return rolServicio.listarTodosRoles();
    }

    public Rol actualizarRolUsuario(Rol rolUsuario) {
        return rolServicio.guardar(rolUsuario);
    }

    public void eliminarRolUsuario(long id) {
        rolServicio.eliminar(id);
    }
}
