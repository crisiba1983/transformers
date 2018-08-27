package com.aequilibrium.hw.model;

import com.aequilibrium.hw.constants.TransformerTeam;

public class TransformerTO {
	
	private Long id;
	private String name;
	private TransformerTeam team;
	private Short strength;
	private Short intelligence;
	private Short speed;
	private Short endurance;
	private Short rank;
	private Short courage;
	private Short firepower;
	private Short skill;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TransformerTeam getTeam() {
		return team;
	}

	public void setTeam(TransformerTeam team) {
		this.team = team;
	}

	public Short getStrength() {
		return strength;
	}

	public void setStrength(Short strength) {
		this.strength = strength;
	}

	public Short getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(Short intelligence) {
		this.intelligence = intelligence;
	}

	public Short getSpeed() {
		return speed;
	}

	public void setSpeed(Short speed) {
		this.speed = speed;
	}

	public Short getEndurance() {
		return endurance;
	}

	public void setEndurance(Short endurance) {
		this.endurance = endurance;
	}

	public Short getRank() {
		return rank;
	}

	public void setRank(Short rank) {
		this.rank = rank;
	}

	public Short getCourage() {
		return courage;
	}

	public void setCourage(Short courage) {
		this.courage = courage;
	}

	public Short getFirepower() {
		return firepower;
	}

	public void setFirepower(Short firepower) {
		this.firepower = firepower;
	}

	public Short getSkill() {
		return skill;
	}

	public void setSkill(Short skill) {
		this.skill = skill;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Long getOverallRating() {
		return new Long(this.getStrength() + this.getIntelligence() + this.getSpeed() + this.getEndurance() + this.getFirepower());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransformerTO other = (TransformerTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
