package chemasmas.mx.uamchat.model;

public class Mensaje {
    public Long remitente;
    public Long desinatario;
    public String mensaje;

    public Long getRemitente() {
        return remitente;
    }

    public void setRemitente(Long remitente) {
        this.remitente = remitente;
    }

    public Long getDesinatario() {
        return desinatario;
    }

    public void setDesinatario(Long desinatario) {
        this.desinatario = desinatario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
