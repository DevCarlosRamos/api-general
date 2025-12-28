package uk.carlosramos.api.usuario.usuarioConCorreo.modelo;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UsuarioConCorreoModeloReq {

    @Id
    private String id;
    @NotNull(message = "El nombre no puede ser nulo")
    private String nombre;

    @NotNull(message = "El password no puede ser nulo")
    private String password;

    @NotNull(message = "El correo no puede ser nulo" )
    private String correo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
