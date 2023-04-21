package app.core;


import app.core.security.RsaKeyProperties;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
@EnableScheduling
public class CouponSystemProjectNoamApplication {

    public static void main(String[] args) {
        SpringApplication.run(CouponSystemProjectNoamApplication.class, args);
    }

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title("title").version("version").description("description")).addSecurityItem(new SecurityRequirement().addList("my security")).components(new Components().addSecuritySchemes("my security", new SecurityScheme().name("my security").type(SecurityScheme.Type.HTTP).scheme("bearer")));
    }
}
