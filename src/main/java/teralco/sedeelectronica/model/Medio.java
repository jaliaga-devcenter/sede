package teralco.sedeelectronica.model;

public enum Medio {
	doue("D.O.U.E."), boe("B.O.E."), borm("B.O.R.M.");

	private String text;

	Medio(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

}
