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


public class Inmo {
	
	
    public static void main( String[] args ){
     
       //Se crea una nueva configuración, para tener acceso a los recursos
      final Configuration configuracion = new Configuration();
      configuracion.setClassForTemplateLoading(Inmo.class, "/");
    	
    	
       //Menu principal
        	get(new Route("/index") {
                @Override
               
                public Object handle(Request request, Response response) {
       			//Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
       			//Base.openTransaction();
                String head = "<head>" + 
                		"<title>Sistema de Gestión Inmobiliaria</title>" +
                		" </head>";
                		
                	
                String header = "<h1> Inmobiliaria </h1>";

       			String menu = "<ul> <li> <a href=\"/building\"> Inmuebles </a> </li> </ul>";
       			String menu2 = "<ul> <li> <a href=\"/owner\"> Dueños </a> </li> </ul>";
       			String menu3 = "<ul> <li> <a href=\"/realEstate\"> Inmobiliarias </a> </li> </ul>";
       			
       			//Base.close();
                   return head+  header + menu + menu2 + menu3;
                };
       		 });
       
       //Menu de inmuebles	
        	get(new Route("/building") {
                @Override
                
                public Object handle(Request request, Response response) {
                	
                String head = "<head>" + 
                    		"<title>Gestion de Inmuebles</title>" +
                    		" </head>";
                	
       			String header = "<h1>Menú Inmueble </h1>";

       			String alta = "<ul> <li> <a href=\"/altaInmueble\"> Crear inmueble </a> </li> </ul>";
       			String baja = "<ul> <li> <a href=\"/bajaInmueble\"> Eliminar inmueble </a> </li> </ul>";
       			String listar  = "<ul> <li> <a href=\"/listarInmueble\"> Listar inmuebles </a> </li> </ul>";
       			String atras = "<ul> <li> <a href=\"/index\">Volver</a></li></ul>";
       			
       			return head + header + alta + baja + listar + atras;
                };
       		 });
       
       //Menu de duenos
        	get(new Route("/owner") {
                @Override
               
                public Object handle(Request request, Response response) {
                String head = "<head>" + 
                    		"<title>Gestion de Duenos</title>" +
                    		" </head>";
                	
                String header = "<h1>Menú Dueños </h1>";

       			String alta = "<ul> <li> <a href=\"/altaOwner\"> Crear dueño </a> </li> </ul>";
       			String baja = "<ul> <li> <a href=\"/bajaOwner\"> Eliminar dueño </a> </li> </ul>";
       			String listar  = "<ul> <li> <a href=\"/listarOwner\"> Listar dueños </a> </li> </ul>";
       			String atras = "<ul> <li> <a href=\"/index\">Volver</a></li></ul>";
       			
                   return head + header + alta + baja + listar + atras;
                };
       		 });
       	
       //Menu de inmobiliarias
        	get(new Route("/realEstate") {
                @Override
               
                public Object handle(Request request, Response response) {
              
                String head = "<head>" + 
                    		"<title>Gestion de Inmobiliarias</title>" +
                    		" </head>";
                	
                String header = "<h1>Menú Inmobiliarias </h1>";

       			String alta = "<ul> <li> <a href=\"/altaRS\"> Crear inmobiliaria </a> </li> </ul>";
       			String baja = "<ul> <li> <a href=\"/bajaRS\"> Eliminar inmobiliaria </a> </li> </ul>";
       			String listar  = "<ul> <li> <a href=\"/listarRS\"> Listar inmobiliarias </a> </li> </ul>";
       			String atras = "<ul> <li> <a href=\"/index\">Volver</a></li></ul>";
       			
       		
                   return head+ header + alta + baja + listar + atras;
                };
       		 });
        	
       //Crear Dueno 
        	post(new Route("/newOwner") {
                @Override
                public Object handle(Request request, Response response) {
                	String head = "<head>" + 
                    		"<title>Dueno Creado</title>" +
                    		" </head>";
                	
                	Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
                	
                	Base.openTransaction();
                	
                		String name = request.queryParams("owner_name");
                		String dni = request.queryParams("owner_dni");
                		String neighborhood = request.queryParams("neighborhood");
                		String street = request.queryParams("street");
                		String mail = request.queryParams("mail");
                		String locality = request.queryParams("locality");
                		ABMOwner abmO = new ABMOwner();
                		abmO.Alta(name, neighborhood, street, mail, locality, dni);
                    
                	Base.commitTransaction();
                	Base.close();
                		
                    response.status(201); // 201 Created
                    return (head + "Dueño DNI: "+ dni +" exitosamente");
                }
            });
            
       //Crear Inmueble  
            post(new Route("/newBuilding") {
            	@Override 
            	public Object handle(Request request, Response response) {
            		String head = "<head>" + 
                    		"<title>Inmueble Creado</title>" +
                    		" </head>";
            		Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
                	
                	Base.openTransaction();
            		
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
        
            		
            		Base.commitTransaction();
            		
            		Base.close();
            		
            		response.status(201); // 201 Created
                    return (head +"Inmueble creado exitosamente en la direccion: "+ street);
            	}
            });
        	
       //Crear Inmobiliaria	
            post(new Route("/newRealEstate") {
           
            @SuppressWarnings("unused")
			public Object handle(Request request, Response response) {
            String head = "<head>" + 
            		"<title>Inmobiliaria Creada</title>" +
            		" </head>";
    		Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
        	
        	Base.openTransaction();
    		
    		String name = request.queryParams("name");
    		String mail = request.queryParams("mail");
    		String web = request.queryParams("web");
    		int tel = Integer.parseInt(request.queryParams("tel"));
    		String neighborhood = request.queryParams("neighborhood");
    		String street = request.queryParams("street");
    		String locality = request.queryParams("locality");
    		//LinkedList<String> dnis = request.;
    		ABMRealEstate abmRS = new ABMRealEstate();
    		//abmRS.Alta(name, mail, web, tel, neighborhood, street, locality, dnis);

    		
    		Base.commitTransaction();
    		
    		Base.close();
    		
    		response.status(201); // 201 Created
            return (head +"Inmueble creado exitosamente en la direccion: "+ street);
            }
    });
       //Listar duenos    
            get(new Route("/listarOwner") {
            	       
            	@Override
                public Object handle(Request request, Response response) {
            		String head = "<head>" + 
                    		"<title>Lista completa de Duenos</title>" +
                    		" </head>";
            		
            		Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
           			
           			Search search = new Search();
                	final String owner = search.OwnerStr();
            		
                	Base.close();
                	return head + "Dueños: "+ owner + "";
                }
             });
       //Listar Inmobiliarias    
            get(new Route("/listarRS") {
            	
            	@Override
                public Object handle(Request request, Response response) {
            		String head = "<head>" + 
                    		"<title>Listado completo de Inmobiliarias</title>" +
                    		" </head>";
            		Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
            		
            		Search search = new Search();
                	final String rs = search.RealEstates();
                	
                	Base.close();
              
                   return head + "Inmobiliaria: "+ rs;
                }
             });
       //Listar Inmuebles
            get(new Route("/listarInmueble") {
            	
            	@Override
                public Object handle(Request request, Response response) {
            		
            		String head = "<head>" + 
                    		"<title>Listado completo de Inmuebles</title>" +
                    		" </head>";
            		
            		Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
            		
            		Search search = new Search();
                	final String inmuebles = search.toString();
            		
                	Base.close();
                   return head +"Inmuebles: "+inmuebles;
                }
             });
            
       //HTML para dar de alta duenos.  
            Spark.get( new Route("/altaOwner") {
        	  //El objeto que recogerá la salida
        	  StringWriter writer = new StringWriter();
        	  @Override
        	  public Object handle(Request arg0, Response arg1) {
        	  try {
        		  
                  //Se carga la plantilla
        		  Template plantilla = configuracion.getTemplate("newOwner.html");
        		  //Cargamos el map, que se va a utilizar para aplicarlo a la plantilla
        		  Map<String, Object> ownerMap = new HashMap<String, Object>();
        		  //Procesamos la plantilla
        		  plantilla.process(ownerMap, writer);
        	  } catch (Exception e) {
        		  // Si falla, devuelve un 500
        		  halt(500);
        		  e.printStackTrace();
        	  }
        	  return writer;
           }
          });
       
       //HTML para dar de alta inmuebles.
            Spark.get( new Route("/altaBuilding") {
          	  //El objeto que recogerá la salida
          	  StringWriter writer = new StringWriter();
          	  @Override
          	  public Object handle(Request arg0, Response arg1) {
          	  try {
          		  
                    //Se carga la plantilla
          		  Template plantilla = configuracion.getTemplate("newBuilding.html");
          		  //Cargamos el map, que se va a utilizar para aplicarlo a la plantilla
          		  Map<String, Object> buildingMap = new HashMap<String, Object>();
          		  //Procesamos la plantilla
          		  plantilla.process(buildingMap, writer);
          	  } catch (Exception e) {
          		  // Si falla, devuelve un 500
          		  halt(500);
          		  e.printStackTrace();
          	  }
          	  return writer;
             }
            });
       //HTML para dar de alta inmobiliarias.
            Spark.get( new Route("/altaRealEstate") {
          	  //El objeto que recogerá la salida
          	  StringWriter writer = new StringWriter();
          	  @Override
          	  public Object handle(Request arg0, Response arg1) {
          	  try {
          		  
                    //Se carga la plantilla
          		  Template plantilla = configuracion.getTemplate("newRS.html");
          		  //Cargamos el map, que se va a utilizar para aplicarlo a la plantilla
          		  Map<String, Object> rsMap = new HashMap<String, Object>();
          		  //Procesamos la plantilla
          		  plantilla.process(rsMap, writer);
          	  } catch (Exception e) {
          		  // Si falla, devuelve un 500
          		  halt(500);
          		  e.printStackTrace();
          	  }
          	  return writer;
             }
            });
            
            
           System.out.println( "Hello World!" );
    }
}
