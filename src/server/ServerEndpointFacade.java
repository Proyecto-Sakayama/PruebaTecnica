package server;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;



import logica.FachadaImp;
import logica.IFachada;
import logica.excepciones.JugadorExisteException;
import logica.excepciones.PersistenciaException;

@ServerEndpoint("/serverendpointdemo")
public class ServerEndpointFacade {
	
	IFachada ifachada = null;
	
	private static Set<Session> sesionesClientes = Collections.synchronizedSet(new HashSet<Session>());
	
	public ServerEndpointFacade(){
		try {
			 ifachada = new FachadaImp();
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
	}
	
	

	@OnOpen
	public void handleOpen(Session session){
		System.out.println("Cliente conectado");
		sesionesClientes.add(session);
	}
	
	@OnMessage 
	public void onMessage(Session session, String message) {
//		String replyMessage=null ;
		try {  
	    	
			System.out.println("Recibido del cliente;"+ message);
			
			guardarPosicionJugador(message);
//	      for (Session s : session.getOpenSessions()) {
			for (Session s : sesionesClientes){
	        if (s.isOpen()) {

	          s.getBasicRemote().sendText(message);
	    		 String replyMessage = "echo: " + message;
	    		System.out.println("Enviado al cliente"+ replyMessage);
	        }
	      }
	    } catch (IOException ex) {
	    	ex.printStackTrace();
	  } 
//	    return replyMessage;
	}
	

/**
 * llama a la fachada para guardar el pesquero
 * @param message
 */
	private void guardarPosicionJugador(String message) {
		
		String ArrayCord[]=message.split("-");		
	
		
		int coorx = Integer.parseInt(ArrayCord[0]);
				
		int coory = Integer.parseInt(ArrayCord[1]);
		
		String nombre = ArrayCord[2];
		
		try {
			ifachada.nuevoJugador(nombre, coorx, coory);
		} catch (JugadorExisteException e) {
			e.printStackTrace();
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
	}
	
	@OnClose
	public void handleClose(Session session){
		sesionesClientes.remove(session);
		System.out.println("Cliente Desconectado");
	}
	
	@OnError
	public void handleError(Throwable t){
		t.printStackTrace();
		
	}
	
}
