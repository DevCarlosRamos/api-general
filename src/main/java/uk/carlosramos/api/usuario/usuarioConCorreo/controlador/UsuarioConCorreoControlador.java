package uk.carlosramos.api.usuario.usuarioConCorreo.controlador;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uk.carlosramos.api.usuario.usuarioConCorreo.modelo.UsuarioConCorreoModeloReq;
import uk.carlosramos.api.usuario.usuarioConCorreo.modelo.UsuarioConCorreoModeloRes;
import uk.carlosramos.api.usuario.usuarioConCorreo.servicio.UsuarioConCorreoServicio;

import java.util.Map;

@RestController
public class UsuarioConCorreoControlador {

    @Autowired
    UsuarioConCorreoServicio usuarioConCorreoServicio;

    @PostMapping("/usuario-con-correo")
    public Mono<UsuarioConCorreoModeloRes> crearUsuarioConContra(@RequestHeader Map<String,String> header,
            @Valid @RequestBody UsuarioConCorreoModeloReq usuarioConCorreoModeloReq){
        return usuarioConCorreoServicio.crearUsuarioConContra(header, usuarioConCorreoModeloReq);
    }

    @GetMapping("/usuarios-con-correo")
    public Flux<UsuarioConCorreoModeloRes> listarUsuario(){
        return usuarioConCorreoServicio.listarUsuarios();
    }

    @PutMapping("/usuario-con-correo")
    public Mono<UsuarioConCorreoModeloRes> actualizarUsuarioConContra(@Valid @RequestBody UsuarioConCorreoModeloReq usuarioConCorreoModeloReq){
        return usuarioConCorreoServicio.actualizarUsuarioConContra(usuarioConCorreoModeloReq);
    }

    @DeleteMapping("/usuario-con-correo/{id}")
    public Mono<Void> eliminarUsuarioConContra(@PathVariable String id){
        return usuarioConCorreoServicio.eliminarUsuarioConContra(id);
    }

    @GetMapping("/usuario-con-correo/{id}")
    public Mono<UsuarioConCorreoModeloRes> obtenerrUsuarioConContra(@PathVariable String id){
        return usuarioConCorreoServicio.obtenerUsuarioConContra(id);
    }
}
