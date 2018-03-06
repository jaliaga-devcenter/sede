package teralco.sedeelectronica.gexflow.dto;

import org.apache.tomcat.util.codec.binary.Base64;

public class IconoDTO extends AbstractDTO {

	private String nombre;

	private String extension;

	private byte[] fichero;

	private String base64encoded;

	private Integer idCategoria;

	public IconoDTO(String _nombre, String _extension, byte[] _fichero) {
		super();
		this.nombre = _nombre;
		this.extension = _extension;
		this.fichero = _fichero;
		this.base64encoded = Base64.encodeBase64String(this.fichero);
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getExtension() {
		return this.extension;
	}

	public byte[] getFichero() {
		return this.fichero;
	}

	public Integer getIdCategoria() {
		return this.idCategoria;
	}

	public IconoDTO setIdCategoria(Integer _idCategoria) {
		this.idCategoria = _idCategoria;
		return this;
	}

	public String getBase64encoded() {
		return this.base64encoded;
	}


}
