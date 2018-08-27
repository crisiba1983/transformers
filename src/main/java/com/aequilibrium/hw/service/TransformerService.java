package com.aequilibrium.hw.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aequilibrium.hw.constants.FightEndReason;
import com.aequilibrium.hw.constants.TransformerRankComparator;
import com.aequilibrium.hw.constants.TransformerTeam;
import com.aequilibrium.hw.data.entity.Transformer;
import com.aequilibrium.hw.data.repository.TransformerRepository;
import com.aequilibrium.hw.mapper.TransformerMapper;
import com.aequilibrium.hw.model.FightResultTO;
import com.aequilibrium.hw.model.TransformerTO;

@Service
@Transactional
public class TransformerService {

	@Autowired
	private TransformerRepository transformerRepository;

	@Autowired
	private TransformerMapper transformerMapper;

	public List<TransformerTO> findAll() {
		List<Transformer> entityList = transformerRepository.findAll();
		List<TransformerTO> result = transformerMapper.fromEntityToModel(entityList);

		return result;
	}

	public TransformerTO createOrUpdateTransformer(TransformerTO el) {

		Transformer entity = transformerMapper.fromModelToEntity(el);
		Transformer savedEntity = transformerRepository.save(entity);
		TransformerTO result = transformerMapper.fromEntityToModel(savedEntity);

		return result;
	}

	public void deleteTransformer(TransformerTO el) {
		Transformer entity = transformerMapper.fromModelToEntity(el);
		transformerRepository.delete(entity);
	}

	public TransformerTO findById(long id) {
		Optional<Transformer> findById = transformerRepository.findById(id);
		TransformerTO result = transformerMapper.fromEntityToModel(findById.get());
		return result;
	}

	public FightResultTO transformerFight(List<Long> transformerIds) {
		List<Transformer> entities = transformerRepository.findAllById(transformerIds);
		List<TransformerTO> models = transformerMapper.fromEntityToModel(entities);

		Map<TransformerTeam, List<TransformerTO>> teams = splitRobots(models);

		FightResultTO result = squadsFight(teams.get(TransformerTeam.A), teams.get(TransformerTeam.D));
		return result;
	}

	private Map<TransformerTeam, List<TransformerTO>> splitRobots(List<TransformerTO> models) {
		Map<TransformerTeam, List<TransformerTO>> teams = new HashMap<TransformerTeam, List<TransformerTO>>();
		
		for (TransformerTeam team : TransformerTeam.values()) {
			teams.put(team, new ArrayList<TransformerTO>());
		}

		for (TransformerTO el : models) {
			teams.get(el.getTeam()).add(el);
		}

		for (TransformerTeam team : TransformerTeam.values()) {
			Collections.sort(teams.get(team), new TransformerRankComparator());
		}
		
		return teams;
	}

	private FightResultTO squadsFight(List<TransformerTO> autobots, List<TransformerTO> decepticons) {
		FightResultTO result = new FightResultTO();
		int minSize = autobots.size();

		if (decepticons.size() < minSize) {
			minSize = decepticons.size();
		}

		if (minSize == 0) {
			result.setEndReason(FightEndReason.MISSING_OTHER_TEAM);
			return result;
		}

		int k = 0;
		for (int i = 0; i < minSize; i++) {
			//chiefs battle
			if (TransformerTeam.A.getChief().equals(autobots.get(i).getName()) 
					&& TransformerTeam.D.getChief().equals(decepticons.get(i).getName()) ){
				
				result.setEndReason(FightEndReason.CHIEFS_FIGHT);
				return result;
			}
			
			TransformerTO winner = duel(autobots.get(i), decepticons.get(i));
			if (winner != null) {
				if (winner.getTeam().equals(TransformerTeam.A)) {
					k++;
				} else {
					k--;
				}
				result.increaseBattlesNr();
				result.addSurvivor(winner);
			}
		}

		result.setWinningTeam(null);
		result.setEndReason(FightEndReason.DRAW);
		
		if (k > 0) {
			result.setWinningTeam(TransformerTeam.A);
			
			addSkippedRobots(decepticons, result, minSize, TransformerTeam.A);
		} else if (k < 0) {
			result.setWinningTeam(TransformerTeam.D);
			addSkippedRobots(autobots, result, minSize, TransformerTeam.D);
		}
		result.setEndReason(FightEndReason.NORMAL_CONTEST);
		
		return result;
	}

	private void addSkippedRobots(List<TransformerTO> robots, FightResultTO result, int minSize, TransformerTeam winningTeam) {
		result.removeSurvivorsTeam(winningTeam);
		for (int j=minSize;j<robots.size();j++) {
			result.addSurvivor(robots.get(j));
		}
	}

	private TransformerTO duel(TransformerTO t1, TransformerTO t2) {
		TransformerTO result = null;
		
		result = executeRuleChief(t1);
		if(result!=null) return result;
		
		result = executeRuleChief(t2);
		if(result!=null) return result;
		
		result = execRuleCourageStrength(t1,t2);
		if(result!=null) return result;
		
		result = execRuleCourageStrength(t2,t1);
		if(result!=null) return result;
		
		result = execRuleSkill(t1,t2);
		if(result!=null) return result;
		
		result = execRuleSkill(t2,t1);
		if(result!=null) return result;
		
		result = execRuleOverallRating(t1,t2);
			
		return result;
	}
	
	private TransformerTO executeRuleChief(TransformerTO t) {
		if (t.getTeam().getChief().equals(t.getName()) ) {
			return t;
		}
		
		return null;
	}

	private TransformerTO execRuleCourageStrength(TransformerTO t1, TransformerTO t2) {
		if (t1.getCourage()-t2.getCourage() >=4 && t1.getStrength()-t2.getStrength()>=3) {
			return t1;
		}
		
		return null;
	}
	
	private TransformerTO execRuleSkill(TransformerTO t1, TransformerTO t2) {
		if (t1.getSkill()-t2.getSkill()>=3) {
			return t1;
		}
		
		return null;
	}
	
	private TransformerTO execRuleOverallRating(TransformerTO t1, TransformerTO t2) {
		if (t1.getOverallRating() > t2.getOverallRating()) {
			return t1;
		}
		
		if (t2.getOverallRating() > t1.getOverallRating()) {
			return t2;
		}
		
		return null;
	}
}
