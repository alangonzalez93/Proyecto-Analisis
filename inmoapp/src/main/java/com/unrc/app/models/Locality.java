package src.main.java.com.unrc.app.models;

import org.javalite.activejdbc.Model;

public class Locality extends Model {
  static{
      validatePresenceOf("name");
  }
}
