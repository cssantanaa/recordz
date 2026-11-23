
package com.cssantana.recordz.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI recordzOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Recordz API")
                        .description(
                                "API REST para gerenciamento de músicas, " +
                                        "álbuns, avaliações, usuários e diário musical."
                        )
                        .version("1.0.0")
                );
    }
}
