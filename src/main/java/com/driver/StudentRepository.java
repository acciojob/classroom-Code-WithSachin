package com.driver;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@Repository
public class StudentRepository {
    HashMap<String, Student>studentDataBase=new HashMap<>();
    HashMap<String, Teacher>teacherDataBase=new HashMap<>();
    HashMap<String, List<String>>pairDatabase=new HashMap<>();

    public void addStudent(Student student){
        studentDataBase.put(student.getName(), student);
    }

    public void addTeacher(Teacher teacher){
        teacherDataBase.put(teacher.getName(), teacher);
    }

    public void addStudentTeacherPair(String studentName, String teacherName){
        List<String> list = new ArrayList<>();
        if (pairDatabase.containsKey(teacherName)) {
            list = pairDatabase.get(teacherName);
            list.add(studentName);
            pairDatabase.put(teacherName, list);
        } else {
            list.add(studentName);
            pairDatabase.put(teacherName, list);
        }
    }

    public Student getStudentByName(String studentName){
        return studentDataBase.get(studentName);
    }

    public Teacher getTeacherByName(String teacherName){
        return teacherDataBase.get(teacherName);
    }

    public List<String> getStudentsByTeacherName(String teacherName){
        return pairDatabase.get(teacherName);
    }

    public List<String> getAllStudents(){
        List<String>list=new ArrayList<>();
        for(String key:studentDataBase.keySet()){
            list.add(studentDataBase.get(key).getName());
        }
        return list;
    }

    public void deleteTeacherByName(String teacherName){

        List<String>list=new ArrayList<>();
        list=pairDatabase.get(teacherName);
        pairDatabase.remove(teacherName);
        teacherDataBase.remove(teacherName);

        for(int i=0;i<list.size();i++){
            studentDataBase.remove(list.get(i));
        }
    }

    public void deleteAllTeachers(){
        for(String teacherName:pairDatabase.keySet()){
            deleteTeacherByName(teacherName);
        }
    }
}
