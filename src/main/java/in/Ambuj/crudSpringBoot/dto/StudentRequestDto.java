package in.Ambuj.crudSpringBoot.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.jspecify.annotations.NonNull;

public class StudentRequestDto {
    @NotBlank(message = "name can not be null or blank or space")
    @Size(min = 2,max = 20, message = "name should be 2 character long")
    private String name;
    @NotNull(message = "age is required ")
    @Min(value = 18, message = "age should be atleast 18 year old")
    private Integer age;
    @Email(message = "email is required ")
    private String email;
    @NotNull(message = "roll number should not be null")
    private  Integer rollNo;
    @NotBlank(message = "name can not be null or blank or space")
    @NotBlank(message = " sbject should not me null of empty of blank space")
    private String subject;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
