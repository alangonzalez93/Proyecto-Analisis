package com.unrc.app;
//package com.unrc.app;
//import java.util.LinkedList;

//import java.util.LinkedList;
//import java.util.List;

//import javax.swing.text.html.HTMLDocument.Iterator;

import java.util.List;

import org.javalite.activejdbc.LazyList;
//import org.javalite.activejdbc.Model;

import com.unrc.app.models.Building;
import com.unrc.app.models.Locality;
import com.unrc.app.models.Owner;
import com.unrc.app.models.RealEstate;
import com.unrc.app.ABMBuilding;


public class Search{
	
	public int verifLocality(String locality){
		Locality loc = Locality.first("name = ?", locality);
		int id_loc = 0;
		if (loc != null){
			id_loc = loc.getInteger("id");
			}
		return id_loc;
	}
	
	
	public String searchByCat(String category){
		List<Building> build = Building.where("category =?", category);
		String list = " ";
		
		int i = 0;
		while(!build.isEmpty() && i < build.size()) {
			Building b = build.get(i);
			int id = b.getInteger("id");
			int price = b.getInteger("price");
			String type = b.getString("type");
			String description = b.getString("description");
			String barrio = b.getString("neighborhood");
			String street = b.getString("b_street");
			list = "\n" + list + "ID: " + id + ", Tipo: "+ type + ", Descripcion: "+ description + ", Precio: " + price + ", Barrio: " + barrio + ", Direccion: " + street +"\n"+"\n";
			i++;
		}
		return list;	
	}
	
	public String searchByType(String type) {
		List<Building> build = Building.where("type =?", type);
		String list = " ";
		
		int i = 0;
		while(!build.isEmpty() && i < build.size()) {
			Building b = build.get(i);
			int id = b.getInteger("id");
			int price = b.getInteger("price");
			String category = b.getString("category");
			String description = b.getString("description");
			String barrio = b.getString("neighborhood");
			String street = b.getString("b_street");
			list = "\n" + list + "ID: " + id + ", Categoria: " + category +", Descripcion: "+ description + ", Precio: " + price +  ", Barrio: " + barrio + ", Direccion: " + street +"\n"+"\n";
			i++;
		}
		return list;	
	}
	
	public String searchByPrice(int minPrice, int maxPrice) {
		List<Building> build = Building.findBySQL("select * from buildings where price >= "+minPrice+" and price <= " + maxPrice);
		String list = " ";
		int i = 0;
		while(!build.isEmpty() && i < build.size()) {
			Building b = build.get(i);
			int id = b.getInteger("id");
			int p = b.getInteger("price");
			String description = b.getString("description");
			String barrio = b.getString("neighborhood");
			String street = b.getString("b_street");
			String type = b.getString("type");
			String cat = b.getString("category");
			list = "\n" + list + "ID: " + id +", Tipo: "+ type + ", Categoria: " + cat +", Descripcion: "+ description + ", Precio: " + p + ", Barrio: " + barrio + ", Direccion: " + street +"\n"+"\n";
			i++;
		}
		return list;	
	}
	
	public String searchByLocality(int id) {
		List<Building> build = Building.where("locality_id = ? ", id);
		String list = " ";
		int i = 0;
		if (build == null) {
			list = "No existen edificios en esta localidad";
		}
		while(!build.isEmpty() && i < build.size()) {
			Building b = build.get(i);
			int idb = b.getInteger("id");
			int p = b.getInteger("price");
			String description = b.getString("description");
			String barrio = b.getString("neighborhood");
			String street = b.getString("b_street");
			String type = b.getString("type");
			String cat = b.getString("category");
			list = "\n" + list + "ID: " + idb +", Tipo: "+ type + ", Categoria: " + cat +", Descripcion: "+ description + ", Precio: " + p +  ", Barrio: " + barrio + ", Direccion: " + street +"\n"+"\n";
			i++;
		}
		return list;	
	}
			
	public String toString(){
		LazyList<Building> build = Building.findAll();
		int i=0;
		long cant = build.size();
		String list = "";
		while ((!build.isEmpty()) && i < cant){
			Building buil = build.get(i);
			int id = buil.getInteger("id");
			int price = buil.getInteger("price");
			String type = buil.getString("type");
			String neigh = buil.getString("neighborhood");
			String desc = buil.getString("description");
			String street = buil.getString("b_street");
			String category = buil.getString("category");
			int idloc = buil.getInteger("locality_id");
			int idow = buil.getInteger("owner_id");
			Locality locality = Locality.first("id = ?", idloc);
			String namelocality = locality.getString("name");
			Owner owner = Owner.first("id = ?", idow);
			String nameow = owner.getString("owner_name");
			String mail = owner.getString("owner_mail");
			
			list= "\n"+list+". Precio: "+price+ ", id: "+ id +", Tipo: "+type+", Categoria: "+category +", Direccion: "+street+", Barrio: "+neigh+", Localidad: "+namelocality+ ", Descripcion: "+desc+", Duenio: "+ nameow+ ", Mail de contacto " + mail+ "\n";
			i++;
		}
		return list;
	}
	
	public String RealEstates(){
		LazyList<RealEstate> re = RealEstate.findAll();
		int i=0;
		long cant = re.size();
		String list="";
		while ((!re.isEmpty()) && i < cant){
			RealEstate realestate = re.get(i);
			int id = realestate.getInteger("id");
			String web = realestate.getString("web");
			String mail = realestate.getString("rs_mail");
			String name = realestate.getString("rs_name");
			String neigh = realestate.getString("rs_neighborhood");
			String street = realestate.getString("rs_street");
			int tel = realestate.getInteger("tel");
			int locality = realestate.getInteger("locality_id");
			Locality loc = Locality.first("id = ?", locality);
			String namelocality = loc.getString("name");
			
			list= "\n"+list+". Nombre: "+name+ ", id: "+ id + ", Localidad: "+ namelocality+ ", Barrio: "+ neigh+ ", Calle: "+ street+ ", Telefono: "+ tel+ ", Mail: "+mail+", Web: "+web +"\n";
			i++;
		}
		return list;
	}
	
	public String OwnerStr(){
		LazyList<Owner> own = Owner.findAll(); 
		int i=0;
		long cant = own.size();
		String listOwn = "";
		while ((!own.isEmpty()) && i< cant){
			Owner ownie = own.get(i);
			String name= ownie.getString("owner_name");
			String mail= ownie.getString("owner_mail");
			String neighborhood = ownie.getString("owner_neighborhood");
			String street = ownie.getString("owner_street");
			String dni = ownie.getString("owner_dni");
			int locality = ownie.getInteger("locality_id");
			Locality loc = Locality.first("id =?",locality);
			String name_locality = loc.getString ("name");
			listOwn = "\n"+listOwn+". Nombre: "+name+", E-mail: "+mail+", Localidad: "+name_locality+", Barrio: "+neighborhood+", Calle: "+street+", DNI: "+dni+"\n";
			i++;
		}
		return listOwn;
	}
	
}