package com.aequilibrium.hw.constants;

public enum TransformerTeam {

	A("Autobots", "Optimus Prime"), D("Decepticons", "Predaking");

	private String label;
	private String chief;

	private TransformerTeam(String label, String chief) {
		this.label = label;
		this.chief = chief;
	}

	public String getLabel() {
		return this.label;
	}
	
	public String getChief() {
		return this.chief;
	}
}
