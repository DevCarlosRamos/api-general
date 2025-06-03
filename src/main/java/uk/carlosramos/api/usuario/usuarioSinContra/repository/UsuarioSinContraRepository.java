package uk.carlosramos.api.usuario.usuarioSinContra.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import uk.carlosramos.api.usuario.usuarioSinContra.modelo.UsuarioSinContraModeloReq;

public interface UsuarioSinContraRepository extends ReactiveCrudRepository<UsuarioSinContraModeloReq,String> {
}
