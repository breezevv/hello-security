package example.hellosecurity.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "ignore")
public class IgnoreRequest {
    private List<String> get = new ArrayList<>();
    private List<String> post = new ArrayList<>();
}
