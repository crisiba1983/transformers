package com.aequilibrium.hw.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.aequilibrium.hw.constants.TransformerTeam;

@Entity
public class Transformer{
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TransformerTeam team;

	@Column(nullable = false)
	private Short strength;

	@Column(nullable = false)
	private Short intelligence;

	@Column(nullable = false)
	private Short speed;
	
	@Column(nullable = false)
	private Short endurance;
	
	@Column(nullable = false)
	private Short rank;
	
	@Column(nullable = false)
	private Short courage;
	
	@Column(nullable = false)
	private Short firepower;
	
	@Column(nullable = false)
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
}
