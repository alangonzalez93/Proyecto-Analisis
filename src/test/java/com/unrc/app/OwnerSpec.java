package src.main.java.com.unrc.app;

import src.main.java.com.unrc.app.models.Owner;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.javalite.test.jspec.JSpec.the;


public class OwnerSpec {

	@Before
	public void before() {
		Base.open("com.mysql.jdbc.Driver","jdbc:mysql://localhost/inmoapp_development","root","root");
	}
	
	@After
	public void after() {
		Base.rollbackTransaction();
		Base.close();
	}
	
	@Test
	public void shouldValidateMandatoryFields() {
		
		Owner owner = new Owner();
		
		//Check Errors
		
		the(owner).shouldNotBe("valid");
		the(owner.errors().get("owner_name")).shouldBeEqual("value is missing");
		
		//Set Missing Values
		owner.set("owner_name","Juan Perez");
		
		//All Is Good
		the(owner).shouldBe("valid");
	}
	
	
	
	
}
