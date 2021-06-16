package sitio;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class SitioWeb implements HomePagePublisher, Observer{

	
	private List<String> inmueblesConInteres;
	
	public SitioWeb(List <String> tipoDeInmuebles) {
		
		
		inmueblesConInteres= tipoDeInmuebles;
	}


	@Override
	public void publish(String string) {
		// TODO Auto-generated method stub
		System.out.println("string");
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	public List<String> getInmueblesConInteres() {
		return inmueblesConInteres;
	}

	public void setInmueblesConInteres(List<String> inmueblesConInteres) {
		this.inmueblesConInteres = inmueblesConInteres;
	}
	
	
}
