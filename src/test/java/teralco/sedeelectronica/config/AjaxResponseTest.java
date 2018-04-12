package teralco.sedeelectronica.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.junit.Test;

public class AjaxResponseTest {
	@Test
	public void AjaxResponse() {
		AjaxResponseBody response = new AjaxResponseBody();
		response.setData(null);
		response.setMsg("sucess");
		response.setErrorMessageList(new ArrayList<String>());

		assertThat(response.getMsg()).isEqualTo("sucess");
		assertNull(response.getData());
		assertThat(response.getErrorMessageList().size()).isEqualTo(0);
	}

	@Test
	public void AjaxResponseBuild() {
		AjaxResponseBody response = new AjaxResponseBody("sucess", null);

		assertThat(response.getMsg()).isEqualTo("sucess");
		assertNull(response.getData());
	}
}
