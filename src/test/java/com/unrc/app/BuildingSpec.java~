package src.main.java.com.unrc.app;

import src.main.java.com.unrc.app.models.Building;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.javalite.test.jspec.JSpec.the;

public class BuildingSpec {
	
	@Before
	public void before() {
		Base.open("com.mysql.jdbc.Driver","jdbc:mysql://localhost/inmoapp_development","root","");
	}
	
	@After
	public void after() {
		Base.rollbackTransaction();
		Base.close();
	}
	
	@Test
	public void shouldValidateMandatoryFields() {
		
		Building building = new Building();
		
		//Check Errors
		the(building).shouldNotBe("valid");
		the(building.errors().get("type")).shouldBeEqual("value is missing");
		the(building.errors().get("description")).shouldBeEqual("value is missing");
		the(building.errors().get("categorie")).shouldBeEqual("value is missing");
		
		//Set Missing Values
		building.set("type",1,"description","no description","categorie",1);
		
		//All Is Good
		the(building).shouldBe("valid");
		
	}
	
	
}
