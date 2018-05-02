package teralco.sedeelectronica.model;

import org.hibernate.validator.constraints.NotEmpty;

public class CSVValidation {
	@NotEmpty(message = "Debe escribir un código CSV.")
	public String csv;

	public String getCsv() {
		return this.csv;
	}

	public void setCsv(String pCsv) {
		this.csv = pCsv;
	}

}
