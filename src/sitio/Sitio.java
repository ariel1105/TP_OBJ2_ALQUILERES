package sitio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Categorias.Categoria;
import Suscripciones.SitioWeb;
import inmueble.Inmueble;
import perfiles.PerfilPropietario;
import perfiles.PerfilInmueble;
import perfiles.PerfilInquilino;
import reservas.Reserva;

import usuario.Usuario;

public class Sitio {
	
	/**
     * Esta clase sera la principal, se encargara de funciones importantes como registrar usuarios, inmuebles, categorias para puntuar,
     * tipos de inmuebles validos y crear perfiles para los usuarios sean inquilinos o propietarios y para los inmuebles.
     * 
     */
	private List <Usuario> usuariosRegistrados;
	private List <Inmueble> inmueblesPublicados;
	private List <Categoria> categoriasParaInmueble;
	private List <Categoria> categoriasParaPropietario;
	private List <Categoria> categoriasParaInquilino;
	private List <String> tipoDeInmuebles;
	private List <String> servicios;
	
	
	public Sitio() {
		this.usuariosRegistrados = new ArrayList<Usuario>();
		this.inmueblesPublicados= new ArrayList<Inmueble>();	
		this.categoriasParaInmueble= new ArrayList <Categoria>();
		this.categoriasParaPropietario= new ArrayList <Categoria>();
		this.categoriasParaInquilino= new ArrayList <Categoria>();
		this.tipoDeInmuebles= new ArrayList <String>();
		this.servicios= new ArrayList<String>();
	}
	/**
     * Constructor para la clase, no se le agrega ningun parametro. Solo se instancian las listas como arrays
     * 
     */


	public List<Usuario> obtenerUsuariosRegistrados() {
		// TODO Auto-generated method stub
		return usuariosRegistrados;
	}
	/**
     * Método que retorna todos los usuarios registrados en el sitio
     */  

	public void registrarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		if(!this.elUsuarioEstaRegistrado(usuario)) {
			this.crearPerfilInquilino(usuario);
			usuariosRegistrados.add(usuario);
		}
	}
	/**
     * Método que registra un usuario en el sitio. Primero pregunta si el usuario no esta registrado. Luego, si no lo estaba
     * le crea el perfil calificable al usuario y agrega el usuario a los registrados
     */  
	
	public void crearPerfilInquilino(Usuario usuario) {
		PerfilInquilino perfil = new PerfilInquilino(this.categoriasParaInquilino, usuario);
		usuario.setPerfilInquilino(perfil);
	}
	/**
     * Método que crea el perfil calificable en el usuario
     */  
	public boolean elUsuarioEstaRegistrado(Usuario usuario) {
		// TODO Auto-generated method stub
		return usuariosRegistrados.contains(usuario);
	}
	/**
     * Método que retorna un booleano preguntando si el usuario esta registrado dentro del sitio
     */  


	public void publicar(Inmueble inmueble, Usuario propietario) {
		if (this.esInmuebleValido(inmueble)) {
			PerfilPropietario perfil = new PerfilPropietario(categoriasParaPropietario, propietario);
			propietario.setPerfilPropietario(perfil);
			this.crearPerfilInmueble(inmueble, perfil);
			this.inmueblesPublicados.add(inmueble);
			propietario.agregarInmueble(inmueble);
		}
	}
	/**
     * Método que publica un inmueble dentro del sitio
     * Primero pregunta si el inmueble es valido para publicarse
     * Luego, crea el perfil calificable al propietario, al mismo inmueble
     *  y agrega el inmueble dentro de la lista de inmuebles y al propietario
     */  
	public boolean esInmuebleValido(Inmueble inmueble) {
		// TODO Auto-generated method stub
		return (this.tipoDeInmuebles.contains(inmueble.getTipoDeInmueble()) && this.servicios.containsAll(inmueble.getServicios()));
	}
	/**
     * Método que retorna si el inmueble que se le pasa como parametro es válido.
     * Primero queremos saber si el el tipo de inmueble esta dentro de los inmuebles validos. 
     * Mismo caso con los servicios del inmueble, preguntamos si todos los servicios del inmueble estan dentro del sitio.
     */  



	public void crearPerfilInmueble(Inmueble inmueble, PerfilPropietario perfil) {
		PerfilInmueble perfil2 = new PerfilInmueble(this.categoriasParaInmueble, inmueble, perfil);
		inmueble.setPerfilInmueble(perfil2);
		
	}
	/**
     * Método que crea el perfil calificable en el inmueble
     */  
	public void enviarMailDeConfirmacion(Reserva reserva) {
		
	}

	public void altaDeCategoriaInmueble(Categoria categoria) {
		// TODO Auto-generated method stub
		if (!categoriasParaInmueble.contains(categoria)) {
		this.categoriasParaInmueble.add(categoria);
		}
		
	}
	/**
     * Método que agrega una categoria de tipo inmueble, primero, pregunta si la categoria esta registrada.
     */  
	public void altaDeCategoriaPropietario(Categoria categoria) {
		// TODO Auto-generated method stub
		if (!categoriasParaPropietario.contains(categoria)) {
		this.categoriasParaPropietario.add(categoria);
		}
	}	/**
     * Método que agrega una categoria de tipo propietario, primero, pregunta si la categoria esta registrada.
     */  

	public void altaDeCategoriaInquilino(Categoria categoria) {
		// TODO Auto-generated method stub
		if (!categoriasParaInquilino.contains(categoria)) {
		this.categoriasParaInquilino.add(categoria);
		}
	}
	/**
     * Método que agrega una categoria de tipo inquilino, primero, pregunta si la categoria esta registrada.
     */  
	public void altaDeTipoInmueble(String tipo) {
		// TODO Auto-generated method stub
		if (!tipoDeInmuebles.contains(tipo)) {
		this.tipoDeInmuebles.add(tipo);
		}
	}
	/**
     * Método que agrega un tipo inmueble, primero, pregunta si el tipo de inmueble esta registrado.
     */  
	public void altaServicio(String servicio) {
		// TODO Auto-generated method stub
		if (!servicios.contains(servicio)) {
		this.servicios.add(servicio);
		}
	}
	/**
     * Método que agrega un servicio valido, primero, pregunta si el servicio esta registrado.
     */  

	public List<Inmueble> getInmueblesPublicados() {
		return inmueblesPublicados;
	}
	/**
     * Método que agrega un servicio valido, primero, pregunta si el servicio esta registrado.
     */  
	public List<Categoria> getCategoriasParaInmueble() {
		return categoriasParaInmueble;
	}
	/**
     * Método que retorna todas las categorias para inmueble
     */  
	public List<Categoria> getCategoriasParaPropietario() {
		return categoriasParaPropietario;
	}
	/**
     * Método que retorna todas las categorias para propietario
     */  
	public List<Categoria> getCategoriasParaInquilino() {
		return categoriasParaInquilino;
	}
	/**
     * Método que retorna todas las categorias para inquilino
     */  
	public List<String> getTipoDeInmuebles() {
		return tipoDeInmuebles;
	}
	/**
     * Método que retorna todos los tipos de inmuebles
     */  
	public List<String> getServicios() {
		return servicios;
	}
	/**
     * Método que retorna todas los servicios de inmueble
     */  
}
