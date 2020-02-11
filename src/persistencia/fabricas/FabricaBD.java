package persistencia.fabricas;

import persistencia.daos.DaoJugadorBD;
import persistencia.daos.IDaoJugador;

public class FabricaBD implements FabricaAbstracta{
	@Override
	public IDaoJugador crearIDAOJugador() {
		return new DaoJugadorBD();
	}
	
}
