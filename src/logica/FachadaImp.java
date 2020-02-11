package logica;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.server.UnicastRemoteObject;
import java.util.Properties;

import logica.excepciones.JugadorExisteException;
import logica.excepciones.JugadorNoExisteException;
import logica.excepciones.PersistenciaException;
import logica.poolConexiones.IConexion;
import logica.poolConexiones.IPoolConexiones;
import persistencia.daos.IDaoJugador;
import persistencia.fabricas.FabricaAbstracta;

public class FachadaImp  implements IFachada{
	
	
	/* cargo el nombre de la clase PoolConexiones desde un archivo de configuración */
	private String poolConcreto = null ;	
	private String nomFab = null;			
	private IPoolConexiones pool = null ;	
	private FabricaAbstracta fabrica = null;	
	private IDaoJugador  diccioDaoJugador= null;	

	private void loadProperties(){
		
		Properties prop = new Properties();
		InputStream conectionProp = null;
		try {
			conectionProp = new FileInputStream("src/conf/config.properties");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			prop.load(conectionProp);
		} catch (IOException e) {
			e.printStackTrace();
		}				
		
		//cargo variable para el tipo de pool que se va a usar
		poolConcreto=prop.getProperty("poolConcreto");
		nomFab=prop.getProperty("nomFab");
	}
	
	public FachadaImp() throws  PersistenciaException{
		loadProperties();
		//poolConcreto="logica.poolConexiones.PoolConexionesBD";
		//nomFab="persistencia.fabricas.FabricaBD";
		
		try {
			fabrica =(FabricaAbstracta) Class.forName(nomFab).newInstance();	
			diccioDaoJugador = fabrica.crearIDAOJugador();
			pool= (IPoolConexiones) Class.forName(poolConcreto).newInstance();
		} catch (InstantiationException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new PersistenciaException(e.getMessage());
		}
	}
	

	@Override
	public void nuevoJugador(String nombre, int coorx, int coory) throws JugadorExisteException,PersistenciaException{
		IConexion con = null;
		try {
			//llamo al requerimiento		 
			con = pool.obtenerConexion(true); 	
			Jugador j = new Jugador(nombre, coorx, coory);
			diccioDaoJugador.insert(j, con);
			//Libero la conexion de forma exitosa
			pool.liberarConexion(con, true);	
		} catch (PersistenciaException e ) {
			//Libero la conexion pero no error en la transaccion
			pool.liberarConexion(con, false);			
			throw new PersistenciaException(e.getMessage());
		} catch (JugadorExisteException e ) {
			//Libero la conexion pero no error en la transaccion
			pool.liberarConexion(con, false);			
			throw new JugadorExisteException();
		}

	}

	@Override
	public void actualizarPosicionJugador(String nombre, int coorx, int coory) throws PersistenciaException, JugadorNoExisteException {
		IConexion con = null;
		try {
			//llamo al requerimiento		 
			con = pool.obtenerConexion(true); 	
			Jugador j = new Jugador(nombre, coorx, coory);
			diccioDaoJugador.update(j, con);
			//Libero la conexion de forma exitosa
			pool.liberarConexion(con, true);	
		} catch (PersistenciaException e ) {
			//Libero la conexion pero no error en la transaccion
			pool.liberarConexion(con, false);			
			throw new PersistenciaException(e.getMessage());
		} catch (JugadorNoExisteException e ) {
			//Libero la conexion pero no error en la transaccion
			pool.liberarConexion(con, false);			
			throw new JugadorNoExisteException();
		}
	}

	
}
