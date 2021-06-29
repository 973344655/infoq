package demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@MapperScan("demo.work.work2.mapper")
@ComponentScan(excludeFilters = {
        @ComponentScan.Filter(type = FilterType.REGEX,pattern = "demo.work.work9.*")
})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
