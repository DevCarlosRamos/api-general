package uk.carlosramos.api.usuario.usuarioSinContra.modelo;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UsuarioSinContraModeloReq {

    @Id
    private String id;
    @NotNull(message = "El nombre no puede ser nulo")
    private String nombre;

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

}
