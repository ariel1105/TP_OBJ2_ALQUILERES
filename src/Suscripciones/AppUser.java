package Suscripciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import inmueble.Inmueble;

public class AppUser implements INotify, PopUpWindow{
	/**
     * Esta clase se encargara de avisarle a la aplicacion del usuario si un inmueble en el cual esta interesado se liberó.
     * Implementara 2 interfaces; Inotify y PopUpWindow
     */
	
	private List<Inmueble> inmueblesDeInteres;
	
	public AppUser(List <Inmueble> tipoDeInmuebles) {
		
		
		inmueblesDeInteres= tipoDeInmuebles;
		
		for (Inmueble observable : tipoDeInmuebles) {
			
			observable.addObserver(this);
		}
	}
	/**
     * Constructor para la Aplicacion del usuario. Se agregara a la lista "inmueblesDeInteres" los inmuebles de la lista que se le pasara como parametro
     * 
     */
		@Override
	public void popUp(String message, String color, int fontSize) {
		// TODO Auto-generated method stub
		System.out.println(message);
	}
		/**
	     * Método que imprime un mensaje avisando que un inmueble X se libero
	     */
		@Override
	public  List<Inmueble> getInmueblesConInteres() {
		// TODO Auto-generated method stub
		return inmueblesDeInteres;
	}
		/**
	     * Método que devuelve los inmuebles en los que el observador esta interesado
	     */

		@Override
	public void update(Inmueble inmueble,String evento) {
		// TODO Auto-generated method stub
		if (evento.equals("Cancelacion")){
		this.popUp("El inmueble  "+ inmueble.getTipoDeInmueble() + " que te interesa se ha liberado! Corre a reservarlo!.", "Red", 2);
		}
	}
		/**
	     * Método que llama a otro metodo pasandole un texto de alerta en caso de que el evento sea igual al del condicional
	     */






	
	

}
