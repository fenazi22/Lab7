package com.example.learningmanagementsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Courses {

    @NotEmpty(message = "Cannot be 'id' null.\n")
    @Pattern(regexp = "^\\d{10}+$")
    private String CourseID;

    @Size(min = 3, message = "Length 'Course name' must be more than 4 characters")
    private String CourseName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate staetCourse;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endCourse;

}
