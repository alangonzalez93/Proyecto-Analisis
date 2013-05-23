package com.unrc.app;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.unrc.app.models.RealEstate;

import static org.javalite.test.jspec.JSpec.the;
public class RealEstateSpec {
	
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
		
		RealEstate rs = new RealEstate();
		
		//Check Errors
		the(rs).shouldNotBe("valid");
		the(rs.errors().get("rs_name")).shouldBeEqual("value is missing");
		
		//Set Missing Values
		rs.set("rs_name","No name");
		
		//All Is Good
		the(rs).shouldBe("valid");
	}

}
