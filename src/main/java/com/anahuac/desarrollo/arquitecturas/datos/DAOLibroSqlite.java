package com.anahuac.desarrollo.arquitecturas.datos;

import java.util.ArrayList;
import java.util.List;
import com.anahuac.desarrollo.arquitecturas.entidades.Libros;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DAOLibroSqlite implements DAOLibro{
	
	public DAOLibroSqlite() {}
	
	public Connection getConnection() { //crear conexcion con la base de datos usando un api de la app donde creamos la base de datos (sqlite) usando el api "jdbc" de este mismo
		Connection c = null;
		try {
			String url = "jdbc:sqlite:/Users/jhonatanalexis/Documents/ingsw2/ingsw/arquitecturas/database_libros.db"; //el path es la base de datos
			c = DriverManager.getConnection(url); //creando la conexion
		}
		catch(Exception e) {
			System.out.println("Exception at connection: " + e);
		}
		return c;
	}
	
	public Libros createLibro(String _nombre, String _autor, String _isbn) {
		Connection c = getConnection();
		int id = -1;
		Libros libro = null;
		try {
			PreparedStatement ps; //el preparedStatement sirve para compilar la consulta sql de una manera mas eficiente, se pone ? en lugar de poner los valores de una para luego ponerlos con el set que tiene el preparedstatement
			ps = c.prepareStatement("INSERT INTO libros(nombre, autor, isbn) VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS); // va a regresar la llave autogenerada (en este caso el id)
			ps.setString(1, _nombre);
			ps.setString(2, _autor);
			ps.setString(3, _isbn);
			//ejecutar la sentencia sql
			int rows = ps.executeUpdate();//regresa el numero de filas afectadas
			if(rows >= 1) { //o sea si el numero de filas afectadas es 1 o mas significa que se inserto un dato con la sentencia sql (independientemente de cuantos datos se insertaron pues se esta validando si se inseerto una o mas)
				ResultSet rs = ps.getGeneratedKeys(); //se regresa la llave autogenerada (id) el result set es como una tabla que se genera y tiene un puntero este puntero se va moviendo con el next() por fila y fila porque ahi se insertan los datos (la tabla es como la tabla de la base de datos)
				if(rs.next()) { //result set tienes valores? dame el siguiente valor, esta fuera de la lista, cuando se da el next se va a la siguiente de la lista
					id = rs.getInt(1); //asi como el ps tiene los set de distintos de datos, el rs tiene los get con distintos tipos de datos, el 1 es porque se esta haceidno referencia a la columna donde esta el id y este es tipo enetero por eso int 
				}
				libro = new Libros(id, _nombre, _autor, _isbn);
			}
			c.close();
			ps.close();
		}
		catch(Exception e) {
			System.out.println("Exception at connection: " + e);
		}
		return libro;
	}
	
	public Libros buscarLibro(int _id) {
		Connection c = getConnection();
		Libros libro = null;
		try {
			PreparedStatement ps;
			ps = c.prepareStatement("SELECT * FROM libros WHERE id = ?");
			ps.setInt(1, _id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) { //se mueve a la fila que contiene la condicion sql
				libro = new Libros(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
			c.close();
			ps.close();
		}
		catch(Exception e) {
			System.out.println("Exceptiron at connection: " + e);
		}
		return libro;
	}
	
	public boolean uptadeLibro(Libros _libro) {
		Connection c = getConnection();
		boolean seActualizo = false;
		try {
			PreparedStatement ps;
			ps = c.prepareStatement("UPDATE libros SET nombre = ?, autor = ?, isbn = ? WHERE id = ?");
			ps.setString(1, _libro.getNombre());
			ps.setString(2, _libro.getAutor());
			ps.setString(3, _libro.getIsbn());
			ps.setInt(4, _libro.getId());
			int rows = ps.executeUpdate();
			if(rows >= 1) {
				seActualizo = true;
			}
			c.close();
			ps.close();
		}
		catch(Exception e) {
			System.out.println("Exception at connection: " + e);
		}
		return seActualizo;
	}
	
	public void deleteLibro(int _id) {
		Connection c = getConnection();
		try {
			PreparedStatement ps;
			ps = c.prepareStatement("DELETE from libros WHERE id = ?");
			ps.setInt(1, _id);
			int rows = ps.executeUpdate();
			if(rows >= 1) {
				System.out.println("El libro ha sido borrado");
			}
			c.close();
			ps.close();
		}
		catch(Exception e) {
			System.out.println("Exception at connection: " + e);
		}
	}
	
	public List<Libros> readAllLibros(){
		Connection c = getConnection();
		List<Libros> libro = new ArrayList<>();
		Libros libros = null;
		try {
			PreparedStatement ps;
			ps = c.prepareStatement("SELECT * FROM libros");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				libros = new Libros(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				libro.add(libros);;
			}
			c.close();
			ps.close();
		}
		catch(Exception e) {
			System.out.println("Exception at connection: " + e);
		}
		return libro;
	}
	
	public Libros buscarLibroIsbn(String _isbn) {
		Connection c = getConnection();
		Libros libro = null;
		try {
			PreparedStatement ps;
			ps = c.prepareStatement("SELECT * FROM libros WHERE isbn = ?");
			ps.setString(1, _isbn);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				libro = new Libros(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
			c.close();
			ps.close();
		}catch(Exception e) {
			System.out.println("Exception at connection: " + e);
		}
		return libro;
	}
}
