package logica;
import logica.excepciones.JugadorExisteException;
import logica.excepciones.JugadorNoExisteException;
import logica.excepciones.PersistenciaException;

public interface IFachada {
	
	public void nuevoJugador(String nombre, int coorx, int coory) throws PersistenciaException, JugadorExisteException;
	public void actualizarPosicionJugador(String nombre, int coorx, int coory) throws PersistenciaException, JugadorNoExisteException;
}
