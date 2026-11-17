package in.Ambuj.crudSpringBoot.controller;

import in.Ambuj.crudSpringBoot.dto.StudentRequestDto;
import in.Ambuj.crudSpringBoot.dto.StudentResponseDto;
import in.Ambuj.crudSpringBoot.dto.UpdateRequestDto;
import in.Ambuj.crudSpringBoot.dto.UpdateResponceDto;
import in.Ambuj.crudSpringBoot.entitys.Student;
import in.Ambuj.crudSpringBoot.services.StudentService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.util.StringBuilderFormattable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    public  StudentController(StudentService studentService){
        this.studentService=studentService;
    }


    //  create student
    @PostMapping()
   public StudentResponseDto createStudent( @Valid  @RequestBody StudentRequestDto studentRequestDto){
      return studentService.createdStudent(studentRequestDto);
    }

    //get student by id
    @GetMapping("/{id}")
    public  ResponseEntity<StudentResponseDto> getOneStudent( @Valid @PathVariable Long id){
        StudentResponseDto studentRes=studentService.getStudent(id);
        return ResponseEntity.ok(studentRes);

    }


    // get All students
    @GetMapping()
    public  ResponseEntity<List<StudentResponseDto>> getOneStudent(){
        List<StudentResponseDto> studentsList=studentService.getAllStudent();

        return ResponseEntity.ok(studentsList);
    }


    // update student
    @PutMapping("/{id}")
    public  ResponseEntity<UpdateResponceDto> updateStudent( @Valid @PathVariable Long id,
                                                            @RequestBody UpdateRequestDto studentReq){
        UpdateResponceDto studentRes=studentService.updateStudent(id,studentReq);
        return ResponseEntity.ok(studentRes);
    }

    // delete student

    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteStudent( @Valid @PathVariable Long id){
      studentService.deleteStudent(id);
        return ResponseEntity.ok("student deleted");
    }

    // soft delete
    @PatchMapping("/{id}")
     public  ResponseEntity<String> soft_delete(@PathVariable Long id){
       studentService.sofrDeleted(id);

        return ResponseEntity.ok("student soft deleted deleted");
    }

    }



