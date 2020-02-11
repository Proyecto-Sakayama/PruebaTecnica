package logica.poolConexiones;

import java.sql.Connection;

public class Conexion implements IConexion{
	Connection con;
	
	public Conexion(Connection connection) {
		this.con = connection;
	}
	
	public Connection getConnection() {
		return con;
	}

	@Override
	public Connection getCon() {
		// TODO Auto-generated method stub
		return null;
	}
}
