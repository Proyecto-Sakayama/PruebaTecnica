package persistencia.fabricas;

import persistencia.daos.IDaoJugador;

public interface FabricaAbstracta {
	public IDaoJugador crearIDAOJugador();
}
