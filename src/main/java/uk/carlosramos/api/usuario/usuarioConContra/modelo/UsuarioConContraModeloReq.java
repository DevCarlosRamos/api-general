package uk.carlosramos.api.usuario.usuarioConContra.modelo;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UsuarioConContraModeloReq {

    @Id
    private String id;
    @NotNull(message = "El nombre no puede ser nulo")
    private String nombre;

    @Transient
    @NotNull(message = "La clave no puede ser nulo")
    private String clave;

    @NotNull(message = "El password no puede ser nulo")
    private String password;

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

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
