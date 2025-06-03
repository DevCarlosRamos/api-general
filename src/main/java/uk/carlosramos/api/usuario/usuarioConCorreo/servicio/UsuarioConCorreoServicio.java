package uk.carlosramos.api.usuario.usuarioConCorreo.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uk.carlosramos.api.errores.ClaveIncorrectaException;
import uk.carlosramos.api.usuario.usuarioConCorreo.modelo.UsuarioConCorreoModeloReq;
import uk.carlosramos.api.usuario.usuarioConCorreo.modelo.UsuarioConCorreoModeloRes;
import uk.carlosramos.api.usuario.usuarioConCorreo.repository.UsuarioConCorreoRepository;

import java.util.Map;

@Service
public class UsuarioConCorreoServicio {

    @Autowired
    UsuarioConCorreoRepository usuarioConCorreoRepository;

    public Mono<UsuarioConCorreoModeloRes> crearUsuarioConContra(Map<String,String> header, UsuarioConCorreoModeloReq usuarioConCorreoModeloReq) {
        return Mono.justOrEmpty(header.get("clave"))
                .filter("u5WeuEcK92ZZoA{N29w~{BrY%y30F%p6p]"::equals)
                .switchIfEmpty(Mono.error(new ClaveIncorrectaException("Clave incorrecta")))
                .flatMap(claveValida ->
                        usuarioConCorreoRepository
                                .save(usuarioConCorreoModeloReq)
                                .map(usuario ->
                                        new UsuarioConCorreoModeloRes(
                                                usuario.getNombre(),
                                                usuario.getPassword(),
                                                usuario.getId(),
                                                usuario.getCorreo()))
                );
    }

    public Flux<UsuarioConCorreoModeloRes> listarUsuarios() {
        return usuarioConCorreoRepository.findAll().map(usuario -> new UsuarioConCorreoModeloRes(
                        usuario.getNombre(),
                        usuario.getPassword(),
                        usuario.getId(),
                        usuario.getCorreo()
                )
        );
    }

    public Mono<UsuarioConCorreoModeloRes> obtenerUsuarioConContra(String id) {

        return usuarioConCorreoRepository
                .findById(id)
                .map(usuario -> new UsuarioConCorreoModeloRes(
                        usuario.getNombre(),
                        usuario.getPassword(),
                        usuario.getId(),
                        usuario.getCorreo())
                );
    }

    public Mono<Void> eliminarUsuarioConContra(String id) {

        return usuarioConCorreoRepository.deleteById(id);
    }

    public Mono<UsuarioConCorreoModeloRes> actualizarUsuarioConContra(UsuarioConCorreoModeloReq usuarioConCorreoModeloReq) {
        return usuarioConCorreoRepository
                .save(usuarioConCorreoModeloReq)
                .map(usuario -> new UsuarioConCorreoModeloRes(
                        usuario.getNombre(),
                        usuario.getPassword(),
                        usuario.getId(),
                        usuario.getCorreo())
                );
    }

}
