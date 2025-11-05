package Sumurduc.Alexandru.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI steamCloneOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Steam Clone API")
                        .version("0.3.0")
                        .description("REST API pentru gestionarea utilizatorilor și dezvoltatorilor din SteamClone."));
    }
}