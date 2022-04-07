package com.lhfioravanso.assemblyvoting.config;

import com.google.common.base.Predicate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private static final String api_package = "com.lhfioravanso.assemblyvoting";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(apis())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private Predicate<RequestHandler> apis() {
        return RequestHandlerSelectors.basePackage(api_package);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(projectName)
                .contact(new Contact("Voting List", "https://softdesign.com.br/",
                        "lucasramos2500@gmail.com"))
                .description(projectDescription)
                .version(projectVersion).build();
    }

    @Value("${info.app.name}")
    String projectName;
    String projectDescription = "API de votação.";
    @Value("${info.app.version:1.0.0}")
    String projectVersion;
}
