package teralco.sedeelectronica.model;

public enum Medio {
	doue("D.O.U.E."), boe("B.O.E."), borm("B.O.R.M.");

	private String text;

	Medio(String _text) {
		this.text = _text;
	}

	public String getText() {
		return this.text;
	}

}
