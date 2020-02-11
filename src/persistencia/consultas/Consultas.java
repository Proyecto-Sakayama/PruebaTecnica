package persistencia.consultas;

public class Consultas {
	public String nuevoJugador()
	{
		String query = "INSERT INTO juego.posicion_jugador (nombre,X_coordenada,Y_coordenada) VALUES (?,?,?)";
		return query;
	}
	
	public String actualizarJugador()
	{
		String query = "UPDATE juego.posicion_jugador set X_coordenada = (?), Y_coordenada = (?) WHERE nombre = (?)";
		return query;
	}

	public String listarJugadores()
	{
		String query = "SELECT * from juego.posicion_jugador WHERE nombre = (?)";
		return query;
	}

}
