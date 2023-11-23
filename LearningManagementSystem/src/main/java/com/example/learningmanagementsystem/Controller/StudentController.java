package com.example.learningmanagementsystem.Controller;

import com.example.learningmanagementsystem.Model.Courses;
import com.example.learningmanagementsystem.Model.Student;
import com.example.learningmanagementsystem.Service.CourseService;
import com.example.learningmanagementsystem.Service.StudService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/student")
public class StudentController {
    private  final StudService studService;
    @GetMapping("/get")
    public ResponseEntity getStudent(){
        return ResponseEntity.status(HttpStatus.OK).body(studService.getStudents());
    }

  @PostMapping("/add")
    public ResponseEntity addSud(@Valid @RequestBody Student student, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
    studService.addStud(student);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully Added");
  }




    @PutMapping("/update/{id}")
    public ResponseEntity updateSud(@PathVariable String id, @Valid @RequestBody Student student,Errors errors){
    if (errors.hasErrors()){
        String message= Objects.requireNonNull(errors.getFieldError()).getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
         }

    boolean isUpdate=studService.updateSud(id,student);
            if (isUpdate){
        return ResponseEntity.status(HttpStatus.OK).body("Successfully Updated");

    }else{
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sorry Not Found");
    }
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStud(@PathVariable String id){
        boolean isDeleted=studService.deleteStud(id);
        if (isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body("Deleted");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sorry Not found");
        }
    }


    @PostMapping("/addCourse/{studentId}")
    public ResponseEntity addStudCourse(@PathVariable String studentId, @Valid @RequestBody Courses courses, Errors errors) {
        if (errors.hasErrors()) {
            String message = Objects.requireNonNull(errors.getFieldError()).getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        boolean isSuccess = studService.addCoursesToStudent(studentId, courses);

        if (isSuccess) {
            return ResponseEntity.status(HttpStatus.OK).body("Courses added to the student successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
    }


//@DeleteMapping("/rmCourse/{studentId}")
//    public  ResponseEntity removeCourseFromStudent(@PathVariable String studentId,@Valid @RequestBody Courses course,Errors errors){
//    if (errors.hasErrors()){
//        String message= Objects.requireNonNull(errors.getFieldError()).getDefaultMessage();
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
//    }
//            boolean isRmCourse=studService.removeCourseFromStudent(studentId,course);
//    if (isRmCourse){
//        return  ResponseEntity.status(HttpStatus.OK).body("removed Course");
//    }else {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not Found");
//    }
//    }




//    @DeleteMapping("/rmCourse/{studentId}")
//    public ResponseEntity removeCourseFromStudent(@PathVariable String studentId) {
//        boolean isRmCourse = studService.removeCourseFromStudent(studentId);
//        if (isRmCourse) {
//            return ResponseEntity.status(HttpStatus.OK).body("Removed Course");
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student or Course Not Found");
//        }
//    }





    @DeleteMapping("/rmCourse/{studentId}/{courseId}")
    public ResponseEntity removeCourseFromStudent(@PathVariable String studentId, @PathVariable String courseId) {
        boolean isRmCourse = studService.removeCourseFromStudent(studentId, courseId);
        if (isRmCourse) {
            return ResponseEntity.status(HttpStatus.OK).body("Removed Course");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student or Course Not Found");
        }
    }


    @GetMapping("/viewstudCour/{}")
    public ResponseEntity viewStudentCourses(@PathVariable String studentId){
    studService.viewStudentCourses(studentId);
    return ResponseEntity.status(HttpStatus.OK).body("Successfully");
    }

//    @GetMapping("/searchNewStartCourses")
//    public ResponseEntity searchNewStartCourses(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate) {
//        ArrayList<Courses> newStartCourses = studService.getNewStartCourses(startDate);
//
//        if (!newStartCourses.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.OK).body(newStartCourses);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No courses found with the specified start date");
//        }
//    }





    @GetMapping("/searchNewStartCourses")
    public ResponseEntity searchNewStartCourses(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate) {
        ArrayList<Courses> newStartCourses = studService.getNewStartCourses(startDate);

        if (!newStartCourses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(newStartCourses);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No courses found with the specified start date");
        }
    }



}
