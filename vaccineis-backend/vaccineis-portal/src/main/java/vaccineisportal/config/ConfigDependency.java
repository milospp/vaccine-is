package vaccineisportal.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan("zajednicko")
@EnableScheduling
@EnableAsync
public class ConfigDependency {


}
