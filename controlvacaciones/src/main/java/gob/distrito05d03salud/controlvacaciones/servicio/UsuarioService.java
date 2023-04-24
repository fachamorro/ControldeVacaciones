package gob.distrito05d03salud.controlvacaciones.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import gob.distrito05d03salud.controlvacaciones.dominio.seguridad.Usuario;
import gob.distrito05d03salud.controlvacaciones.repositorio.UsuarioRepository;
import gob.distrito05d03salud.controlvacaciones.util.MyUserDetails;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> listarTodosUsuarios() {
        return repository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(null);
    }

    public Usuario buscarPorUsername(String username) {
        return repository.findByUsername(username);
    }

    public Usuario buscarPorUsuarioContrasenia(String usuario, String contrasenia) {
        return repository.findByUsernameAndContrasenia(usuario, contrasenia);
    }

    @Transactional
    public Usuario actualizar(Usuario usuario) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(usuario.getContrasenia());
        usuario.setContrasenia(encodedPassword);
        return repository.save(usuario);
    }

    @Transactional
    public Usuario guardar(Usuario usuario) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(usuario.getContrasenia());
        usuario.setContrasenia(encodedPassword);
        return repository.save(usuario);
    }

    @Transactional
    public void eliminar(long id) {
        // check if object with this id exists
        buscarPorId(id);
        repository.deleteById(id);
    }

    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = repository.findByUsername(username);
        if (user == null) {
            System.out.println("Usuario o password incorrectos");
            throw new UsernameNotFoundException("Usuario o password incorrectos");
        }
        return new MyUserDetails(user);
    }

}
