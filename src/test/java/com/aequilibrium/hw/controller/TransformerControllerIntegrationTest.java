package com.aequilibrium.hw.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.aequilibrium.hw.TestBase;
import com.aequilibrium.hw.constants.NavConstants;
import com.aequilibrium.hw.model.TransformerTO;

/**
 * data.sql file contains three insert statements.
 * All the tests assume to have a database with 3 records
 * 
 * @author Cristian Constantin
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransformerControllerIntegrationTest extends TestBase implements NavConstants {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void getAllTransformers() {
		ResponseEntity<TransformerTO[]> responseEntity = restTemplate.getForEntity(ROOT + GET_ALL, TransformerTO[].class);
		TransformerTO[] list = responseEntity.getBody();

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertTrue(list.length > 0);
	}
	
	@Test
	public void testCreateTransformer() {
		Object[] params= new Object[1]; 
		ResponseEntity<TransformerTO> responseEntity = restTemplate.postForEntity(ROOT + CREATE, dummyObject, TransformerTO.class, params);
		TransformerTO el = responseEntity.getBody();

		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertNotNull(el.getId());
	}
}
