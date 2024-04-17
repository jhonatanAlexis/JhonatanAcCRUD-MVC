package com.anahuac.desarrollo.arquitecturas.entidades;

public class Libros {
	private int id;
	private String nombre, autor, isbn;
	
	public Libros(String _nombre, String _autor, String _isbn) {
		//super();
		this.nombre = _nombre;
		this.autor = _autor;
		this.isbn = _isbn;
	}
	
	public Libros(int _id, String _nombre, String _autor, String _isbn) {
		//super();
		this.id = _id;
		this.nombre = _nombre;
		this.autor = _autor;
		this.isbn = _isbn;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public String getAutor(){
		return this.autor;
	}
	
	public String getIsbn(){
		return this.isbn;
	}
	
	public int getId(){
		return this.id;
	}
	
	public void setNombre(String _nombre){
		this.nombre = _nombre;
	}
	
	public void setAutor(String _autor){
		this.autor = _autor;
	}
	
	public void setIsbn(String _isbn){
		this.isbn = _isbn;
	}
	
	public void setId(int _id){
		this.id = _id;
	}

	public String toString() {
		return "Libros [id=" + id + ", nombre=" + nombre + ", autor=" + autor + ", isbn=" + isbn + "]\n";
	}
}
