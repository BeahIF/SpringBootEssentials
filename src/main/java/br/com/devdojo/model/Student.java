package br.com.devdojo.model;
import javax.persistence.*;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import static java.util.Arrays.asList;

import java.io.Serializable;
import java.util.ArrayList;
import  java.util.List;
import java.util.Objects;
import javax.persistence.MappedSuperclass;
////@author Bea Costa for +praTi on 17/04/2021
@Entity
public class Student extends  AbstractEntity{
//    private int id;
    @NotEmpty
    private String name;
    @NotEmpty
    @Email
    private String email;
//    public static List<Student> studentList;
//    static{
//        studentRepository();
//    }
//    public Student(int id,String name){
//        this.id = id;
//        this.name=name;
//    }
//    public Student(String name){
//        this.name=name;
//
//    }
//    public Student(){
//
//    }
//
//    private static void studentRepository(){
////        studentList = new ArrayList<>(asList(new Student (name:"Deku"), new Student(name:"Todoro")));
//        studentList = new ArrayList<>(asList(new Student(1,"deKu"), new Student(2,"bia")));
//    }

    public String getName(){
        return name;

    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Student student = (Student) o;
//        return id == student.id;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
//
//    public void setId(Integer id){
//        this.id= id;
//    }
//
    public void setName(String name){
        this.name=name;
    }
}
