package com.unrc.app;

import com.unrc.app.models.Locality;

@SuppressWarnings("unused")
public class ObjectBuilding {

	//enum BuildingType {Campo, Quinta, Casa, Departamento, Oficina, Cochera}
	//enum Publication {Alquiler, Venta}
	
	//Atributos
	private int type; //Casa = 1, Campo = 2, Quinta = 3, Departamento = 4, Oficina = 5, Cochera = 6
	private String owner;
	private String locality;
	private String street;
	private String neighborhood;
	private String description;
	private float price;
	private int categorie; //Alquiler = 1, Venta = 2
	
//Getters & Setters
	// Get Tipo	
	public int getType() {
		return type;
	}
	// Set Tipo	
	public void setType(int t) {
		this.type = t;
	}
	// Get Owner
	public String getOwner() {
		return owner;
	}
	// Set Owner
	public void setOwner(String o) {
		this.owner = o;
	}
	// Get Locality
	public String getLocality() {
		return locality;
	}
	// Set Locality
	public void setLocality(String l) {
		this.locality = l;
	}
	// Get Street
	public String getStreet() {
		return street;
	}
	// Set Street
	public void setStreet(String s) {
		this.street = s;
	}
	// Get Neighborhood
	public String getNeighborhood() {
		return neighborhood;
	}
	// Set Neighborhood
	public void setNeighborhood(String n) {
		this.neighborhood = n;
	}
	// Get Description
	public String getDescription() {
		return description;
	}
	// Set Description
	public void setDescription(String desc) {
		this.description = desc;
	}
	// Get Price
	public float getPrice() {
		return price;
	}
	// Set Price
	public void setPrice(float p) {
		this.price = p;
	}
	// Get Publication
	public int getCategorie() {
		return categorie;
	}
	// Set Publication
	public void setCategorie(int p) {
		this.categorie = p;
	}
	
}
