package com.unrc.app.models;

import org.javalite.activejdbc.Model;

public class RealEstate extends Model {
  static{
      validatePresenceOf("rs_name","tel");
     }
}
