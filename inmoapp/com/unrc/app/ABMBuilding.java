package com.unrc.app;

import com.unrc.app.models.Building;
import com.unrc.app.models.Locality;
import com.unrc.app.models.Owner;

import java.util.Scanner;
public class ABMBuilding {

	private Scanner sc;

	public void AltaBuilding(float price, String type, String description, String locality, String street, String owner, String neighborhood, String categorie) {
		Building building = Building.first("locality = ?","street = ?","neighborhood = ?", locality, street, neighborhood); //Busco el edificio por direccion
		if (building == null) { // Si el edificio no existe lo creo
			Building building2 = new Building();
			building2.set("price","description","street","neighborhood",price,description,street,neighborhood); //Seteo el precio
			//Comienzo a setear tipo
			Boolean a = true;
			sc = new Scanner(System.in);
			while (a) {
				if (type == "Casa" || type == "casa") {
					building2.set("type", 1);
					a = false;
				} if (type == "Quinta" || type == "quinta") {
					building2.set("type",2);
					a = false;
				} if (type == "Campo" || type == "campo") {
					building2.set("type",3);
					a = false;
				} if (type == "Departamento" || type == "departamento") {
					building2.set("type", 4);
					a = false;
				} if (type == "Oficina" || type == "oficina") {
					building2.set("type", 5);
					a = false;
				} if (type == "Cochera" || type == "cochera") {
					building2.set("type", 6);
					a = false;
				} else { 
					System.out.println("Tipo de inmueble, no válido");
					System.out.println("Ingrese un tipo valido: ");
					System.out.println("Casa, Quinta, Campo, Departamento, Oficina, Cochera");
					type = sc.nextLine();	
				}
			}
			//Seteo localidad
			Locality l = Locality.first("name = ?", locality);
			if (l == null) { //Si la localidad no existe la creo
				Locality local = new Locality();
				local.set("name", locality);
				local.saveIt();
				local.add(building2);
				building2.set("locality",locality);
			} else { //Si existe agrego la relación
				l.add(building2);
				building2.set("locality",locality);
			}
			Owner o = Owner.first("owner_name = ?", owner); //Busco el dueño
			if (o == null) { //Si no existe lo creo con todos sus datos, lo guardo y relaciono
				Owner newOwn = new Owner();
				newOwn.set("owner_name",owner);
				System.out.println("Dueño inexistente - Creando nuevo dueño");
				System.out.println("Ingrese el barrio del nuevo dueño");
				String barrio = sc.nextLine();
				newOwn.set("owner_neighborhood",barrio);
				System.out.println("Ingrese la calle del dueño");
				String calle = sc.nextLine();
				newOwn.set("owner_street",calle);
				System.out.println("Ingrese el mail del dueño");
				String mail = sc.nextLine();
				newOwn.set("owner_mail", mail);
				newOwn.saveIt();
				newOwn.add(building2);
				building2.set("owner",owner);
			} else { //Si existe lo relaciono
				o.add(building2);
				building2.set("owner",owner);
			}
			Boolean b = true;
			while (b) {
				if (categorie=="Alquiler" || categorie =="alquiler"){
					building2.set("categorie",1);
					b = false;
				} if (categorie == "Venta" || categorie == "venta"){
					building2.set("categorie",2);
					b = false;
				} else {
					System.out.println("Categoria incorrecta");
					System.out.println("Ingrese una categoría valida");
					System.out.println("Alquiler o Venta");
					categorie = sc.nextLine();
				}
			}
			building2.saveIt();
		}
	}
	
	public void BajaBuilding(String street, String locality, String neighborhood){
		Building building = Building.first("locality = ?","street = ?","neighborhood = ?", locality, street, neighborhood); //Busco el edificio por direccion
		if (building == null) {
			System.out.println("No existe el edificio");
		} else {
			Locality local = Locality.first("name = ?", locality); // Busco la localidad correspondiente al inmueble
			local.remove(building);
			Building.delete("street = ?", "locality = ?", "neighborhood =?", street,locality,neighborhood);
		}	
	}
		
	public void ModBuilding(float price, String type, String description, String locality, String street, String owner, String neighborhood, String categorie) {
		Building b = Building.first("locality = ?","street = ?","neighborhood = ?", locality, street, neighborhood);
		if (b == null) {
			System.out.println("No existe el edificio");
		} else {
			Building.update("price = ?, description = ?","locality = ?, street = ?, neighborhood = ?", price, description, locality, street, neighborhood);
			Boolean a = true;
			sc = new Scanner(System.in);
			while (a) {
				if (type == "Casa" || type == "casa") {
					b.set("type", 1);
					a = false;
				} if (type == "Quinta" || type == "quinta") {
					b.set("type",2);
					a = false;
				} if (type == "Campo" || type == "campo") {
					b.set("type",3);
					a = false;
				} if (type == "Departamento" || type == "departamento") {
					b.set("type", 4);
					a = false;
				} if (type == "Oficina" || type == "oficina") {
					b.set("type", 5);
					a = false;
				} if (type == "Cochera" || type == "cochera") {
					b.set("type", 6);
					a = false;
				} else { 
					System.out.println("Tipo de inmueble, no válido");
					System.out.println("Ingrese un tipo valido: ");
					System.out.println("Casa, Quinta, Campo, Departamento, Oficina, Cochera");
					type = sc.nextLine();	
				}
			}
			Boolean c = true;
			while (c) {
				if (categorie=="Alquiler" || categorie =="alquiler"){
					b.set("categorie",1);
					c = false;
				} if (categorie == "Venta" || categorie == "venta"){
					b.set("categorie",2);
					c = false;
				} else {
					System.out.println("Categoria incorrecta");
					System.out.println("Ingrese una categoría valida");
					System.out.println("Alquiler o Venta");
					categorie = sc.nextLine();
				}
			}
		b.saveIt();
		}
	}
	
	public void modBuilding(float price, String type, String description, String locality, String street, String owner, String neighborhood, String categorie) {
		Building b = Building.first("locality = ?","street = ?","neighborhood = ?", locality, street, neighborhood);
		if (b == null) {
			System.out.println("No existe el edificio");
		} else {
			b.set("price","description", price, description);
			Boolean a = true;
			sc = new Scanner(System.in);
			while (a) {
				if (type == "Casa" || type == "casa") {
					b.set("type", 1);
					a = false;
				} if (type == "Quinta" || type == "quinta") {
					b.set("type",2);
					a = false;
				} if (type == "Campo" || type == "campo") {
					b.set("type",3);
					a = false;
				} if (type == "Departamento" || type == "departamento") {
					b.set("type", 4);
					a = false;
				} if (type == "Oficina" || type == "oficina") {
					b.set("type", 5);
					a = false;
				} if (type == "Cochera" || type == "cochera") {
					b.set("type", 6);
					a = false;
				} else { 
					System.out.println("Tipo de inmueble, no válido");
					System.out.println("Ingrese un tipo valido: ");
					System.out.println("Casa, Quinta, Campo, Departamento, Oficina, Cochera");
					type = sc.nextLine();	
				}
			}
			Boolean c = true;
			while (c) {
				if (categorie=="Alquiler" || categorie =="alquiler"){
					b.set("categorie",1);
					c = false;
				} if (categorie == "Venta" || categorie == "venta"){
					b.set("categorie",2);
					c = false;
				} else {
					System.out.println("Categoria incorrecta");
					System.out.println("Ingrese una categoría valida");
					System.out.println("Alquiler o Venta");
					categorie = sc.nextLine();
				}
			}
			b.saveIt();
		}
	}
}