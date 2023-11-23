package com.example.learningmanagementsystem.Service;

import com.example.learningmanagementsystem.Model.Courses;
import com.example.learningmanagementsystem.Model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Data
@AllArgsConstructor
@Service
public class CourseService {

    //Arrayes.
    ArrayList<Courses>courses=new ArrayList<>();

    //Get
    public ArrayList<Courses> getCoursess (){
        return courses;
    }


    //Post
    public void addCourse(Courses cours){
courses.add(cours);
    }


    //Update.
    public boolean updateCourse(String id,Courses updateCourse){
        for (int i = 0; i <courses.size() ; i++) {
            if (courses.get(i).getCourseID().equals(id)){
            courses.set(i,updateCourse);
            return true;
            }
        }
    return  false;
    }




    //Delete
    public boolean deleteCourse(String id){
        for (int i = 0; i <courses.size() ; i++) {
            if (courses.get(i).getCourseID().equals(id)){
                courses.remove(i);
                return true;
            }
        }
        return false;
        }


        //----------------------------------------------
//
//    public boolean removeStudentFromCourse(String studentId, String courseId) {
//        Optional<Student> studentOptional = students.stream()
//                .filter(student -> student.getId().equals(studentId))
//                .findFirst();
//
//        Optional<Courses> courseOptional = courses.stream()
//                .filter(course -> course.getId().equals(courseId))
//                .findFirst();
//
//        if (studentOptional.isPresent() && courseOptional.isPresent()) {
//            Student student = studentOptional.get();
//            Courses course = courseOptional.get();
//            student.getCourses().remove(course);
//            course.getStudents().remove(student);
//            return true;
//        }
//        return false;
//    }


}
