package com.example.learningmanagementsystem.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class Student {


    @Pattern(regexp = "\\d{10}")
    private String StudId;


    @Size(min = 3,message = "Length name must be more than 4 characters.")
    @Pattern(regexp = "^[a-zA-z]+$")
    private String StudName;


   @NotNull(message = "Should be Input your Email.")
    @Email
    private String StudEmail;



    @Digits(integer = 2, fraction = 0)
    @Min(value = 17,message = "You must be over 18 years old")
   private String studAge;



    @NotNull(message = "Must be input phone number")
    @Pattern(regexp = "^05\\d{8}",message = "must be your phone number start '05',")
    private String studPhone;

    private Set<Courses> courses;
    public Set<Courses> getCourses() {
        return courses;
    }

}
