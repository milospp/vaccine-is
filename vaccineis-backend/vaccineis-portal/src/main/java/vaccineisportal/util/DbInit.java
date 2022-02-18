package vaccineisportal.util;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@AllArgsConstructor
@Component
public class DbInit {

    @PostConstruct
    private void postConstruct() {


    }
}