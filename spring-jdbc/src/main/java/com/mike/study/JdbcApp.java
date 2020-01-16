package com.mike.study;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class JdbcApp {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbcNormal.xml");

        StudentImpl stImpl = (StudentImpl) ctx.getBean("studentImpl");
        System.out.println("新建student数据");
        stImpl.create("Mike", 20);
        stImpl.create("Cece", 18);
        stImpl.create("JDBC", 99);
        System.out.println("================student数据================");
        List<Student> students = stImpl.listStudents();
        for (Student student : students) {
            System.out.println("id=" + student.getId() + ",name=" + student.getName() + ",age" + student.getAge());
        }
        System.out.println("以下更新id=2的student数据");
        stImpl.update(2, 15);
        System.out.println("================以下所有student数据================");
        students = stImpl.listStudents();
        for (Student student : students) {
            System.out.println("id=" + student.getId() + ",name=" + student.getName() + ",age" + student.getAge());
        }

    }

}
