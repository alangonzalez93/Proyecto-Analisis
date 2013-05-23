package com.unrc.app;

import com.unrc.app.models.Building;
import com.unrc.app.models.Locality;
import com.unrc.app.models.Owner;



public class ABMBuilding {

	public Boolean findInmueble(String street, String neighborhood){
		return (Building.first("b_street = ? and neighborhood = ?", street, neighborhood) != null);
	}
	
	public Boolean findOwner(String dni){
		return (Owner.first("owner_dni = ?", dni)!=null);
	}

	public int verifType(String type){
		if (type == "Casa" || type == "casa") {
			return 1;
		} if (type == "Campo" || type =="campo") {
			return 2;
		} if (type == "Quinta" || type == "quinta"){
			return 3;
		} if (type == "Departamento" || type == "departamento") {
			return 4;
		} if (type == "Oficina" || type == "oficina"){
			return 5;
		} if (type == "Cochera" || type == "cochera"){
			return 6;
		} else {
			return -1;
		}
	}
	public int verifCat(String cat){
		if (cat =="Alquiler" || cat == "alquiler") {
			return 1;
		} if (cat == "Venta" || cat == "venta") {
			return 2;
		} else {
			return -1;
		}
	}

	public void createLocality(String n){
		Locality l = Locality.create("name",n);
		l.saveIt();
	}

	
	public void altaBuilding(String description, int price, String categorie, String type, String neighborhood, String street, String locality, String dni) {
		if (findInmueble(street,neighborhood)) {
			System.out.println("El inmueble ya existe");
		} else {
			Building b = Building.create("price", price,"type",verifType(type),"neighborhood",neighborhood,"description",description,"b_street",street,"categorie",verifCat(categorie));
			b.saveIt();
			Locality l = Locality.first("name = ? ", locality);
			if (l == null) {
				Locality local = new Locality();
				local.set("name", locality);
				local.saveIt();
				local.add(b);
			} else {
				l.add(b);
			}
			Owner ow = Owner.first("owner_dni = ?", dni);
			if(ow != null){
				ow.add(b);
			}else{
				System.out.println("duenio inexistente");
			}
		}
	}

	public void bajaBuilding(String street, String neigh) {
			if (findInmueble(street,neigh)){
				Building.delete("b_street = ? and neighborhood = ?", street, neigh);
			}
			else{
				System.out.println("inmueble inexistente");
			}
	}
	
	public void modPrice(String street, String neigh, int newprice) {
		Building b = new Building();
		b = Building.first("b_street = ? and neighborhood = ?", street, neigh);
		b.set("price", newprice);
		b.saveIt();
	}
	
	public void modType(String street, String neigh, String newtype){
		Building b = Building.first("b_street = ? and neighborhood = ?", street, neigh);
		b.set("type", newtype);
		b.saveIt();
	}
	
	public void modDesc(String street, String neigh, String newdesc){
		Building b = Building.first("b_street = ? and neighborhood = ?", street, neigh);
		b.set("description", newdesc);
		b.saveIt();
	}
	 
	public void modOwner(String street, String neigh, String dninewowner){
		Building b = Building.first("b_street = ? and neighborhood = ?", street, neigh);
		Owner owner = Owner.first("owner_dni = ?", dninewowner);
		if(owner != null){
			owner.add(b);
		}
		else{
			System.out.println("duenio inexistente");
		}
	}
	
}