package teralco.sedeelectronica.exception;

public enum ExceptionType {
	UNEXPECTED("UNEXPECTED_ERROR", 500),
	THIRD_PARTY_SERVICE_ERROR("UNEXPECTED_ERROR", 500);
	
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
