package rdv.model;

public enum Status {

	PASSE("passe"), ANNULE("annule"), FUTUR("a venir");
	
	private String label;
	
	private Status(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
	
}
