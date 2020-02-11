package logica.valueObjects;

import java.io.Serializable;

public class JugadorVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public JugadorVO(String nombre, int xCoordenada, int yCoordenada)
	{
		this.nombre=nombre;
		this.xCordenada = xCoordenada;
		this.yCordenada = yCordenada;	
	}
	
	private String nombre;
	private int xCordenada;
	private int yCordenada;
	
	public String getNombre() {
		return nombre;
	}
	
	public int getxCordenada() {
		return xCordenada;
	}

	public int getyCordenada() {
		return yCordenada;
	}

	
	
}