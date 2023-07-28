package com.api.projeto_music.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration

public class SwaggerConfig {
    @Bean
    public Docket api() {
       return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getApiInfo() {
        ApiInfo apiInfo = new ApiInfoBuilder()
    .title("API Projeto Music")
    .description("API para gerenciamento de musicas.")
    .termsOfServiceUrl("/servico.html")
    .license("Apache License Version 2.0")
    .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
    .version("1.0.0")
    .build();

    return apiInfo;
    }
}
