package teralco.sedeelectronica.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"teralco.sedeelectronica.gexflow", "teralco.sedeelectronica.verifirma", "teralco.sedeelectronica.captcha"})
public class TestApplication {

	public static void main(String[] args) {
		
	}

}