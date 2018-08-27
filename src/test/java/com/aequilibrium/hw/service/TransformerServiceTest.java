package com.aequilibrium.hw.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.aequilibrium.hw.TestBase;
import com.aequilibrium.hw.constants.TransformerTeam;
import com.aequilibrium.hw.model.FightResultTO;
import com.aequilibrium.hw.model.TransformerTO;

/**
 * data.sql file contains three insert statements.
 * All the tests assume to have a database with 3 records
 * 
 * @author Cristian Constantin
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransformerServiceTest extends TestBase{

	@Autowired
	TransformerService service;

	@Test
	public void testFindAll() {
		List<TransformerTO> findAll = service.findAll();
		assertTrue("Find all transformers failed: ", findAll.size() > 0);
	}

	@Test
	public void testCreateTransformer() {
		TransformerTO savedEl = service.createOrUpdateTransformer(dummyObject);
		assertNotNull(savedEl.getId());
		dummyObject = savedEl;
	}

	@Test(expected = NoSuchElementException.class)
	public void testDeleteTransformer() {
		dummyObject.setId(1l);
		service.deleteTransformer(dummyObject);
		
		TransformerTO transformerTO = service.findById(dummyObject.getId());
		assertNull(transformerTO);
	}
	
	@Test()
	public void testUpdateTransformer() {
		Short modified = 99;
		TransformerTO transformerTO = service.findById(1l);
		assertNotEquals(modified, transformerTO.getRank());
		
		transformerTO.setRank(modified);
		service.createOrUpdateTransformer(transformerTO);
		
		transformerTO = service.findById(1l);
		assertEquals(modified, transformerTO.getRank());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testUpdateMisingTransformer() {
		Short modified = 99;
		TransformerTO transformerTO = service.findById(999l);
		assertNotEquals(modified, transformerTO.getRank());
		
		transformerTO.setRank(modified);
		service.createOrUpdateTransformer(transformerTO);
		
		transformerTO = service.findById(1l);
		assertEquals(modified, transformerTO.getRank());
	}

	@Test
	public void testTransformerFight() {
		List<Long> transformerIds = new ArrayList<Long>();
		transformerIds.add(1l);
		transformerIds.add(2l);
		transformerIds.add(3l);
		
		FightResultTO transformerFight = service.transformerFight(transformerIds);
		
		assertEquals(1,transformerFight.getBattlesNr());
		assertEquals(TransformerTeam.D,transformerFight.getWinningTeam());
		assertEquals("Hubcap",transformerFight.getSurvivors().get(TransformerTeam.A).get(0).getName());
	}

}
