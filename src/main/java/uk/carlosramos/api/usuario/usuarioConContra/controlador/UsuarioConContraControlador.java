package uk.carlosramos.api.usuario.usuarioConContra.controlador;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uk.carlosramos.api.usuario.usuarioConContra.modelo.UsuarioConContraModeloReq;
import uk.carlosramos.api.usuario.usuarioConContra.modelo.UsuarioConContraModeloRes;
import uk.carlosramos.api.usuario.usuarioConContra.servicio.UsuarioConContraServicio;

import java.util.Map;

@RestController
public class UsuarioConContraControlador {

    @Autowired
    UsuarioConContraServicio usuarioConContraServicio;

    @PostMapping("/usuario-con-contra")
    public Mono<UsuarioConContraModeloRes> crearUsuarioConContra(
            @RequestHeader Map<String, String> header,
            @Valid @RequestBody UsuarioConContraModeloReq usuarioConContraModeloReq) {
        return usuarioConContraServicio.crearUsuarioConContra(header, usuarioConContraModeloReq);
    }

    @GetMapping("/usuarios-con-contra")
    public Flux<UsuarioConContraModeloRes> listarUsuario() {
        return usuarioConContraServicio.listarUsuarios();
    }

    @PutMapping("/usuario-con-contra")
    public Mono<UsuarioConContraModeloRes> actualizarUsuarioConContra(
            @RequestHeader Map<String, String> header,
            @Valid @RequestBody UsuarioConContraModeloReq usuarioConContraModeloReq) {
        return usuarioConContraServicio.actualizarUsuarioConContra(header,
                usuarioConContraModeloReq);
    }

    @DeleteMapping("/usuario-con-contra/{id}")
    public Mono<Void> eliminarUsuarioConContra(@PathVariable String id) {
        return usuarioConContraServicio.eliminarUsuarioConContra(id);
    }

    @GetMapping("/usuario-con-contra/{id}")
    public Mono<UsuarioConContraModeloRes> obtenerrUsuarioConContra(@PathVariable String id) {
        return usuarioConContraServicio.obtenerUsuarioConContra(id);
    }
}
