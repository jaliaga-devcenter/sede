package teralco.sedeelectronica.captcha;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "google.recaptcha.key")
public class CaptchaSettings {

	private String site;
	private String secret;

	public CaptchaSettings() {
	}

	public String getSite() {

		return this.site;
	}

	public void setSite(String _site) {
		this.site = _site;
	}

	public String getSecret() {
		return this.secret;
	}

	public void setSecret(String _secret) {
		this.secret = _secret;
	}
}
