package teralco.sedeelectronica.exception;

public enum ExceptionType {
	UNEXPECTED("UNEXPECTED_ERROR", 400);
	
	private String key;
	private Integer status;

	ExceptionType(String pKey, Integer pStatus) {
		this.key = pKey;
		this.status = pStatus;
	}

	public String getKey() {
		return this.key;
	}
	
	public Integer getStatus() {
		return this.status;
	}

}
