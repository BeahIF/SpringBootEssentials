package br.com.devdojo.endpoint;

import br.com.devdojo.error.CustomErrorType;
import br.com.devdojo.error.ResourceNotFoundException;
import br.com.devdojo.model.Student;
import br.com.devdojo.repository.StudentRepository;
import br.com.devdojo.util.DateUtil;
import org.apache.coyote.Request;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
//import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;

@RestController
@RequestMapping("students")
public class StudentEndpoint {
    private final StudentRepository studentDAO;
    @Autowired
    public  StudentEndpoint(StudentRepository studentDAO){
        this.studentDAO = studentDAO;
    }
    @GetMapping
    public ResponseEntity<?> listAll(Pageable pageable){
        return new ResponseEntity<>(studentDAO.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Long id){
        verifyIfStudentExists(id);
        Optional<Student> student = studentDAO.findById(id);
        if(student == null){
            throw new ResourceNotFoundException("Student not found for ID:"+id);
//            return  new ResponseEntity<>(new CustomErrorType("Student not found"), HttpStatus.NOT_FOUND);

        }else{
            return  new ResponseEntity<>(student, HttpStatus.OK);
        }
    }
    @GetMapping(path="/findByName")
    public ResponseEntity<?>findStudentsByName(@PathVariable String name){
        return new ResponseEntity<>(studentDAO.findByNameIgnoreCaseContaining(name), HttpStatus.OK);
    }
    @PostMapping
//    @Transactional(rollBackFor = Exception.class)
    public ResponseEntity<?> save(@Valid @RequestBody Student student ){
        return new ResponseEntity<>(studentDAO.save(student), HttpStatus.CREATED);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        verifyIfStudentExists(id);
//        studentDAO.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
    @PutMapping
    public ResponseEntity<?>update (@RequestBody Student student){
        verifyIfStudentExists(student.getId());
        studentDAO.save(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    private void verifyIfStudentExists(Long id ){
        if(studentDAO.findById(id)==null)
            throw new ResourceNotFoundException("Student not found for Id:"+id);    }
//    private DateUtil dateUtil;
//
//    @GetMapping
//    public ResponseEntity<?> listAll() {
////        System.out.println(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
//        return new ResponseEntity<>(Student.studentList, HttpStatus.OK);
//    }
//
//    @GetMapping(path = "/{id}")
//    public ResponseEntity<?> getStudentById(@PathVariable("id") int id) {
//        Student student = new Student();
//        student.setId(id);
//        int index = Student.studentList.indexOf(student);
//        if (index == -1) {
//            return new ResponseEntity<>(new CustomErrorType("Student not found"), HttpStatus.NOT_FOUND);
//        } else {
//            return new ResponseEntity<>(Student.studentList.get(index), HttpStatus.OK);
//        }
//    }
//
//    @PostMapping
//    public ResponseEntity<?> save(@RequestBody Student student) {
//        Student.studentList.add(student);
//        return new ResponseEntity<>(student, HttpStatus.OK);
//    }
//    @RequestMapping(method = RequestMethod.DELETE)
//    public ResponseEntity<?> delete(@RequestBody Student student) {
//        Student.studentList.remove(student);
//        return new ResponseEntity<>( HttpStatus.OK);
//    }
//    @RequestMapping(method = RequestMethod.PUT)
//    public ResponseEntity<?> update(@RequestBody Student student) {
//        Student.studentList.remove(student);
////        Student.studentList.
//        return new ResponseEntity<>( HttpStatus.OK);
//    }

}