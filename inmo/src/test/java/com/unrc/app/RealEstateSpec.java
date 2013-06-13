package com.unrc.app;

import com.unrc.app.models.Locality;
import com.unrc.app.models.RealEstate;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.javalite.test.jspec.JSpec.the;


public class RealEstateSpec {

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
		
		RealEstate rs = new RealEstate();
		
		
		//Check Errors
		the(rs).shouldNotBe("valid");
		the(rs.errors().get("rs_name")).shouldBeEqual("value is missing");
		the(rs).shouldNotBe("valid");
		the(rs.errors().get("tel")).shouldBeEqual("value is missing");
		the(rs).shouldNotBe("valid");
		the(rs.errors().get("rs_neighborhood")).shouldBeEqual("value is missing");
		the(rs).shouldNotBe("valid");
		the(rs.errors().get("rs_street")).shouldBeEqual("value is missing");
		//the(rs).shouldNotBe("valid");
		//the(rs.errors().get("locality_id")).shouldBeEqual("value is missing");
		
		//Set Missing Values
		rs.set("rs_name", "default name");
		the(rs).shouldNotBe("valid");
		rs.set("tel","12345");
		the(rs).shouldNotBe("valid");
		rs.set("rs_neighborhood", "barrio");
		the(rs).shouldNotBe("valid");
		rs.set("rs_street", "calle");
		//the(rs).shouldNotBe("valid");
		//Locality l = Locality.create("name", "carnerillo");
		//l.saveIt();
		//Locality locality = Locality.first("name = ?", "carnerillo");
		//int idloc = locality.getInteger("id");
		//rs.set("locality_id", idloc);
		
		//All Is Good
		the(rs).shouldBe("valid");
	}
}
