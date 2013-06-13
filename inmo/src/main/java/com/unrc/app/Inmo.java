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

       			String alta = "<ul> <li> <a href=\"/altaBuilding\"> Crear inmueble </a> </li> </ul>";
       			String baja = "<ul> <li> <a href=\"/bajaBuilding\"> Eliminar inmueble </a> </li> </ul>";
       			String listar  = "<ul> <li> <a href=\"/listarBuilding\"> Listar inmuebles </a> </li> </ul>";
       			String busqueda  = "<ul> <li> <a href=\"/buildingSearchMenu\"> Buscar Inmuebles </a> </li> </ul>";
       			String atras = "<ul> <li> <a href=\"/index\">Volver</a></li></ul>";
       			
       			return head + header + alta + baja + listar+ busqueda + atras;
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
       	
       //Menu de busqueda de inmuebles
        	get(new Route("/buildingSearchMenu") {
                @Override
               
                public Object handle(Request request, Response response) {
                String head = "<head>" + 
                    		"<title>Menu de Busqueda de Inmuebles</title>" +
                    		" </head>";
                	
                String header = "<h1>Buscar por: </h1>";

       			String cat = "<ul> <li> <a href=\"/searchByCategory\"> Categoria </a> </li> </ul>";
       			String tipo = "<ul> <li> <a href=\"/searchByType\"> Tipo </a> </li> </ul>";
       			String precio  = "<ul> <li> <a href=\"/searchByPrice\"> Precio </a> </li> </ul>";
       			String loc = "<ul> <li> <a href=\"/searchByLocality\"> Localidad </a> </li> </ul>";
       			
       			
       			String atras = "<ul> <li> <a href=\"/building\">Volver</a></li></ul>";
       			
                   return head + header + cat+ tipo + precio + loc + atras;
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

       			String alta = "<ul> <li> <a href=\"/altaRealEstate\"> Crear inmobiliaria </a> </li> </ul>";
       			String baja = "<ul> <li> <a href=\"/bajaRealEstate\"> Eliminar inmobiliaria </a> </li> </ul>";
       			String listar  = "<ul> <li> <a href=\"/listarRealEstate\"> Listar inmobiliarias </a> </li> </ul>";
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
                	String atras = "<ul> <li> <a href=\"/owner\">Volver</a></li></ul>";
                	Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
                	
                	Base.openTransaction();
                	
                		String name = request.queryParams("owner_name");
                		String dni = request.queryParams("owner_dni");
                		String neighborhood = request.queryParams("neighborhood");
                		String street = request.queryParams("street");
                		String mail = request.queryParams("mail");
                		String locality = request.queryParams("locality");
                		try {
                			ABMOwner abmO = new ABMOwner();
                			if(abmO.findOwner(dni)) {
                				String msjError = "<head>" + 
                                		"<title>Error</title>" +
                                		" </head>"+
                                		"Dueno ya existente";
                				Base.close();
                				return  msjError + atras;
                			}
                			abmO.Alta(name, neighborhood, street, mail, locality, dni);
                		} catch(Exception e) {
                			Base.close();
                			halt(500);
                		}
                	Base.commitTransaction();
                	Base.close();
                		
                    response.status(201); // 201 Created
                    return (head + "Dueno DNI: "+ dni +" exitosamente");
                }
            });
            
       //Crear Inmueble  
            post(new Route("/newBuilding") {
            	@Override 
            	public Object handle(Request request, Response response) {
            		String head = "<head>" + 
                    		"<title>Inmueble Creado</title>" +
                    		" </head>";
            		String atras = "<ul> <li> <a href=\"/building\">Volver</a></li></ul>";
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
            		
            		try {
            			ABMBuilding abmB = new ABMBuilding();
            			if(abmB.findInmueble(street, neighborhood)) {
            				String msjError = "<head>" + 
                            		"<title>Error</title>" +
                            		" </head>"+
                            		"Inmueble ya existente";
            				Base.rollbackTransaction();
            				Base.close();
            				return  msjError + atras;
            			}
            			if(!abmB.findOwner(dni)) {
            				String msjError = "<head>" + 
                    		"<title>Error</title>" +
                    		" </head>"+
                    		"Duenio inexistente";
            				Base.rollbackTransaction();
            				Base.close();
            				return  msjError + atras;
            			}
            			abmB.altaBuilding(description, price, category, type, neighborhood, street, locality, dni);
            		} catch (Exception e) {
            			Base.rollbackTransaction();
            			Base.close();
            			System.out.println(e);
            			halt(500);
            		}
            		
            		Base.commitTransaction();
            		
            		Base.close();
            		
            		response.status(201); // 201 Created
                    return (head +"Inmueble creado exitosamente en la direccion: "+ street + atras);
            	}
            });
        	
       //Crear Inmobiliaria	
            post(new Route("/newRealEstate") {
           
			public Object handle(Request request, Response response) {
            String head = "<head>" + 
            		"<title>Inmobiliaria Creada</title>" +
            		" </head>";
            String atras = "<ul> <li> <a href=\"/realEstate\">Volver</a></li></ul>";
    		Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
        	
        	Base.openTransaction();
    		
    		String name = request.queryParams("name");
    		String mail = request.queryParams("mail");
    		String web = request.queryParams("web");
    		String tel = request.queryParams("tel");
    		String neighborhood = request.queryParams("neighborhood");
    		String street = request.queryParams("street");
    		String locality = request.queryParams("locality");
    		String dnis = request.queryParams("dni");
    		if (dnis.length() != 0) {
    			ABMOwner o = new ABMOwner();
    			String[] aux = dnis.split(",");
    			LinkedList<String> listDni = new LinkedList<String>();
    			for (int i=0;i<aux.length;i++) {
    				if(!o.findOwner(aux[i])) {
    					String msjError = "<head>" + 
        	            		"<title>Error</title>" +
        	            		" </head>";
        				Base.close();
        				return msjError + "Duenio DNI: " + aux[i] + " inexistente" + atras;
    				}
    				listDni.add(aux[i]);
    			}	
    			try {
    				ABMRealEstate abmRS = new ABMRealEstate();
    				if(abmRS.findRealEstate(name)) {
    					String msjError = "<head>" + 
    	            		"<title>Error</title>" +
    	            		" </head>";
    					Base.close();
    					return msjError + "Inmobiliaria ya existente" + atras;
    				}
    				abmRS.Alta(name, mail, web, tel, neighborhood, street, locality, listDni);

    			} catch(Exception e) {
    				Base.close();
    				System.out.println(e);
    				halt(500);
    			} 
    		Base.commitTransaction();
    		
    		Base.close();
    		
    		response.status(201); // 201 Created
            return (head +"Inmobiliaria creado exitosamente en la direccion: "+ street + atras);
            } else {
            	String errorDni = "No se ha ingresado ningun dni";
            	Base.close();
            	return errorDni + atras;
            }
		}
    });
      
       //Listar duenos    
            get(new Route("/listarOwner") {
            	       
            	@Override
                public Object handle(Request request, Response response) {
            		String atras = "<ul> <li> <a href=\"/owner\">Volver</a></li></ul>";
            		String head = "<head>" + 
                    		"<title>Lista completa de Duenos</title>" +
                    		" </head>";
            		
            		Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
           			
           			Search search = new Search();
                	final String owner = search.OwnerStr();
            		
                	Base.close();
                	return head + "Dueños: "+ owner + "" + atras;
                }
             });
       
       //Listar Inmobiliarias    
            get(new Route("/listarRealEstate") {
            	
            	@Override
                public Object handle(Request request, Response response) {
            		String atras = "<ul> <li> <a href=\"/realEstate\">Volver</a></li></ul>";
            		String head = "<head>" + 
                    		"<title>Listado completo de Inmobiliarias</title>" +
                    		" </head>";
            		Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
            		
            		Search search = new Search();
                	final String rs = search.RealEstates();
                	
                	Base.close();
              
                   return head + "Inmobiliaria: "+ rs +atras;
                }
             });
       
       //Listar Inmuebles
            get(new Route("/listarBuilding") {
            	
            	@Override
                public Object handle(Request request, Response response) {
            		String atras = "<ul> <li> <a href=\"/building\">Volver</a></li></ul>";
            		String head = "<head>" + 
                    		"<title>Listado completo de Inmuebles</title>" +
                    		" </head>";
            		
            		Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
            		
            		Search search = new Search();
                	final String inmuebles = search.toString();
            		
                	Base.close();
                   return head +"Inmuebles: "+inmuebles + atras ;
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
          		  Template plantilla = configuracion.getTemplate("newRealEstate.html");
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
       
       //HTML para buscar por categoria.     
            Spark.get( new Route("/searchByCategory") {
            	  //El objeto que recogerá la salida
            	  StringWriter writer = new StringWriter();
            	  @Override
            	  public Object handle(Request arg0, Response arg1) {
            	  try {
            		  
                      //Se carga la plantilla
            		  Template plantilla = configuracion.getTemplate("srchCtgry.html");
            		  //Cargamos el map, que se va a utilizar para aplicarlo a la plantilla
            		  Map<String, Object> searchMap = new HashMap<String, Object>();
            		  //Procesamos la plantilla
            		  plantilla.process(searchMap, writer);
            	  } catch (Exception e) {
            		  // Si falla, devuelve un 500
            		  halt(500);
            		  e.printStackTrace();
            	  }
            	  return writer;
               }
              });
           
       //HTML para buscar por categoria.     
            Spark.get( new Route("/searchByType") {
            	  //El objeto que recogerá la salida
            	  StringWriter writer = new StringWriter();
            	  @Override
            	  public Object handle(Request arg0, Response arg1) {
            	  try {
            		  
                      //Se carga la plantilla
            		  Template plantilla = configuracion.getTemplate("srchType.html");
            		  //Cargamos el map, que se va a utilizar para aplicarlo a la plantilla
            		  Map<String, Object> searchMap = new HashMap<String, Object>();
            		  //Procesamos la plantilla
            		  plantilla.process(searchMap, writer);
            	  } catch (Exception e) {
            		  // Si falla, devuelve un 500
            		  halt(500);
            		  e.printStackTrace();
            	  }
            	  return writer;
               }
              });
            
       //HTML para buscar por categoria.     
            Spark.get( new Route("/searchByPrice") {
            	  //El objeto que recogerá la salida
            	  StringWriter writer = new StringWriter();
            	  @Override
            	  public Object handle(Request arg0, Response arg1) {
            	  try {
            		  
                      //Se carga la plantilla
            		  Template plantilla = configuracion.getTemplate("srchPrice.html");
            		  //Cargamos el map, que se va a utilizar para aplicarlo a la plantilla
            		  Map<String, Object> searchMap = new HashMap<String, Object>();
            		  //Procesamos la plantilla
            		  plantilla.process(searchMap, writer);
            	  } catch (Exception e) {
            		  // Si falla, devuelve un 500
            		  halt(500);
            		  e.printStackTrace();
            	  }
            	  return writer;
               }
              });
            
       //HTML para buscar por categoria.     
            Spark.get( new Route("/searchByLocality") {
            	  //El objeto que recogerá la salida
            	  StringWriter writer = new StringWriter();
            	  @Override
            	  public Object handle(Request arg0, Response arg1) {
            	  try {
            		  
                      //Se carga la plantilla
            		  Template plantilla = configuracion.getTemplate("srchLoc.html");
            		  //Cargamos el map, que se va a utilizar para aplicarlo a la plantilla
            		  Map<String, Object> searchMap = new HashMap<String, Object>();
            		  //Procesamos la plantilla
            		  plantilla.process(searchMap, writer);
            	  } catch (Exception e) {
            		  // Si falla, devuelve un 500
            		  halt(500);
            		  e.printStackTrace();
            	  }
            	  return writer;
               }
              });
           
       //Buscar por categoria
            get(new Route("/categoryResult") {
            	@Override
                public Object handle(Request request, Response response) {
            	String atras = "<ul> <li> <a href=\"/buildingSearchMenu\">Volver</a></li></ul>";
        		String cat = request.queryParams("category");
        		if (cat == null) {
        			Base.close();
        			halt(500);
        		}
            	String head = "<head>" + 
                		"<title>Resultado de la busqueda</title>" +
                		" </head>";
            	String msg = "<h1>Inmuebles disponibles para: </h1>";
        		Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
            	Search search = new Search();
            	String list = search.searchByCat(cat);	
 
            	Base.close();
            	return head + msg + cat + list + atras;
            	}
            });
            
       //Buscar por tipo
            get(new Route("/typeResult") {
            	@Override
                public Object handle(Request request, Response response) {
            	String atras = "<ul> <li> <a href=\"/buildingSearchMenu\">Volver</a></li></ul>";
        		String type = request.queryParams("type");
        		if (type == null) {
        			Base.close();
        			halt(500);
        		}
        		String head = "<head>" + 
                		"<title>Resultado de la busqueda</title>" +
                		" </head>";
            	String msg = "<h1>Inmuebles disponibles para: </h1>";
        		Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
            	Search search = new Search();
            	String list = search.searchByType(type);	
 
            	Base.close();
            	return head + msg + type + list + atras;
            	}
            });
            
            //Buscar por precio
            get(new Route("/priceResult") {
            	@Override
                public Object handle(Request request, Response response) {
            	int minPrice = Integer.parseInt(request.queryParams("minPrice"));
            	int maxPrice = Integer.parseInt(request.queryParams("maxPrice"));
            	String atras = "<ul> <li> <a href=\"/buildingSearchMenu\">Volver</a></li></ul>";
            	String head = "<head>" + 
                		"<title>Resultado de la busqueda</title>" +
                		" </head>";
            	String msg = "<h1>Inmuebles disponibles para: </h1>";
        		Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
            	Search search = new Search();
            	String list = search.searchByPrice(minPrice,maxPrice);	
 
            	Base.close();
            	return head + msg + list + atras;
            	}
            });
            
          //Buscar por localidad
            get(new Route("/localityResult") {
            	@Override
                public Object handle(Request request, Response response) {
            	String atras = "<ul> <li> <a href=\"/buildingSearchMenu\">Volver</a></li></ul>";
        		String locality = request.queryParams("locality");
        		String head = "<head>" + 
                		"<title>Resultado de la busqueda</title>" +
                		" </head>";
            	String msg = "<h1>Inmuebles en: </h1>";
        		Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");	
        		Search search = new Search();
        		int id = search.verifLocality(locality);
        		if (Integer.valueOf(id) == null) {
        			Base.rollbackTransaction();
        			Base.close();
        			halt(500);
        		}
            	String list = search.searchByLocality(id);	
 
            	Base.close();
            	return head + msg + locality + list + atras;
            	}
            });
            
            //baja duenio
            
        	post(new Route("/eliminarOwner") {
                @Override
                public Object handle(Request request, Response response) {
                	String head = "<head>" + 
                    		"<title>Duenoo Eliminado</title>" +
                    		" </head>";
                	
                	Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
                	Base.openTransaction();
                	String dni = request.queryParams("owner_dni");
                	Owner owner = Owner.first("owner_dni = ?", dni);
                	if(owner == null){
                    	Base.commitTransaction();
                    	Base.close();
                		return ("Duenio DNI: "+ dni+ " inexistente");
                	}
                	else{
                		ABMOwner Obaja = new ABMOwner();
                		Obaja.Baja(dni);
                		Base.commitTransaction();
                		Base.close();
                		response.status(201); // 201 Created
                		return (head + "Duenio DNI: "+ dni +" borrado exitosamente");
                	}
                }
            });
            
            //HTML para dar de baja duenos.  
            Spark.get( new Route("/bajaOwner") {
        	  //El objeto que recogerá la salida
        	  StringWriter writer = new StringWriter();
        	  @Override
        	  public Object handle(Request arg0, Response arg1) {
        	  try {
        		  
                  //Se carga la plantilla
        		  Template plantilla = configuracion.getTemplate("bajaOwner.html");
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
            
            /*
             * baja BUILDING
             * 
             * 
             */
            
        	post(new Route("/eliminarInmueble") {
                @Override
                public Object handle(Request request, Response response) {
                	String head = "<head>" + 
                    		"<title>Inmueble Eliminado.</title>" +
                    		" </head>";
                	
                	Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
                	Base.openTransaction();
                	String street = request.queryParams("street");
                	String neigh = request.queryParams("neigh");
                	Building buil = Building.first("b_street = ? and neighborhood = ?", street, neigh);
                	if(buil == null){
                    	Base.commitTransaction();
                    	Base.close();
                		return ("Inmueble calle: "+ street+ " y barrio "+neigh +" inexistente");
                	}
                	else{
                		ABMBuilding Bbaja = new ABMBuilding();
                		Bbaja.bajaBuilding(street,neigh);
                		Base.commitTransaction();
                		Base.close();
                		response.status(201); // 201 Created
                		return (head+" Inmueble calle: "+ street+ " y barrio "+neigh+" borrado exitosamente");
                	}
                }
            });
            
            //HTML para dar de baja duenos.  
            Spark.get( new Route("/bajaBuilding") {
        	  //El objeto que recogerá la salida
        	  StringWriter writer = new StringWriter();
        	  @Override
        	  public Object handle(Request arg0, Response arg1) {
        	  try {
        		  
                  //Se carga la plantilla
        		  Template plantilla = configuracion.getTemplate("bajaInmueble.html");
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
            
            /*
             * baja REAL ESTATE
             * 
             * 
             */
            
        	post(new Route("/eliminarRS") {
                @Override
                public Object handle(Request request, Response response) {
                	String head = "<head>" + 
                    		"<title>Inmobiliaria Eliminada.</title>" +
                    		" </head>";
                	
                	Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
                	Base.openTransaction();
                	String name = request.queryParams("name");
                	
                	RealEstate re = RealEstate.first("rs_name = ?", name);
                	if(re == null){
                    	Base.commitTransaction();
                    	Base.close();
                		return ("Inmobiliaria nombre: "+ name+ " inexistente");
                	}
                	else{
                		ABMRealEstate REbaja = new ABMRealEstate();
                		REbaja.Baja(name);
                		Base.commitTransaction();
                		Base.close();
                		response.status(201); // 201 Created
                		return (head+" Inmobiliaria nombre: "+ name+" borrado exitosamente");
                	}
                }
            });
            
            //HTML para dar de baja duenos.  
            Spark.get( new Route("/bajaRealEstate") {
        	  //El objeto que recogerá la salida
        	  StringWriter writer = new StringWriter();
        	  @Override
        	  public Object handle(Request arg0, Response arg1) {
        	  try {
        		  
                  //Se carga la plantilla
        		  Template plantilla = configuracion.getTemplate("bajaRS.html");
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
            
            
            System.out.println( "Hello World!" );
    }
}
