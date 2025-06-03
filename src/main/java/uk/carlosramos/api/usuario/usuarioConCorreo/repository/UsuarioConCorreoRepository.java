package uk.carlosramos.api.usuario.usuarioConCorreo.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import uk.carlosramos.api.usuario.usuarioConCorreo.modelo.UsuarioConCorreoModeloReq;

public interface UsuarioConCorreoRepository extends ReactiveCrudRepository<UsuarioConCorreoModeloReq,String> {
}
