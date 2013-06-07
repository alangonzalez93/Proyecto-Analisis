package com.unrc.app;
import static spark.Spark.*;
import spark.*;

import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;

import com.unrc.app.models.Building;
import com.unrc.app.models.Locality;
import com.unrc.app.models.Owner;
import com.unrc.app.models.RealEstate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@SuppressWarnings("unused")
public class Inmo {
	private static Scanner sc =new Scanner(System.in);
    public static void main( String[] args )
    {
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
      
     /*   get(new Route("/hello") {
            @Override
            public Object handle(Request request, Response response) {
               return "Hello World!";
            }
         });*/
        
//creo localidad rio cuarto y localidad achiras
	
        Locality l= Locality.first("name = ?", "rio cuarto");
        if (l==null){
    		Locality loc = Locality.create("name", "rio cuarto");
    		loc.saveIt();
        }

		
        Locality k= Locality.first("name = ?", "achiras");
        if (l==null){
    		Locality locality = Locality.create("name", "achiras");
    		locality.saveIt();
        }

        
		
		// creo 3 duenios
		ABMOwner owners = new ABMOwner();
		owners.Alta("alan gonzalez", "banda norte", "paraguay 61", "marioalangonzalez@gmail.com", "rio cuarto", "37177303");
		owners.Alta("santiago lapiana", "centro", "buenos aires 800", "santiagolapiana@gmail.com", "rio cuarto", "37546231");
		//En el siguiente caso el programa debera crear la localidad albahacas ya que no esta cargada en la base de datos hasta el momento
		owners.Alta("joaquin heredia", "hipodromo", "san martin 1000", "joaka@gmail.com", "albahacas", "37562321");
		
		//Lista de dni de duenios que se pasara como parametro para crear una inmobiliaria
		LinkedList<String> dniownersrio = new LinkedList<String>();
		//inserto dni validos, es decir de duenios ya creados
		dniownersrio.addLast("37177303");
		dniownersrio.addLast("37562321");
		
		LinkedList<String> dniownersgonzalez = new LinkedList<String>();
		dniownersgonzalez.addLast("37177303");
        
		//inserto dni de duenio nunca creado
		LinkedList<String> dniownersbienes = new LinkedList<String>();
		dniownersbienes.addLast("38456321");
		
		ABMRealEstate realestates = new ABMRealEstate();
		realestates.Alta("inmobiliaria rio cuarto", "inmorio4@gmail.com", "inmorio.com.ar", 4636160, "centro", "sobremonte 1000", "rio cuarto", dniownersrio);
		realestates.Alta("inmobiliaria gonzalez", "inmogonzalez@gmail.com", "inmogonzalez.com.ar", 156324561, "centro", "sarmiento 1500", "achiras", dniownersgonzalez);
		realestates.Alta("inmobiliaria bienes", "bienes.inmo@gmail.com", "inmobiliariabienes.com.ar", 4632365, "centro", "constitucion 1000", "rio cuarto", dniownersbienes);
		

		//damos de baja a la inmobiliaria rio cuarto
		realestates.Baja("inmobiliaria rio cuarto");
		
		//modificamos datos de la inmobiliaria gonzalez
		realestates.ModWeb("inmobiliaria gonzalez","gonzalezinmobiliaria.com.ar");
		realestates.ModMail("inmobiliaria gonzalez", "gonzalezinmobiliaria@hotmail.com");
		realestates.ModTel("inmobiliaria gonzalez", 4651236);
		
        ABMBuilding buil = new ABMBuilding();
        buil.altaBuilding("casa centrica", 500000, "venta", "casa", "centro", "san martin 500", "rio cuarto", "37177303");
        buil.altaBuilding("banda norte", 2000, "alquiler", "casa", "banda norte", "cordoba 661", "rio cuarto", "37546231");
        buil.altaBuilding("barrio alberdi", 3500, "alquiler", "casa", "alberdi", "jujuy 451", "achiras", "37546231");
        
        Search prueba = new Search();
        final String cadena = prueba.toString();
        System.out.println(cadena);
        
        get(new Route("/buildings") {
            @Override
            public Object handle(Request request, Response response) {
               return ("Inmuebles"+cadena);
            }
         });
        
        System.out.println( "Hello World!" );
    }
}
