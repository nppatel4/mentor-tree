package com.solstice.mentorandtree.mentortree.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfo("MentorTree", "MentorAPI", "1.0",
                        null, null, null, null, Collections.emptyList()))
                .tags(new Tag("mentorTree", "Mentor Tree Service"))
                .useDefaultResponseMessages(false)
                .select().paths(regex("/mentor-trees.*")).build();
    }

}
