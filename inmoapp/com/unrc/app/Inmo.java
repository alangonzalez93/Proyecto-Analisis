package com.unrc.app;

import org.javalite.activejdbc.Base;

import com.unrc.app.models.Building;
import com.unrc.app.models.Locality;
import com.unrc.app.models.Owner;
import com.unrc.app.models.RealEstate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SuppressWarnings("unused")
public class Inmo {
    public static void main( String[] args )
    {
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
        //creo una localidad
		Locality l= new Locality();
		l.set("name", "rio cuarto");
		l.saveIt();
		
		//creo una inmobiliaria	
		RealEstate e = new RealEstate();
       
        e.set("web", "holahola");
        e.set("rs_mail", "cacacaca@hotmail.com");
        e.set("rs_name", "alan");
 	    e.set("rs_neighborhood", "holakase");
 	    e.set("rs_street", "kinda");
 	    e.set("tel", "62");
		e.saveIt();
		l.add(e);
    
 	    //creo un dueño
        
        Owner owner_realestate = new Owner();
        
        owner_realestate.set("owner_name","alan");
        owner_realestate.set("owner_neighborhood","banda norte");
        owner_realestate.set("owner_street","san martin");
        owner_realestate.set("owner_mail","alan@alan.com");
        
      //relaciono con la localidad creada anteriormente
        l.add(owner_realestate);
        owner_realestate.saveIt();
     //   e.add(owner_realestate);
        owner_realestate.add(e);



     //creo un dueño
        
        Owner owner_building = new Owner();
        
        owner_building.set("owner_name","joaka");
        owner_building.set("owner_neighborhood","alguno");
        owner_building.set("owner_street","alguna otra");
        owner_building.set("owner_mail","holachau@tumama.com");
      //relaciono con la localidad creada anteriormente
        l.add(owner_building);
        owner_building.saveIt();
        
      // creamos un inmueble
        Building inmueble1 = new Building();
        inmueble1.set("price","5000");
        inmueble1.set("type","2");
        inmueble1.set("neighborhood","banda norte");
        inmueble1.set("description","la casa esta en banda norte");
        inmueble1.set("b_street","paraguay 61");
        inmueble1.set("categorie","1");
      //relaciono con la localidad creada anteriormente
        l.add(inmueble1);
        inmueble1.saveIt();
        //relaciono con el dueño creado anteriormente
        owner_building.add(inmueble1);
        
        
        // creamos otro inmueble
        Building inmueble2 = new Building();
        inmueble2.set("price","2000");
        inmueble2.set("type","1");
        inmueble2.set("neighborhood","centro");
        inmueble2.set("description","casa centrica");
        inmueble2.set("b_street","buenos aires 200");
        inmueble2.set("categorie","2");
      //relaciono con la localidad creada anteriormente
        l.add(inmueble2);
        inmueble2.saveIt();
        //relaciono con el dueño creado antes asi ese dueño tiene 2 inmuebles
        owner_building.add(inmueble2);
         
        System.out.println( "Hello World!" );
    }
}
