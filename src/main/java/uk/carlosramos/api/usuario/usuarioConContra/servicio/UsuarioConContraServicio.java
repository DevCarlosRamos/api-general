package uk.carlosramos.api.usuario.usuarioConContra.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uk.carlosramos.api.errores.ClaveIncorrectaException;
import uk.carlosramos.api.usuario.usuarioConContra.modelo.UsuarioConContraModeloReq;
import uk.carlosramos.api.usuario.usuarioConContra.modelo.UsuarioConContraModeloRes;
import uk.carlosramos.api.usuario.usuarioConContra.repository.UsuarioConContraRepository;

import java.util.Map;

@Service
public class UsuarioConContraServicio {

    @Autowired
    UsuarioConContraRepository usuarioConContraRepository;

    public Mono<UsuarioConContraModeloRes> crearUsuarioConContra(
            Map<String,String> header,
            UsuarioConContraModeloReq usuarioConContraModeloReq) {
        return Mono.justOrEmpty(header.get("clave"))
                .filter("u5WeuEcK92ZZoA{N29w~{BrY%y30F%p6p]"::equals)
                .switchIfEmpty(Mono.error(new ClaveIncorrectaException("Clave incorrecta")))
                .flatMap(claveValida ->
                        usuarioConContraRepository
                                .save(usuarioConContraModeloReq)
                                .map(usuario ->
                                        new UsuarioConContraModeloRes(
                                                usuario.getNombre(),
                                                usuario.getPassword(),
                                                usuario.getId()))
                );
    }

    public Flux<UsuarioConContraModeloRes> listarUsuarios() {
        return usuarioConContraRepository.findAll().map(usuario -> new UsuarioConContraModeloRes(
                        usuario.getNombre(),
                        usuario.getPassword(),
                        usuario.getId()
                )
        );
    }

    public Mono<UsuarioConContraModeloRes> obtenerUsuarioConContra(String id) {

        return usuarioConContraRepository
                .findById(id)
                .map(usuario -> new UsuarioConContraModeloRes(
                        usuario.getNombre(),
                        usuario.getPassword(),
                        usuario.getId())
                );
    }

    public Mono<Void> eliminarUsuarioConContra(String id) {

        return usuarioConContraRepository.deleteById(id);
    }

    public Mono<UsuarioConContraModeloRes> actualizarUsuarioConContra(
            Map<String,String> header,
            UsuarioConContraModeloReq usuarioConContraModeloReq) {
        return Mono.justOrEmpty(header.get("clave"))
                .filter("u5WeuEcK92ZZoA{N29w~{BrY%y30F%p6p]"::equals)
                .switchIfEmpty(Mono.error(new ClaveIncorrectaException("Clave incorrecta")))
                .flatMap(claveValida ->
                        usuarioConContraRepository
                                .save(usuarioConContraModeloReq)
                                .map(usuario ->
                                        new UsuarioConContraModeloRes(
                                                usuario.getNombre(),
                                                usuario.getPassword(),
                                                usuario.getId()))
                );
    }

}
