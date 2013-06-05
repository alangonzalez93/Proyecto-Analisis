package com.unrc.app;

import org.junit.ClassRule;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
@RunWith(Suite.class)
@Suite.SuiteClasses( {InmoTest.class, LocalitySpec.class, OwnerSpec.class, RealEstateSpec.class, BuildingSpec.class} )
public class TestSuite {
}