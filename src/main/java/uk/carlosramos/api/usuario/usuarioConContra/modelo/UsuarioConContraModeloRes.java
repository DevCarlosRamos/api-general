package uk.carlosramos.api.usuario.usuarioConContra.modelo;

public class UsuarioConContraModeloRes {

    private String id;
    private String nombre;
    private String password;

    public UsuarioConContraModeloRes() {
    }

    public UsuarioConContraModeloRes(String nombre, String password, String id) {
        this.nombre = nombre;
        this.password = password;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
