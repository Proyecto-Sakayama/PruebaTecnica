package mainBD;


import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;


public class MainCrearBase {

	public static void main(String[] args) {
		
		try {
			
			
			Properties p = new Properties();
			p.load(new FileInputStream("src/conf/config.properties"));
			
			String url = p.getProperty("url");
			String usr = p.getProperty("usr");
			String pwd = p.getProperty("pwd");
			String driver = p.getProperty("driver");
			
			Class.forName(driver);
			
			Connection con = DriverManager.getConnection(url, usr, pwd);
			
			String DROPDB = "DROP DATABASE IF EXISTS juego";			
			Statement stmt = con.createStatement();
			stmt.executeUpdate(DROPDB);
		
			String createDB="CREATE DATABASE juego";			
			stmt.executeUpdate(createDB);
			String useDB="USE juego";			
			stmt.executeUpdate(useDB);
			
			String create="CREATE TABLE posicion_jugador (nombre VARCHAR(45) primary key,"
					+ "X_coordenada int(11),"
					+ "Y_coordenada int(11));";
			stmt.executeUpdate(create);
			
			
			System.out.println("Base creada correctamente");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
