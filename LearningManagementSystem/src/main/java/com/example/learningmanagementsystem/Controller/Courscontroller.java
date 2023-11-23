package com.example.learningmanagementsystem.Controller;

import com.example.learningmanagementsystem.Model.Courses;
import com.example.learningmanagementsystem.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class Courscontroller {
    private  final CourseService courseService;
    @GetMapping("/get")
    public ResponseEntity getCoursess(){
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourses());
    }

    @PostMapping("/add")
    public ResponseEntity addCourse(@Valid @RequestBody Courses course, Errors errors){
    if (errors.hasErrors()){
        String message=errors.getFieldError().getDefaultMessage();
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

        courseService.addCourse(course);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully add");
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateCourse(@PathVariable String id,@Valid @RequestBody Courses courses,Errors errors){
      if (errors.hasErrors()) {
          String message=errors.getFieldError().getDefaultMessage();
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
      }

      boolean isUpdated=courseService.updateCourse(id,courses);
        if (isUpdated)
            return ResponseEntity.status(HttpStatus.OK).body("Updated Successfully");
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sorry Not Found");

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable String id){

        boolean isDeleted=courseService.deleteCourse(id);
        if (isDeleted)
            return ResponseEntity.status(HttpStatus.OK).body("Successfully Deleted");
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sorry Not Found");
    }




}
