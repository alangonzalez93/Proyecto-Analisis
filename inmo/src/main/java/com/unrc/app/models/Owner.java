package com.unrc.app.models;

import org.javalite.activejdbc.Model;

public class Owner extends Model{
	 static{
	      validatePresenceOf("owner_name","owner_dni");

 }
	}
