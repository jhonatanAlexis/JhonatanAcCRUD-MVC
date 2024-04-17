package com.anahuac.desarrollo.arquitecturas.logica;

import java.util.List;

import com.anahuac.desarrollo.arquitecturas.datos.DAOLibro;
import com.anahuac.desarrollo.arquitecturas.datos.DAOLibroSqlite;
import com.anahuac.desarrollo.arquitecturas.entidades.Libros;

public class ControllerLibro { 
	private Servicios s;//tiene que ir por capas, esta es la capa logica (controller) y la siguiente es la interfaz dao
	
	public ControllerLibro(Servicios _s) {
		this.s = _s;
	}
	
	public Libros crearLibro(String _nombre, String _autor, String _isbn) {
		return this.s.createLibro(_nombre, _autor, _isbn);
	}
	
	public boolean actualizarLibro(Libros _libro) {
		 return this.s.update(_libro);
	}
	
	public Libros buscarLibroo(int _id) {
		return this.s.findById(_id);
	}
	
	public boolean eliminarLibro(int _id) {
		return this.s.delete(_id);
	}
	
	public List<Libros> obtenerLibros(){
		List<Libros> l = this.s.readThemAll();
		return l;
	}
}
