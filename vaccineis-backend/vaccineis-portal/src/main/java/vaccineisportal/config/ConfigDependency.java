package vaccineisportal.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("zajednicko")
//@ComponentScan("zajednicko.repository")
//@EntityScan("zajednicko.repository")
public class ConfigDependency {
}
