package barnett.joshua.lunchinator.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableConfigurationProperties({SwaggerProperties.class})
@ConditionalOnClass({Docket.class})
public class SwaggerAutoConfiguration {
    @Autowired
    private SwaggerProperties swaggerProperties;

    public SwaggerAutoConfiguration() {
    }

    @Bean
    public Docket customImplementation() {
        ApiSelectorBuilder apiBuilder = (new Docket(DocumentationType.SWAGGER_2)).select().apis(RequestHandlerSelectors.any());
        String[] var2 = this.swaggerProperties.getDocumentedEndpoints();
        int var3 = var2.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            String endpoint = var2[var4];
            apiBuilder.paths(PathSelectors.regex(endpoint));
        }

        return apiBuilder.build().apiInfo(this.apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(this.swaggerProperties.getApiLongName(),
                this.swaggerProperties.getApiDescription(),
                this.swaggerProperties.getApiVersion(),
                this.swaggerProperties.getTermsOfServiceUrl(),
                this.swaggerProperties.getDeveloperEmail(),
                this.swaggerProperties.getLicense(),
                this.swaggerProperties.getLicenseUrl());
    }

}

