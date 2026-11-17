package in.Ambuj.crudSpringBoot.services;

import in.Ambuj.crudSpringBoot.dto.StudentRequestDto;
import in.Ambuj.crudSpringBoot.dto.StudentResponseDto;
import in.Ambuj.crudSpringBoot.dto.UpdateRequestDto;
import in.Ambuj.crudSpringBoot.dto.UpdateResponceDto;
import in.Ambuj.crudSpringBoot.entitys.Student;
import in.Ambuj.crudSpringBoot.exceptionHandling.ConflictException;
import in.Ambuj.crudSpringBoot.exceptionHandling.ResourceNotFoundException;
import in.Ambuj.crudSpringBoot.repository.StudentRepositorry;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepositorry studentRepositorry;

    public StudentService(StudentRepositorry studentRepositorry) {

        this.studentRepositorry = studentRepositorry;
    }
 // create student
    public StudentResponseDto createdStudent(StudentRequestDto studentReqDto){
        Student studentReq=mapToEntity(studentReqDto);
        if(emailExist(studentReq)){
            throw new ConflictException("email already exist " + studentReq.getEmail() + " use another email");
        }
        Student studentRes= studentRepositorry.save(studentReq);
      StudentResponseDto studentResponseDto=  mapToDto(studentRes);
       return  studentResponseDto;
   }

   // get one student
    public  StudentResponseDto getStudent(Long id){
   Student existStudent=studentRepositorry
           .findByIDAndDeletedIsFalse(id)
           .orElseThrow(()->
                   new ResourceNotFoundException("student with id " + id +" not found"));


        return mapToDto(existStudent);
    }

    // get all students
    public List<StudentResponseDto> getAllStudent(){
        List<Student> studentsList=studentRepositorry.findByDeletedIsFalse();
        return studentsList.stream()
                .map(this::mapToDto)
                .toList();
    }

    // update student
    public UpdateResponceDto updateStudent(Long id, UpdateRequestDto studentReq){
       Student  existStudent=studentRepositorry
               .findByIDAndDeletedIsFalse(id)
               .orElseThrow(()->
                       new ResourceNotFoundException("student with id " + id +" not found"));

        Student  studentToSave= existStudent;
        studentToSave.setName(studentReq.getName());
        studentToSave.setAge(studentReq.getAge());
        studentToSave.setRollNo(studentReq.getRollNo());
        studentToSave.setSubject(studentReq.getSubject());
        studentToSave.setDeleted(false);
        Student updatedStudent =studentRepositorry.save(studentToSave);
        return  mapToUpdatedStudent(updatedStudent);
    }

    // delete student
    public void deleteStudent(Long id){
        Student studentTobeDeleted =studentRepositorry
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("student with id " + id +" not found"));

        studentRepositorry.delete(studentTobeDeleted);
    }

    // soft delete
    public Boolean sofrDeleted(Long id ){
        Student  existStudent=studentRepositorry
                .findByIDAndDeletedIsFalse(id)
                .orElseThrow(()->  new ResourceNotFoundException("student with id " + id +" not found"));


        Student studentForSoftDelete=existStudent;
        studentForSoftDelete.setDeleted(true);
        studentRepositorry.save(studentForSoftDelete);
        return  true;

    }
    private  Student mapToEntity(StudentRequestDto studentRequestDto){
        Student student=new Student();
        student.setName(studentRequestDto.getName());
        student.setAge(studentRequestDto.getAge());
        student.setEmail(studentRequestDto.getEmail());
        student.setRollNo(studentRequestDto.getRollNo());
        student.setSubject(studentRequestDto.getSubject());
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());
        student.setDeleted(false);
        return student;
    }

    private StudentResponseDto  mapToDto(Student studentRes){
        StudentResponseDto studentResponseDto=new StudentResponseDto();
        studentResponseDto.setId(studentRes.getID());
        studentResponseDto.setName(studentRes.getName());
        studentResponseDto.setAge(studentRes.getAge());
        studentResponseDto.setEmail(studentRes.getEmail());
        studentResponseDto.setRollNo(studentRes.getRollNo());
        studentResponseDto.setSubject(studentRes.getSubject());
        studentResponseDto.setCreatedAt(studentRes.getCreatedAt());
        studentResponseDto.setMessage("student created successfully");

        return studentResponseDto;

    }

    private UpdateResponceDto mapToUpdatedStudent(Student updatedStudent){
        UpdateResponceDto updateResponceDto=new UpdateResponceDto();

        updateResponceDto.setId(updatedStudent.getID());
        updateResponceDto.setName(updatedStudent.getName());
        updateResponceDto.setAge(updatedStudent.getAge());
        updateResponceDto.setEmail(updatedStudent.getEmail());
        updateResponceDto.setRollNo(updatedStudent.getRollNo());
        updateResponceDto.setSubject(updatedStudent.getSubject());
        updateResponceDto.setUpdatedAt(updatedStudent.getUpdatedAt());
        updateResponceDto.setMessage("student updated successfully");

        return updateResponceDto;

    }

    private boolean emailExist(Student student){
        return studentRepositorry.existsByEmail(student.getEmail());
    }
}
