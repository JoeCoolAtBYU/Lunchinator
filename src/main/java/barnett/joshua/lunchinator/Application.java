package barnett.joshua.lunchinator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

    private ApiInfo apiInfo(){
        return new ApiInfo("Lunchinator", "this is my interview project", "0.0.1", "Terms", new Contact("Joshua Barnett", "", "joecoolatbyu@hotmail.com"),
                "License of API", "API License URL");
    }
}