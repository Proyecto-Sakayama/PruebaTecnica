package persistencia.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import logica.Jugador;
import logica.excepciones.JugadorExisteException;
import logica.excepciones.JugadorNoExisteException;
import logica.excepciones.PersistenciaException;
import logica.poolConexiones.IConexion;
import persistencia.consultas.Consultas;

public class DaoJugadorBD implements IDaoJugador{
	Consultas consul = null;

	public DaoJugadorBD() {	
		consul = new Consultas();
	}
	
	public void insert (Jugador j,IConexion con) throws PersistenciaException, JugadorExisteException{
		try {
			 Connection conn = con.getCon();
			 
			 PreparedStatement pst = conn.prepareStatement(consul.nuevoJugador());
			 
			 pst.setString(1, j.getNombre());
			 pst.setInt(2, j.getxCordenada());
			 pst.setInt(3, j.getyCordenada());
		 
			 pst.executeUpdate();
	 
		}catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());			
		}	
	}
	
	@Override
	public void update(Jugador j, IConexion con) throws PersistenciaException, JugadorNoExisteException {
		try {
			 Connection conn = con.getCon();
			 PreparedStatement pst = conn.prepareStatement(consul.actualizarJugador());
			 pst.setInt(1, j.getxCordenada());
			 pst.setInt(2, j.getyCordenada());
			 pst.setString(3, j.getNombre());
		 
			 pst.executeUpdate();
	 
		}catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());			
		}	
		
	}

	@Override
	public Jugador kesima(String nombre, IConexion con) throws PersistenciaException {
		Jugador jugador = null;
		int coordX, coordY;

		try {
			String query = consul.listarJugadores();
			Connection conn = con.getCon();
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, nombre);

			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				rs.next();
			}

			coordX = rs.getInt("X_coordenada");
			coordY = rs.getInt("Y_coordenada");

			jugador = new Jugador(nombre, coordX, coordY);

			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException("Error de conexion con persistencia");
		}
		
		return jugador;
	}

}
