package inmueble;

public class FormaDePago {
	/**
     * Esta clase es la forma de pago que tienen los inmuebles.
     * Solamente de ella se puede saber el nombre de la forma de pago
     * 
     */
	
	private String formaDePago;

	public FormaDePago(String formaDePago) {
		
		this.formaDePago= formaDePago;
	}
	/**
     * Constructor para la clase. 
     * Se le pasa como parametro un string que sera el nombre de la forma de pago
     * 
     */
	public String getFormaDePago() {
		return formaDePago;
	}
	/**
     * Retorna un string con la forma de pago
     * 
     */
}
