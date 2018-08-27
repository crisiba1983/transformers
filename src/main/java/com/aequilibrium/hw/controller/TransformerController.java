package com.aequilibrium.hw.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aequilibrium.hw.constants.NavConstants;
import com.aequilibrium.hw.model.FightResultTO;
import com.aequilibrium.hw.model.TransformerTO;
import com.aequilibrium.hw.service.TransformerService;

/**
 * 
 * @author Cristian Constantin
 *
 */
@RestController()
@RequestMapping(value = NavConstants.ROOT)
public class TransformerController implements NavConstants {

	private static final Logger logger = LoggerFactory.getLogger(TransformerController.class);

	@Autowired
	private TransformerService transformerService;

	@RequestMapping(value = GET_ALL, method = RequestMethod.GET)
	public ResponseEntity<List<TransformerTO>> getAllTransformes() {
		logger.info("Start getAllTransformes...");

		List<TransformerTO> transformerTOs = transformerService.findAll();
		return new ResponseEntity<List<TransformerTO>>(transformerTOs, HttpStatus.OK);
	}

	@RequestMapping(value = CREATE, method = RequestMethod.POST)
	public ResponseEntity<TransformerTO> createTransformer(@RequestBody TransformerTO el) {
		logger.info("Start createTransformer...");
		TransformerTO transformerTO = transformerService.createOrUpdateTransformer(el);

		ResponseEntity<TransformerTO> responseEntity = new ResponseEntity<TransformerTO>(transformerTO,
				HttpStatus.CREATED);
		return responseEntity;
	}

	@RequestMapping(value = UPDATE, method = RequestMethod.POST)
	public ResponseEntity<TransformerTO> updateTransformer(@RequestBody TransformerTO el) {
		logger.info("Start updateTransformer...");

		TransformerTO transformerTO = null;
		ResponseEntity<TransformerTO> responseEntity = null;
		try {
			transformerTO = transformerService.createOrUpdateTransformer(el);
		} catch (NoSuchElementException nsex) {
			responseEntity = new ResponseEntity<TransformerTO>(HttpStatus.NOT_FOUND);
		}

		responseEntity = new ResponseEntity<TransformerTO>(transformerTO, HttpStatus.OK);
		return responseEntity;
	}

	@RequestMapping(value = DELETE, method = RequestMethod.PUT)
	public ResponseEntity<Void> deleteTransformer(@PathVariable("id") long elId) {
		logger.info("Start deleteTransformer...");

		ResponseEntity<Void> responseEntity = null;
		try {
			TransformerTO el = new TransformerTO();
			el.setId(elId);
			transformerService.deleteTransformer(el);
		} catch (NoSuchElementException nsex) {
			responseEntity = new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		responseEntity = new ResponseEntity<Void>(HttpStatus.OK);
		return responseEntity;
	}
	
	@RequestMapping(value = BATTLE, method = RequestMethod.GET)
	public ResponseEntity<FightResultTO> battle(@RequestBody List<Long> transformersIds) {
		logger.info("Start fight...");

		FightResultTO fight = null;
		ResponseEntity<FightResultTO> responseEntity = null;
		try {
			fight = transformerService.transformerFight(transformersIds);
		} catch (NoSuchElementException nsex) {
			responseEntity = new ResponseEntity<FightResultTO>(HttpStatus.NOT_FOUND);
		}

		responseEntity = new ResponseEntity<FightResultTO>(fight, HttpStatus.OK);
		return responseEntity;
	}
}
