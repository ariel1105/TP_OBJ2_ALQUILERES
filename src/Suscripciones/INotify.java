	package Suscripciones;

import java.util.List;

import inmueble.Inmueble;

public interface INotify  {

	
	List<Inmueble> getInmueblesConInteres();


	void update(Inmueble inmueble, String evento);
}
