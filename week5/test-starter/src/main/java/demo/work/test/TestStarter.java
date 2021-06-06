package demo.work.test;

import demo.work.bean.Klass;
import demo.work.bean.School;
import demo.work.bean.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestStarter implements CommandLineRunner {

    @Autowired
    Student student;
    @Autowired
    Klass klass;
    @Autowired
    School school;
    @Override
    public void run(String... args) throws Exception {

        System.out.println(student);

        System.out.println(klass.getStudents().size());
        school.ding();

    }
}
