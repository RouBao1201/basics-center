package com.roubao.web.swagger.autoconfiguration;

import com.roubao.web.swagger.properties.SwaggerProperties;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Swagger自动装配类
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/5/27
 **/
@Slf4j
@Configuration
@EnableSwagger2
@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerAutoconfiguration {

    private static final String BANNER_I_SWAGGER_AUTO =
            "   ___     ___ __      __ ___   ___   ___  ___  ___      ___  _   _  _____   ___  " + System.lineSeparator() +
            "  |_ _|   / __|\\ \\    / //   \\ / __| / __|| __|| _ \\    /   \\| | | ||_   _| / _ \\ " + System.lineSeparator() +
            "   | |    \\__ \\ \\ \\/\\/ / | - || (_ || (_ || _| |   /    | - || |_| |  | |  | (_) |" + System.lineSeparator() +
            "  |___|   |___/  \\_/\\_/  |_|_| \\___| \\___||___||_|_\\    |_|_| \\___/  _|_|_  \\___/ ";

    private final SwaggerProperties swaggerProperties;

    public SwaggerAutoconfiguration(SwaggerProperties swaggerProperties) {
        this.swaggerProperties = swaggerProperties;
    }

    @Bean("docket")
    @ConditionalOnMissingBean(Docket.class)
    public Docket docket() {
        log.info(System.lineSeparator() + BANNER_I_SWAGGER_AUTO + System.lineSeparator());
        log.info("SwaggerAutoconfiguration ==> Start custom autoConfiguration [Docket] bean.");
        log.info("SwaggerAutoconfiguration ==> SwaggerProperties:[{}].", swaggerProperties);
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerProperties.getEnable())
                .groupName(swaggerProperties.getGroupName())
                .protocols(newProtocolsSet())
                .pathMapping(swaggerProperties.getPathMapping())
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swaggerProperties.getTitle())
                .version(swaggerProperties.getVersion())
                .description(swaggerProperties.getDescription())
                .termsOfServiceUrl(swaggerProperties.getTermsOfServiceUrl())
                .build();
    }

    private Set<String> newProtocolsSet() {
        String[] splitProtocols = swaggerProperties.getProtocols().split(",");
        if (splitProtocols.length > 0) {
            return new LinkedHashSet<>(Arrays.asList(splitProtocols));
        }
        return null;
    }
}
