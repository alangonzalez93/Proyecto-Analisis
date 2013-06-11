package com.unrc.app;
//package com.unrc.app;
//import java.util.LinkedList;

//import java.util.LinkedList;
//import java.util.List;

//import javax.swing.text.html.HTMLDocument.Iterator;

import org.javalite.activejdbc.LazyList;
//import org.javalite.activejdbc.Model;

import com.unrc.app.models.Building;
import com.unrc.app.models.Locality;
import com.unrc.app.models.Owner;
import com.unrc.app.models.RealEstate;


public class Search{
	
	public int verifLocality(String locality){
		Locality loc = Locality.first("name = ?", locality);
		int id_loc = 0;
		if (loc != null){
			id_loc = loc.getInteger("id");
			}
		return id_loc;
	}
	
	public String toString(){
		LazyList<Building> build = Building.findAll();//where("price >= ? and price <= ? and type = ? and locality_id = ?",min,max,type,verifLocality(loc));
		int i=0;
		long cant = build.size();
		String list=null;
		while ((!build.isEmpty()) && i < cant){
			Building buil = build.get(i);
			int id = buil.getInteger("id");
			//System.out.println(id);
			int price = buil.getInteger("price");
			//System.out.println(price);
			list= list+" precio: "+price+ " id: "+ id;
			i++;
		}
		return list;
	}
	
	public String RealEstates(){
		LazyList<RealEstate> re = RealEstate.findAll();//where("price >= ? and price <= ? and type = ? and locality_id = ?",min,max,type,verifLocality(loc));
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
			
			list= list+" Nombre: "+name+ " id: "+ id + " Localidad: "+ namelocality+ " Barrio: "+ neigh+ " Calle: "+ street+ " Telefono: "+ tel+ " Mail: "+mail+" Web: "+web;
			i++;
		}
		return list;
	}
	
	public String OwnerStr(){
		LazyList<Owner> own = Owner.findAll(); //where fuck da shit
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
			listOwn = listOwn+" Nombre: "+name+" E-mail: "+mail+" Localidad: "+name_locality+" Barrio: "+neighborhood+" Calle: "+street+" DNI: "+dni;
			i++;
		}
		return listOwn;
	}
	
	public LazyList<Building> search(int min, int max, int type, String loc){
		LazyList<Building> buildings = Building.where("price >= ? and price <= ? and type = ? and locality_id = ?",min,max,type,verifLocality(loc));
		return buildings;
	}
	
}