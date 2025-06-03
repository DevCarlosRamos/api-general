package uk.carlosramos.api.usuario.usuarioConCorreo.modelo;

public class UsuarioConCorreoModeloRes {

    private String id;
    private String nombre;
    private String password;
    private String correo;

    public UsuarioConCorreoModeloRes() {
    }

    public UsuarioConCorreoModeloRes(String nombre, String password, String id, String correo) {
        this.nombre = nombre;
        this.password = password;
        this.id = id;
        this.correo = correo;
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
