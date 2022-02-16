package vaccineisportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication(scanBasePackages = {"zajednicko.repository"})
@SpringBootApplication
public class VaccineisPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(VaccineisPortalApplication.class, args);
	}

}
