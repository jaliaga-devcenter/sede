package teralco.sedeelectronica.config;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class FlywayConfig {

	@Bean
	@Profile("dev")
	public Flyway flyway(DataSource theDataSource) {
		Flyway flyway = new Flyway();
		flyway.setDataSource(theDataSource);
		flyway.setLocations("classpath:db/migration/dev");
		flyway.setSchemas("sede");
		/* persist data base */
		// flyway.clean();
		flyway.repair();
		flyway.migrate();

		return flyway;
	}

	@Bean
	@Profile("test")
	public FlywayMigrationStrategy cleanMigrateStrategy() {
		FlywayMigrationStrategy strategy = new FlywayMigrationStrategy() {
			@Override
			public void migrate(Flyway flyway) {
				flyway.setLocations("classpath:db/migration/test");
				flyway.setSchemas("sede");
			}
		};

		return strategy;
	}

}
