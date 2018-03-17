package barnett.joshua.lunchinator;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Application extends SpringBootServletInitializer {

   /**
    * Allows the app to run as a web app in an executable fat jar.
    *
    * @param args
    */
    public static void main(String[] args) {
        SpringApplication app = new SpringApplicationBuilder(Application.class).bannerMode(Mode.OFF).build();
        app.run(args);
    }

    /**
     * Allows the app to run as a web application in a war file.
     */
    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
       application.sources(Application.class);
       application.bannerMode(Mode.OFF);
       return super.configure(application);
    }
}