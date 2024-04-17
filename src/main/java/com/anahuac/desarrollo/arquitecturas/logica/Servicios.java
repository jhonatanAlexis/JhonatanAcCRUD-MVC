package com.anahuac.desarrollo.arquitecturas.logica;

import java.util.List;

import com.anahuac.desarrollo.arquitecturas.datos.DAOLibroSqlite;
import com.anahuac.desarrollo.arquitecturas.entidades.Libros;

public class Servicios { //debe tener la logica de negocio o sea las validaciones y todo eso
	private DAOLibroSqlite dao; //tiene que ir por capas, esta es la capa logica (servicios) y la siguiente es la capa logica (controller)
	
	public Servicios(DAOLibroSqlite _dao) {
		this.dao = _dao;
	}
	
	//no queremos libros con isbn repetidos
	public Libros createLibro(String _nombre, String _autor, String _isbn) {
		Libros libro = null;
		if(this.dao.buscarLibroIsbn(_isbn) != null) {
			System.out.println("Ya existe un isbn asi");
		}else {
			libro = dao.createLibro(_nombre, _autor, _isbn);
		}
		return libro; 
	}
	
	public boolean update(Libros _libro) {
		 return dao.uptadeLibro(_libro);
	}
	
	//se valida que exista un usuario con ese id
	public Libros findById(int _id) {
		Libros libros = null;
		if(this.dao.buscarLibro(_id) != null) {
			libros = this.dao.buscarLibro(_id); 
		}
		return libros;
	}
	
	//verificar que haya un usuario con ese id
	public boolean delete(int _id) {
		boolean seBorro = false;
		if(this.dao.buscarLibro(_id) != null) {
			this.dao.deleteLibro(_id);
			seBorro = true;
		}
		return seBorro;
	}
	
	public List<Libros> readThemAll(){
		List<Libros> l = dao.readAllLibros();
		return l;
	}
}
