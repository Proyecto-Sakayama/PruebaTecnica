package logica;

import java.io.Serializable;

import logica.excepciones.PersistenciaException;

public class Jugador implements Serializable{
	
	public Jugador() {
		super();
	}
	
	private static final long serialVersionUID = 1L;
		
	private String nombre;	
	private int id,xCordenada,yCordenada;
	
	
	
	public Jugador(String nombre, int xCordenada, int yCordenada) throws PersistenciaException {
		this.nombre=nombre;
		this.xCordenada = xCordenada;
		this.yCordenada = yCordenada;
	}	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	public int getxCordenada() {
		return xCordenada;
	}

	public void setxCordenada(int xCordenada) {
		this.xCordenada = xCordenada;
	}

	public int getyCordenada() {
		return yCordenada;
	}

	public void setyCordenada(int yCordenada) {
		this.yCordenada = yCordenada;
	}

}