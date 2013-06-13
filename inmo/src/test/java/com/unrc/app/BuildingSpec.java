package com.unrc.app;

import com.unrc.app.models.Building;
import com.unrc.app.models.Locality;
import com.unrc.app.models.Owner;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.javalite.test.jspec.JSpec.the;

public class BuildingSpec {
	
	@Before
	public void before() {
		Base.open("com.mysql.jdbc.Driver","jdbc:mysql://localhost/inmoapp_development","root","root");
		Base.openTransaction();
	}
	
	@After
	public void after() {
		Base.rollbackTransaction();
		Base.close();
	}

	@Test
	public void shouldValidateLocality() {
		
		Building building = new Building();
		
		//Check Errors
		
		//the(building).shouldNotBe("valid");
		//the(building.errors().get("id_locality")).shouldBeNull();
		//the(building).shouldNotBe("valid");
		//the(building.errors().get("id_owner")).shouldBeNull();
		the(building).shouldNotBe("valid");
		the(building.errors().get("b_street")).shouldBeEqual("value is missing");
		the(building.errors().get("neighborhood")).shouldBeEqual("value is missing");
		the(building).shouldNotBe("valid");
		the(building.errors().get("type")).shouldBeEqual("value is missing");
		the(building.errors().get("description")).shouldBeEqual("value is missing");
		the(building.errors().get("category")).shouldBeEqual("value is missing");
		
		
		//Set Missing Values 
		/*Locality l = Locality.create("name", "cabrera");
		l.saveIt();
		Locality idloc = Locality.first("name = ?", "cabrera");
		int id = idloc.getInteger("id");
		building.set("locality_id", id);
		
		//Set Missing Values
		Owner owner = new Owner();
		owner.set("owner_name", "John Doe", "owner_mail", "abc@abc.com","owner_neighborhood", "A", "owner_street", "B", "owner_dni", "123456");
		owner.saveIt();
		Owner ow = Owner.first("owner_dni = ?", "123456");
		int idowner = ow.getInteger("id");
		building.set("owner_id", idowner);
		*/
		
		//Set Missing Values
		building.set("b_street","fake street 123");
		building.set("neighborhood","fake neighborhood");
		
		//Set Missing Values
		building.set("type","casa","description","no description","category","venta");
		
		
		
		//All Is Good
		the(building).shouldBe("valid");		
	}
	

	
}