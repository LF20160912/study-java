package com.mike.study;

import javax.sql.DataSource;
import java.util.List;

public class StudentImpl implements StudentDAO {
    private DataSource dataSource;
    private JdbcTemplatelate jdbcTemplateObj;


    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
        this.jdbcTemplateObj = new JdbcTemplate(ds);
    }


    public void create(String name, Integer age) {
        String sqlStr = "insert into Student (name,age) values(?,?)";
        jdbcTemplateObj.update(sqlStr, name, age);
        System.out.println("Created Record Name = " + name + " Age = " + age);
    }


    public Student getStudent(Integer id) {
        String sqlStr = "select id,name,age frome Student where id=?";
        Student st = jdbcTemplateObj.queryForObject(sqlStr, new Object[]{id}, new StudentMapper());
        return st;
    }


    public List<Student> listStudents() {
        String sqlStr = "select * from Student";
        List<Student> students = jdbcTemplateObj.query(sqlStr, new StudentMapper());

        return students;
    }


    public void delect(Integer id) {
        String sqlStr = "delect from Student where id=?";
        jdbcTemplateObj.update(sqlStr, id);
        System.out.println("Deleted Record with ID = " + id);
    }


    public void update(Integer id, Integer age) {
        String sqlStr = "update Student set age=? where id=?";
        jdbcTemplateObj.update(sqlStr, age, id);
        System.out.println("Updated Record with ID = " + id);
    }

}
