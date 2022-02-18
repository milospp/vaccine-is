package vaccineisemployee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("zajednicko")
public class DependencyConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
