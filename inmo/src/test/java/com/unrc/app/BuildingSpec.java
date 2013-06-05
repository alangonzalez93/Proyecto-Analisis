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
		
	//	the(building).shouldNotBe("valid");
	//	the(building.errors().get("id_locality"));
		the(building).shouldNotBe("valid");
		//the(building.errors().get("id_owner"));
		the(building).shouldNotBe("valid");
		the(building.errors().get("b_street")).shouldBeEqual("value is missing");
		the(building.errors().get("neighborhood")).shouldBeEqual("value is missing");
		the(building).shouldNotBe("valid");
		the(building.errors().get("type")).shouldBeEqual("value is missing");
		the(building.errors().get("description")).shouldBeEqual("value is missing");
		the(building.errors().get("category")).shouldBeEqual("value is missing");
		//Check Errors
		building.set("type",7);
		the(building).shouldNotBe("valid");
		building.set("type",0);
		the(building).shouldNotBe("valid");
		
		//Check Errors
		building.set("category",0);
		the(building).shouldNotBe("valid");
		building.set("category",3);
		the(building).shouldNotBe("valid");
		
		//Check Errors
		building.set("category",0);
		the(building).shouldNotBe("valid");
		building.set("category",3);
		the(building).shouldNotBe("valid");

		
		//Set Missing Values 
	/*	Locality l = Locality.create("name", "rio cuarto");
		//l.set("name", "las higueras");
		l.saveIt();
		l.add(building);
		*/
		//Set Missing Values
	/*	Owner owner = new Owner();
		owner.set("owner_name", "John Doe", "owner_mail", "abc@abc.com","owner_neighborhood", "A", "owner_street", "B", "owner_dni", "123456");
		owner.saveIt();
		owner.add(building);
		*/
		//Set Missing Values
		building.set("b_street","fake street 123");
		building.set("neighborhood","fake neighborhood");
		
		//Set Missing Values
		building.set("type",1,"description","no description","category",1);
		
		//Set Right Values
		building.set("type",1);
		
		//Set Right Values
		building.set("category",1);
		
	
		
		//All Is Good
		the(building).shouldBe("valid");		
	}
	
	/*
	@Test
	public void shouldValidateOwner(){
		
		Building building = new Building();
		
		//Check Errors
		the(building).shouldNotBe("valid");
		the(building.errors().get("id_owner")).shouldBeEqual("value is missing");
		
		//Set Missing Values
		Owner owner = new Owner();
		owner.set("owner_name", "John Doe", "owner_mail", "abc@abc.com","owner_neighborhood", "A", "owner_street", "B", "owner_dni", "123456");
		owner.saveIt();
		owner.add(building);
		
		//All Is Good
		the(building).shouldBe("valid");		
	}
	
	@Test
	public void shouldValidateAdress() {
		
		Building building = new Building();
		
		//Check Errors
		the(building).shouldNotBe("valid");
		the(building.errors().get("b_street")).shouldBeEqual("value is missing");
		the(building.errors().get("neighborhood")).shouldBeEqual("value is missing");
		
		//Set Missing Values
		building.set("b_street","fake street 123");
		building.set("neighborhood","fake neighborhood");
		
		//All Is Good
		the(building).shouldBe("valid");
	}
	
	@Test
	public void shouldValidateMandatoryFields() {
		
		Building building = new Building();
		
		//Check Errors
		the(building).shouldNotBe("valid");
		the(building.errors().get("type")).shouldBeEqual("value is missing");
		the(building.errors().get("description")).shouldBeEqual("value is missing");
		the(building.errors().get("category")).shouldBeEqual("value is missing");
		
		//Set Missing Values
		building.set("type",1,"description","no description","category",1);
		
		//All Is Good
		the(building).shouldBe("valid");
	}
	
	@Test
	public void shouldValidateTypeRange() {
		
		Building building = new Building();

		//Check Errors
		building.set("type",7);
		the(building).shouldNotBe("valid");
		building.set("type",0);
		the(building).shouldNotBe("valid");
		
		//Set Right Values
		building.set("type",1);
		
		//All Is Good
		the(building).shouldBe("valid");
	}
	
	@Test
	public void shouldValidateCategoryRange() {
		
		Building building = new Building();
		
		//Check Errors
		building.set("category",0);
		the(building).shouldNotBe("valid");
		building.set("category",3);
		the(building).shouldNotBe("valid");
		
		//Set Right Values
		building.set("category",1);
		
		//All Is Good
		the(building).shouldBe("valid");
	}
	*/
	
}