package uk.carlosramos.api.errores.modelo;

public class RespuestaModeloRes {

    private String mensaje;

    public RespuestaModeloRes() {
    }

    public RespuestaModeloRes(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
