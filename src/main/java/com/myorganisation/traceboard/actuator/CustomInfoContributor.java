package com.myorganisation.traceboard.actuator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomInfoContributor implements InfoContributor {

    private String applicationName;

    public CustomInfoContributor(@Value("${spring.application.name}") String applicationName) {
        this.applicationName = applicationName;
    }

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, Object> details = new HashMap<>();
        details.put("author", "Abhishek Gupta");
        details.put("github", "https://github.com/AbhishekGupta-developer");
        details.put("name", applicationName);
        details.put("description", "Spring Boot backend project with Actuator");
        details.put("version", "0.0.1");

        builder.withDetails(details);
    }
}
