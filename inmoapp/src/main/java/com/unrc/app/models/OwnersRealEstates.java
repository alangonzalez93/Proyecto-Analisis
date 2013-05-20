package src.main.java.com.unrc.app.models;

import org.javalite.activejdbc.Model;

public class OwnersRealEstates extends Model{
	  static{
	      validatePresenceOf("real_estate_id", "owner_id");
	  }
	
}

