package sitio;

import java.util.ArrayList;
import java.util.List;

public class AppUser implements PopUpWindow{
	
	private List<String> inmueblesDeInteres;
	
	public AppUser() {
		
		inmueblesDeInteres= new ArrayList<String>();
		
		
	}
	
	

	public  List<String> getInmueblesDeInteres() {
		// TODO Auto-generated method stub
		return inmueblesDeInteres;
	}

	@Override
	public void popUp(String message, String color, int fontSize) {
		// TODO Auto-generated method stub
		
	}
	
	

}
