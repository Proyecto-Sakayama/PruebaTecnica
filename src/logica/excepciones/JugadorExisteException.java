package logica.excepciones;

public class JugadorExisteException extends Exception{
	private static final long serialVersionUID = 1L;
	
	String mensaje = "El jugador ya existe";

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
