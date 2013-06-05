package com.unrc.app;

import com.unrc.app.models.Locality;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.javalite.test.jspec.JSpec.the;

public class LocalitySpec {

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
	public void shouldValidateMandatoryFields() {
		
		Locality locality = new Locality();
		
		//Check Errors
		the(locality).shouldNotBe("valid");
		the(locality.errors().get("name")).shouldBeEqual("value is missing");
		
		//Set Missing Values
		locality.set("name","fake locality");
		
		//All Is Good
		the(locality).shouldBe("valid");
		
	}
}
