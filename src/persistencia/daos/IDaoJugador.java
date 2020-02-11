package persistencia.daos;

import logica.Jugador;
import logica.excepciones.JugadorExisteException;
import logica.excepciones.JugadorNoExisteException;
import logica.excepciones.PersistenciaException;
import logica.poolConexiones.IConexion;

public interface IDaoJugador {
	public void insert(Jugador j,IConexion con) throws PersistenciaException, JugadorExisteException;
	public void update(Jugador j,IConexion con) throws PersistenciaException, JugadorNoExisteException;
	public Jugador kesima(String nombre,IConexion con) throws PersistenciaException;
}
