package com.anahuac.desarrollo.arquitecturas.datos;
import com.anahuac.desarrollo.arquitecturas.entidades.Libros;
import java.util.List;

public interface DAOLibro {
	public Libros createLibro(String _nombre, String _autor, String _isbn); //no se pone el id porque ya lo genera la base de datos automaticamente
	public Libros buscarLibro(int _id); //busqueda y borrar es id
	public boolean uptadeLibro(Libros libro);
	public void deleteLibro(int _id);
	public List<Libros> readAllLibros();
	public Libros buscarLibroIsbn(String _isbn);
}
