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
		LazyList<Building> build = Building.findAll();  //where("price >= ? and price <= ? and type = ? and locality_id = ?",min,max,type,verifLocality(loc));
		int i=0;
		long cant = build.size();
		String list="";
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
	
	public LazyList<Building> search(int min, int max, int type, String loc){
		LazyList<Building> buildings = Building.where("price >= ? and price <= ? and type = ? and locality_id = ?",min,max,type,verifLocality(loc));
		return buildings;
	}
	
}