package uk.carlosramos.api.usuario.usuarioSinContra.controlador;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uk.carlosramos.api.usuario.usuarioSinContra.modelo.UsuarioSinContraModeloReq;
import uk.carlosramos.api.usuario.usuarioSinContra.modelo.UsuarioSinContraModeloRes;
import uk.carlosramos.api.usuario.usuarioSinContra.servicio.UsuarioSinContraServicio;

import java.util.Map;

@RestController
public class UsuarioSinContraControlador {

    @Autowired
    UsuarioSinContraServicio usuarioSinContraServicio;

    @PostMapping("/usuario-sin-contra")
    public Mono<?> crearUsuarioSinContra(@RequestHeader Map<String,String> header, @Valid @RequestBody UsuarioSinContraModeloReq usuarioSinContraModeloReq){
        return usuarioSinContraServicio.crearUsuarioSinContra(header,usuarioSinContraModeloReq);
    }

    @GetMapping("/usuarios-sin-contra")
    public Flux<UsuarioSinContraModeloRes> listarUsuario(){
        return usuarioSinContraServicio.listarUsuarios();
    }

    @PutMapping("/usuario-sin-contra")
    public Mono<?> actualizarUsuarioSinContra(@Valid @RequestBody UsuarioSinContraModeloReq usuarioSinContraModeloReq){
        return usuarioSinContraServicio.actualizarUsuarioSinContra(usuarioSinContraModeloReq);
    }

    @DeleteMapping("/usuario-sin-contra/{id}")
    public Mono<Void> eliminarUsuarioSinContra(@PathVariable String id){
        return usuarioSinContraServicio.eliminarUsuarioSinContra(id);
    }

    @GetMapping("/usuario-sin-contra/{id}")
    public Mono<UsuarioSinContraModeloRes> obtenerrUsuarioSinContra(@PathVariable String id){
        return usuarioSinContraServicio.obtenerUsuarioSinContra(id);
    }
}
