package teralco.sedeelectronica.captcha;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "google.recaptcha.key")
public class CaptchaSettings {

	private String site;
	private String secret;

	public String getSite() {

		return this.site;
	}

	public void setSite(String pSite) {
		this.site = pSite;
	}

	public String getSecret() {
		return this.secret;
	}

	public void setSecret(String pSecret) {
		this.secret = pSecret;
	}
}
