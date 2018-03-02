package teralco.sedeelectronica.model;

public enum Medio {
	DOUE("D.O.U.E."), BOE("B.O.E."), BORM("B.O.R.M.");

	private String text;

	Medio(String pText) {
		this.text = pText;
	}

	public String getText() {
		return this.text;
	}

}
