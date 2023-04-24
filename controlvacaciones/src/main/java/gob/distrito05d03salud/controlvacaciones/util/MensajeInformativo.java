package gob.distrito05d03salud.controlvacaciones.util;

public class MensajeInformativo {
    private String tipo;
    private String mensaje;

    
    public MensajeInformativo(String tipo, String mensaje) {
        this.tipo = tipo;
        this.mensaje = mensaje;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    
    
}
