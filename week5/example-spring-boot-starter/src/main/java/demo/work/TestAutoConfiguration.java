package demo.work;

import demo.work.bean.Klass;
import demo.work.bean.School;
import demo.work.bean.Student;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 自动配置Student,Klass和School
 */
@Configuration
@EnableConfigurationProperties({Student.class, Klass.class})
@ConditionalOnProperty(prefix = "demo.conf", name = "enable", havingValue = "true", matchIfMissing = true)
public class TestAutoConfiguration {


    private final Student student;
    private final Klass klass;
    public TestAutoConfiguration(Student student, Klass klass){
        this.student = student;
        this.klass = klass;
    }


    @Bean
    public School school(){
        return new School(klass,student);
    }

}
