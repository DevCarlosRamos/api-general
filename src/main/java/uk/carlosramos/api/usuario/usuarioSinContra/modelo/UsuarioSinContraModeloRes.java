package uk.carlosramos.api.usuario.usuarioSinContra.modelo;

public class UsuarioSinContraModeloRes {

    private String id;
    private String nombre;

    public UsuarioSinContraModeloRes() {
    }

    public UsuarioSinContraModeloRes(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
