package teralco.sedeelectronica.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public class BaseModel implements Serializable {

	private static final long serialVersionUID = 495969756727150946L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private Long id;

	public Long getId() {
		return this.id;
	}

	public void setId(Long _id) {
		this.id = _id;
	}

	@JsonIgnore
	public boolean isNew() {
		return this.id == null;
	}

}
