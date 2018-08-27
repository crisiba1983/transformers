package com.aequilibrium.hw.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aequilibrium.hw.constants.FightEndReason;
import com.aequilibrium.hw.constants.TransformerTeam;

public class FightResultTO {
	
	private TransformerTeam winningTeam;
	private int battlesNr;
	private Map<TransformerTeam, List<TransformerTO>> survivors = new HashMap<TransformerTeam, List<TransformerTO>>();
	private FightEndReason endReason;
	
	
	public FightResultTO() {
		init();
	}

	private void init() {
		for (TransformerTeam team:TransformerTeam.values()) {
			survivors.put(team, new ArrayList<TransformerTO>());
		}
	}

	public TransformerTeam getWinningTeam() {
		return winningTeam;
	}

	public void setWinningTeam(TransformerTeam winningTeam) {
		this.winningTeam = winningTeam;
	}

	public int getBattlesNr() {
		return battlesNr;
	}

	public void increaseBattlesNr() {
		this.battlesNr++;
	}

	public FightEndReason getEndReason() {
		return endReason;
	}

	public void setEndReason(FightEndReason endReason) {
		this.endReason = endReason;
	}
	
	public void addSurvivor(TransformerTO survivor) {
		this.survivors.get(survivor.getTeam()).add(survivor);
	}

	public Map<TransformerTeam, List<TransformerTO>> getSurvivors() {
		return Collections.unmodifiableMap(survivors);
	}
	
	public void removeSurvivorsTeam(TransformerTeam team) {
		this.survivors.remove(team);
	}
}