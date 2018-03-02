package teralco.sedeelectronica.captcha.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import teralco.sedeelectronica.captcha.CaptchaSettings;

@RunWith(MockitoJUnitRunner.class)
public class RecaptchaServiceTest {

	private static final String IP = "192.168.1.1";

	private static final String RECAPTCHA_RESPONSE = "TEST";

	@Mock
	private CaptchaSettings settings;

	@Mock
	private RestTemplateBuilder templateBuilder;

	@SuppressWarnings("rawtypes")
	@Mock
	private ResponseEntity<Map> recaptchaResponseEntity;

	@Mock
	private RestTemplate template;

	@InjectMocks
	private RecaptchaService service;

	@SuppressWarnings("unchecked")
	@Test
	public void testVerifyCaptchaSuccess() {
		// ARRANGE
		Mockito.when(this.templateBuilder.build()).thenReturn(this.template);
		Mockito.when(this.recaptchaResponseEntity.getBody()).thenReturn(getSuccessBody());
		Mockito.when(this.template.postForEntity(Mockito.anyString(), Mockito.anyMap(), Mockito.eq(Map.class),
				Mockito.anyMap())).thenReturn(this.recaptchaResponseEntity);

		// ACT
		String verifyRecaptcha = this.service.verifyRecaptcha(IP, RECAPTCHA_RESPONSE);

		// ASSERT
		Assert.assertEquals("", verifyRecaptcha);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testVerifyCaptchaError() {
		// ARRANGE
		Mockito.when(this.templateBuilder.build()).thenReturn(this.template);
		Mockito.when(this.recaptchaResponseEntity.getBody()).thenReturn(getErrorBody());
		Mockito.when(this.template.postForEntity(Mockito.anyString(), Mockito.anyMap(), Mockito.eq(Map.class),
				Mockito.anyMap())).thenReturn(this.recaptchaResponseEntity);

		// ACT
		String verifyRecaptcha = this.service.verifyRecaptcha(IP, RECAPTCHA_RESPONSE);

		// ASSERT
		Assert.assertEquals("Error en la configuarion de la key secret de google., La key secret de google es incorrecta.", verifyRecaptcha);
	}


	public Map<String, Object> getSuccessBody() {
		Map<String, Object> body = new HashMap<>();
		body.put("success", true);
		return body;
	}
	
	public Map<String, Object> getErrorBody() {
		Map<String, Object> body = new HashMap<>();
		body.put("success", false);
		List<String> errorCodes = new ArrayList<>();
		errorCodes.add("missing-input-secret");
		errorCodes.add("invalid-input-secret");
		body.put("error-codes", errorCodes);
		return body;
	}


}
