package Suscripciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import inmueble.Inmueble;

public class SitioWeb implements INotify, HomePagePublisher{

	
	private List<Inmueble> inmueblesDeInteres;
	
	public SitioWeb(List <Inmueble> tipoDeInmuebles) {
		
		
		inmueblesDeInteres= tipoDeInmuebles;
		
		for (Inmueble observable : tipoDeInmuebles) {
			
			observable.addObserver(this);
		}
	}


	@Override
	public void publish(String string) {
		// TODO Auto-generated method stub
		System.out.println(string);
		
		
	}



	public List<Inmueble> getInmueblesConInteres() {
		return inmueblesDeInteres;
	}


	@Override
	public void update(Inmueble inmueble, String evento) {
		// TODO Auto-generated method stub
		if (evento.equals("Baja de precio")) {
		this.publish("No te pierdas esta oferta: Un inmueble "+ inmueble.getTipoDeInmueble() + " a tan solo " + inmueble.getPrecioActual() + " pesos!.");
	}
	}


}


	
	

