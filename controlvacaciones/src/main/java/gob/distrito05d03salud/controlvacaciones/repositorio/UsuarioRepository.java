package gob.distrito05d03salud.controlvacaciones.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import gob.distrito05d03salud.controlvacaciones.dominio.seguridad.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsernameAndContrasenia(@Param("username") String username, @Param("contrasenia") String password);
    Usuario findByUsername(@Param("username") String username);
    
}