package uk.carlosramos.api.usuario.usuarioSinContra.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uk.carlosramos.api.errores.ClaveIncorrectaException;
import uk.carlosramos.api.usuario.usuarioSinContra.modelo.UsuarioSinContraModeloReq;
import uk.carlosramos.api.usuario.usuarioSinContra.modelo.UsuarioSinContraModeloRes;
import uk.carlosramos.api.usuario.usuarioSinContra.repository.UsuarioSinContraRepository;

import java.util.Map;

@Service
public class UsuarioSinContraServicio {

    @Autowired
    UsuarioSinContraRepository usuarioSinContraRepository;

    public Mono<UsuarioSinContraModeloRes> crearUsuarioSinContra(
            Map<String,String> header,
            UsuarioSinContraModeloReq usuarioSinContraModeloReq) {
        return Mono.justOrEmpty(header.get("clave"))
                .filter("u5WeuEcK92ZZoA{N29w~{BrY%y30F%p6p]"::equals)
                .switchIfEmpty(Mono.error(new ClaveIncorrectaException("Clave incorrecta")))
                .flatMap(claveValida ->
                        usuarioSinContraRepository
                                .save(usuarioSinContraModeloReq)
                                .map(usuario ->
                                        new UsuarioSinContraModeloRes(usuario.getNombre(),
                                                usuario.getId()))
                );
    }



    public Flux<UsuarioSinContraModeloRes> listarUsuarios() {
        return usuarioSinContraRepository.findAll().map(usuario -> new UsuarioSinContraModeloRes(
                        usuario.getNombre(), usuario.getId()
                )
        );
    }

    public Mono<UsuarioSinContraModeloRes> obtenerUsuarioSinContra(String id) {

        return usuarioSinContraRepository
                .findById(id)
                .map(usuario -> new UsuarioSinContraModeloRes(
                                usuario.getNombre(), usuario.getId())
                );
    }

    public Mono<Void> eliminarUsuarioSinContra(String id) {

        return usuarioSinContraRepository.deleteById(id);
    }

    public Mono<UsuarioSinContraModeloRes> actualizarUsuarioSinContra(UsuarioSinContraModeloReq usuarioSinContraModeloReq) {
        return usuarioSinContraRepository
                .save(usuarioSinContraModeloReq)
                .map(usuario -> new UsuarioSinContraModeloRes(
                        usuario.getNombre(), usuario.getId())
        );
    }

}
