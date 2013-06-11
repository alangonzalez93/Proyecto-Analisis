package com.unrc.app;
import static spark.Spark.*;

import com.unrc.app.ABMBuilding;
import com.unrc.app.ABMOwner;
import com.unrc.app.ABMRealEstate;


import spark.*;

import org.javalite.activejdbc.*;


import com.unrc.app.models.Building;
import com.unrc.app.models.Locality;
import com.unrc.app.models.Owner;
import com.unrc.app.models.RealEstate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.io.*;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import freemarker.template.Configuration;




@SuppressWarnings("unused")
public class Inmo {
	
	
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
        
        Search search = new Search();
        final String cadena = search.toString();
        
        	get(new Route("/buildings") {
               @Override
               public Object handle(Request request, Response response) {
                  return "Inmuebles: "+cadena;
               }
            });
        	
       
        	get(new Route("/index") {
                @Override
               
                public Object handle(Request request, Response response) {
       			//Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "Pantera1.");
                   String title = "<h1> Inmobiliaria </h1>";

       			String menu = "<ul> <li> <a href=\"/building\"> Inmuebles </a> </li> </ul>";
       			String menu2 = "<ul> <li> <a href=\"/owner\"> Dueños </a> </li> </ul>";
       			String menu3 = "<ul> <li> <a href=\"/realEstate\"> Inmobiliarias </a> </li> </ul>";

       			//Base.close();
                   return title + menu + menu2 + menu3;
                };
       		 });
        	
        	get(new Route("/building") {
                @Override
               
                public Object handle(Request request, Response response) {
       			//Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "Pantera1.");
                   String title = "<h1>Menú Inmueble </h1>";

       			String alta = "<ul> <li> <a href=\"/altaInmueble\"> Crear inmueble </a> </li> </ul>";
       			String baja = "<ul> <li> <a href=\"/bajaInmueble\"> Eliminar inmueble </a> </li> </ul>";
       			String listar  = "<ul> <li> <a href=\"/listarInmueble\"> Listar inmuebles </a> </li> </ul>";
       			String atras = "<ul> <li> <a href=\"/index\">Volver</a></li></ul>";

       			//Base.close();
                   return title + alta + baja + listar + atras;
                };
       		 });
        	
        	get(new Route("/owner") {
                @Override
               
                public Object handle(Request request, Response response) {
       			//Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "Pantera1.");
                   String title = "<h1>Menú Dueños </h1>";

       			String alta = "<ul> <li> <a href=\"/altaOwner\"> Crear dueño </a> </li> </ul>";
       			String baja = "<ul> <li> <a href=\"/bajaOwner\"> Eliminar dueño </a> </li> </ul>";
       			String listar  = "<ul> <li> <a href=\"/listarOwner\"> Listar dueños </a> </li> </ul>";
       			String atras = "<ul> <li> <a href=\"/index\">Volver</a></li></ul>";

       			//Base.close();
                   return title + alta + baja + listar + atras;
                };
       		 });
        	
        	get(new Route("/realEstate") {
                @Override
               
                public Object handle(Request request, Response response) {
       			//Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "Pantera1.");
                   String title = "<h1>Menú Inmobiliarias </h1>";

       			String alta = "<ul> <li> <a href=\"/altaRS\"> Crear inmobiliaria </a> </li> </ul>";
       			String baja = "<ul> <li> <a href=\"/bajaRS\"> Eliminar inmobiliaria </a> </li> </ul>";
       			String listar  = "<ul> <li> <a href=\"/listarRS\"> Listar inmobiliarias </a> </li> </ul>";
       			String atras = "<ul> <li> <a href=\"/index\">Volver</a></li></ul>";

       			//Base.close();
                   return title + alta + baja + listar + atras;
                };
       		 });
        	
            post(new Route("/owner") {
              //  Random random = new Random();
                @Override
                public Object handle(Request request, Response response) {
                    String name = request.queryParams("owner_name");
                    String dni = request.queryParams("owner_dni");
                    String neighborhood = request.queryParams("neighborhood");
                    String street = request.queryParams("street");
                    String mail = request.queryParams("mail");
                    String locality = request.queryParams("locality");
                    ABMOwner abmO = new ABMOwner();
                    abmO.Alta(name, neighborhood, street, mail, locality, dni);
                 //   int id = random.nextInt(Integer.MAX_VALUE);
                  //  books.put(String.valueOf(id), book);
                    
                    response.status(201); // 201 Created
                    return ("Dueño DNI: "+ dni +"exitosamente");
                }
            });
            

            get(new Route("/listarOwner") {
            	Search search = new Search();
            	final String owner = search.OwnerStr();
            	@Override
                public Object handle(Request request, Response response) {
                   return "Dueños: "+ owner + "";
                }
             });
            
            get(new Route("/listarRS") {
            	Search search = new Search();
            	final String rs = search.RealEstates();
            	@Override
                public Object handle(Request request, Response response) {
                   return "Inmobiliaria: "+ rs;
                }
             });

            
            get(new Route("/listarInmueble") {
            	Search search = new Search();
            	final String inmuebles = search.toString();
            	@Override
                public Object handle(Request request, Response response) {
                   return "Inmuebles: "+inmuebles;
                }
             });
            
            
            post(new Route("/alta") {
            	@Override 
            	public Object handle(Request request, Response response) {
            		//(String description, int price, String category, String type, 
            		//String neighborhood, String street, String locality, String dni) {
            		String description = request.queryParams("description");
            		Integer price = Integer.parseInt(request.queryParams("price"));
            		String category = request.queryParams("category");
            		String type = request.queryParams("type");
            		String neighborhood = request.queryParams("neighborhood");
            		String street = request.queryParams("street");
            		String locality = request.queryParams("locality");
            		String dni = request.queryParams("dni");
            		ABMBuilding abmB = new ABMBuilding();
            		abmB.altaBuilding(description, price, category, type, neighborhood, street, locality, dni);
            		String volver =  "<ul> <li> <a href=\"/building\">Volver</a></li></ul>";
            		
            		response.status(201); // 201 Created
                    return ("Inmueble creado exitosamente en la direccion: "+ street + "/br" +  "<ul> <li> <a href=\"/index/building\">Volver</a></li></ul>");
            	}
            });
			
            
           System.out.println( "Hello World!" );
    }
}
