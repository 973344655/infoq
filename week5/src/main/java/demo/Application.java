package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
@ComponentScan("demo.work.work10")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
