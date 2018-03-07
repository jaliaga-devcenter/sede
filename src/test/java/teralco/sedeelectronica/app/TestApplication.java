package teralco.sedeelectronica.app;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("teralco.sedeelectronica.model")
@EnableJpaRepositories("teralco.sedeelectronica.repository")
@ComponentScan(basePackages = { "teralco.sedeelectronica.gexflow", "teralco.sedeelectronica.verifirma" })
public class TestApplication {

	public static void main(String[] args) {

	}

}