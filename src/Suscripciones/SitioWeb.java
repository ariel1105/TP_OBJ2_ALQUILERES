package Suscripciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import inmueble.Inmueble;

public class SitioWeb implements INotify, HomePagePublisher{
	/**
     * Esta clase se encargara de avisarle al Sitio web si un inmueble en el cual esta interesado se le bajo el precio.
     * Implementara 2 interfaces; Inotify y HomePagePublisher
     */
	
	
	private List<Inmueble> inmueblesDeInteres;
	
	public SitioWeb(List <Inmueble> tipoDeInmuebles) {
		
		
		inmueblesDeInteres= tipoDeInmuebles;
		
		for (Inmueble observable : tipoDeInmuebles) {
			
			observable.addObserver(this);
		}
	}
	/**
     * Constructor para la el notificaciones. Se agregara a la lista "inmueblesDeInteres" los inmuebles de la lista que se le pasara como parametro
     * 
     */

	@Override
	public void publish(String string) {
		// TODO Auto-generated method stub
		System.out.println(string);
		
		
	}
	/**
     * Método que imprime un mensaje avisando que un inmueble X se le ha bajado el precio
     */


	public List<Inmueble> getInmueblesConInteres() {
		return inmueblesDeInteres;
	}
	/**
     * Método que devuelve los inmuebles en los que el observador esta interesado
     */

	@Override
	public void update(Inmueble inmueble, String evento) {
		// TODO Auto-generated method stub
		if (evento.equals("Baja de precio")) {
		this.publish("No te pierdas esta oferta: Un inmueble "+ inmueble.getTipoDeInmueble() + " a tan solo " + inmueble.getPrecioActual() + " pesos!.");
	}
	}
	/**
     * Método que llama a otro metodo pasandole un texto de alerta en caso de que el evento sea igual al del condicional
     */


}


	
	

