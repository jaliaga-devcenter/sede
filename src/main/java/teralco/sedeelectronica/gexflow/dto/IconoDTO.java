package teralco.sedeelectronica.gexflow.dto;

public class IconoDTO extends AbstractDTO {

	private String nombre;

	private String extension;

	private byte[] fichero;

	private Integer idCategoria;

	public IconoDTO(String nombre, String extension, byte[] fichero) {
		super();
		this.nombre = nombre;
		this.extension = extension;
		this.fichero = fichero;

	}

	public String getNombre() {
		return nombre;
	}

	public String getExtension() {
		return extension;
	}

	public byte[] getFichero() {
		return fichero;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public IconoDTO setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
		return this;
	}

}
