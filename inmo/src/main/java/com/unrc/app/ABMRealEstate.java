package com.unrc.app;
import java.util.*;


import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Model;

import com.unrc.app.models.Locality;
import com.unrc.app.models.Owner;
import com.unrc.app.models.OwnersRealEstates;
import com.unrc.app.models.RealEstate;


@SuppressWarnings("unused")
public class ABMRealEstate{
	
	public void createLocality(String n){
		Locality l = Locality.create("name",n);
		l.saveIt();
	}
	
	public boolean findRealEstate(String name){
		return (RealEstate.first("rs_name = ?", name) != null);
	}

	public void Alta(String rsname, String rsmail, String rsweb, String rstel,String rsneighborhood,String rsstreet, String loc, LinkedList<String> dniowners){
		if (findRealEstate(rsname)){
			System.out.println("real estate ya existente");
		}
		else{
			RealEstate RE = RealEstate.create("rs_name", rsname, "rs_mail", rsmail, "web", rsweb, "tel", rstel, "rs_neighborhood", rsneighborhood, "rs_street", rsstreet);
			RE.saveIt();
			Locality l = Locality.first("name = ? ",loc);
			if (l == null) {
				Locality local = Locality.create("name",loc);
				local.saveIt();
				local.add(RE);

			} else {
				l.add(RE);
			}
			while(!dniowners.isEmpty()){
				String ow = dniowners.removeFirst();
				Owner o = Owner.first("owner_dni = ?", ow);
				if (o != null){
					RE.add(o);
				}else{
					System.out.println("dueño inexistente");
				}
			}
		}
		
	}

	public void Baja(String name){
		RealEstate RE = RealEstate.first("rs_name = ?", name);
		OwnersRealEstates.delete("real_estate_id = ?", RE.getId());
		RealEstate.delete("rs_name = ?", name);
	}

	public void ModWeb(String name, String web){
		RealEstate RE = new RealEstate();
		RE = RealEstate.first("rs_name = ?", name);
		RE.set("web", web);
		RE.saveIt();
	
	}

	public void ModMail(String name, String mail){
		RealEstate RE = new RealEstate();
		RE = RealEstate.first("rs_name = ?", name);
		RE.set("rs_mail", mail);
		RE.saveIt();
	}

	public void ModTel(String name, String tel){
		RealEstate RE = new RealEstate();
		RE = RealEstate.first("rs_name = ?", name);
		RE.set("tel", tel);
		RE.saveIt();
	}
		
}


