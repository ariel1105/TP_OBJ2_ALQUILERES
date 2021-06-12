package inmueble;

public class Hora {
	private int hora;
	private int minuto;
	
	public Hora(int hora, int minuto) {
		this.hora = hora;
		this.minuto = minuto;
	}

	public int getHora(){
		return hora;
	}
	
	public int getMinuto() {
		return minuto;
	}
}