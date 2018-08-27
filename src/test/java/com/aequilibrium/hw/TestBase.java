package com.aequilibrium.hw;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.aequilibrium.hw.constants.TransformerTeam;
import com.aequilibrium.hw.model.TransformerTO;

@SpringBootTest
public class TestBase {

	protected static TransformerTO dummyObject;

	@BeforeClass
	public static void setUp() throws Exception {
		dummyObject = new TransformerTO();
		short dummy = 1;
		dummyObject.setCourage(dummy);
		dummyObject.setEndurance(dummy);
		dummyObject.setFirepower(dummy);
		dummyObject.setIntelligence(dummy);
		dummyObject.setName("Dummy robot");
		dummyObject.setRank(dummy);
		dummyObject.setSkill(dummy);
		dummyObject.setSpeed(dummy);
		dummyObject.setStrength(dummy);
		dummyObject.setTeam(TransformerTeam.A);
	}
}
