package uk.carlosramos.api.usuario.usuarioConContra.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import uk.carlosramos.api.usuario.usuarioConContra.modelo.UsuarioConContraModeloReq;

public interface UsuarioConContraRepository extends ReactiveCrudRepository<UsuarioConContraModeloReq,String> {
}
