package rdv.model;

public enum Jour {
	L("lundi"), MA("mardi"), ME("mercredi"), J("jeudi"), V("vendredi"), S("samedi"), D("dimanche");

	private String label;

	private Jour(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
