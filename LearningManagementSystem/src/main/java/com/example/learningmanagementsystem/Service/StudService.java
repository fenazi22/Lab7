package com.example.learningmanagementsystem.Service;

import com.example.learningmanagementsystem.Model.Courses;
import com.example.learningmanagementsystem.Model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Data
@AllArgsConstructor
@Service
public class StudService {
    ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Courses> courses = new ArrayList<>();


    //Get
    public ArrayList<Student> getStudent() {
        return students;
    }


    //add
    public void addStud(Student student) {
        students.add(student);
    }

    //update
    public boolean updateSud(String id, Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudId().equals(id)) {
                students.set(i, student);
                return true;
            }
        }
        return false;
    }

    //Delete
public boolean deleteStud(String id){
    for (int i = 0; i <students.size() ; i++) {
        if (students.get(i).getStudId().equals(id)){
            students.remove(i);
            return true;
        }

    }
    return false;
}


//-------------------------------------------------

    // Add Course to Student
    public boolean addCourseToStudent(String studentId, Courses course) {
        for (Student student : students) {
            if (student.getStudId().equals(studentId)) {
                student.getCourses().add(course);
                return true;
            }
        }
        return false;
    }

    // Remove Course from Student
    public boolean removeCourseFromStudent(String studentId, String courseId) {
        for (Student student : students) {
            if (student.getStudId().equals(studentId)) {
                // Remove the course with the specified course ID
                student.getCourses().removeIf(course -> course.getCourseID().equals(courseId));
                return true;
            }
        }
        return false;
    }

    //----------------
    public void viewStudentCourses(String studentId) {
        for (Student student : students) {
            if (student.getStudId().equals(studentId)) {
                Set<Courses> courses = student.getCourses();
                System.out.println("Student Courses: " + courses);
                return;
            }
        }
        System.out.println("Student not found.");
    }



    public boolean addCoursesToStudent(String studentId, Courses courses) {
        Optional<Student> studentOptional = students.stream()
                .filter(student -> student.getStudId().equals(studentId))
                .findFirst();

        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();

            // Ensure getCourses() does not return null
            if (student.getCourses() == null) {
                student.setCourses(new HashSet<>());
            }

            student.getCourses().add(courses);
            return true;
        }
        return false;

    }


    private ArrayList<Courses> course = new ArrayList<>();

    public ArrayList<Courses> getNewStartCourses(LocalDate startDate) {
        if (courses == null) {
            return new ArrayList<>();
        }

        return courses.stream()
                .filter(course -> course.getStaetCourse() != null && course.getStaetCourse().isEqual(startDate))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }




//    public ArrayList<Courses> getNewStartCourses(LocalDate startDate) {
//        if (courses == null) {
//            return new ArrayList<>();
//        }
//
//        return courses.stream()
//                .filter(course -> course.getStaetCourse() != null && course.getStaetCourse().isEqual(startDate))
//                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
//    }


}
