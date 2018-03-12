package teralco.sedeelectronica.exception;

public class SedeElectronicaException extends RuntimeException {

	private static final long serialVersionUID = 5486853207074617140L;
	public static final String UNEXPECTED_ERROR = "UNEXPECTED_ERROR";

	public SedeElectronicaException(ExceptionType type, Exception e) {
		super(type.getKey(), e);
	}

	public SedeElectronicaException(ExceptionType type) {
		super(type.getKey());
	}

}
