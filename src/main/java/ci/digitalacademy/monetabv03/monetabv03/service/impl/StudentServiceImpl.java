package ci.digitalacademy.monetabv03.monetabv03.service.impl;


import ci.digitalacademy.monetabv03.monetabv03.models.Gender;
import ci.digitalacademy.monetabv03.monetabv03.models.Student;
import ci.digitalacademy.monetabv03.monetabv03.repositories.StudentRepository;
import ci.digitalacademy.monetabv03.monetabv03.security.AuthorityConstants;
import ci.digitalacademy.monetabv03.monetabv03.service.*;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.*;
import ci.digitalacademy.monetabv03.monetabv03.service.mapper.StudentMapper;
import ci.digitalacademy.monetabv03.monetabv03.service.mapping.AbsenceMapping;
import ci.digitalacademy.monetabv03.monetabv03.service.mapping.StudentMapping;
import ci.digitalacademy.monetabv03.monetabv03.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
@Slf4j
@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final FileStorageService fileStorageService;

//    private final AddressService addressService;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleUserService roleUserService;



    @Override
    public StudentDTO save(StudentDTO studentDTO) {
        Student student = studentMapper.DtoToEntity(studentDTO);
        student.setSlug(SlugifyUtils.generate(student.getMatricule()));
        return studentMapper.ToDto(studentRepository.save(student));
    }

    @Override
    public StudentDTO uploadStudentPicture(Long id, MultipartFile picture) throws IOException {
        Optional<StudentDTO> one = findById(id);
        StudentDTO student = one.orElse(null);
        if(student != null){
            String upload = fileStorageService.upload(picture);
            student.setUrlPicture(upload);
            save(student);
        }
        return student;

//    @Override
//    public StudentDTO update(StudentDTO studentDTO) {
//        return findById((studentDTO.getId_person())).map(existingStudent ->{
//            existingStudent.setAddress(studentDTO.getAddress());
//            existingStudent.setFirstName(studentDTO.getFirstName());
//            return save(existingStudent);
//        }).orElseThrow(()-> new RuntimeException("Student not found"));
//    }
//
//    @Override
//    public StudentDTO partialUpdate(StudentDTO studentDTO, Long id) {
//        return studentRepository.findById(id).map(student -> {
//            StudentMapping.partialUpdate(student,studentDTO);
//            return student;
//        }).map(studentRepository::save).map(studentMapper::ToDto).orElse(null);    }
//
//    @Override
//    public StudentDTO update(StudentDTO studentDTO, Long id) {
//        return findById(id).map(student -> {
//                    student.setLastName(studentDTO.getLastName());
//                    student.setFirstName(studentDTO.getFirstName());
//                    student.setMatricule(studentDTO.getMatricule());
//                    return save(student);
//                }).orElseThrow(()-> new IllegalArgumentException("Students not found"));
//    }
//
//    @Override
//    public Optional<StudentDTO> findById(Long id) {
//        return studentRepository.findById(id).map(student -> studentMapper.ToDto(student));
//    }
//
//
//    @Override
//    public List<StudentDTO> findAll() {
//        return studentRepository.findAll().stream().map(student -> {
//            return studentMapper.ToDto(student);
//        }).toList();
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        studentRepository.deleteById(id);
//    }
//
//    @Override
//    public List<StudentDTO> findByLastNameOrGenderOrMatricule(String query , String gender) {
//        List<Student> students = studentRepository.findByLastNameIgnoreCaseOrMatriculeIgnoreCaseAndGender(query  , query , Gender.valueOf(gender));
//        return students.stream().map(student -> studentMapper.ToDto(student)).toList();
//    }
//
//
//
//    @Override
//    @Transactional
//    public ResponseRegisterStudentDTO registerStudent(RegistrationStudentDTO registrationStudentDTO) {
//        log.debug("Request to register student {}", registrationStudentDTO);
//        AddressDTO address = modelMapper.map(registrationStudentDTO, AddressDTO.class);
////        address = addressService.save(address);
//
//        List<RoleUserDTO> roleUsers = roleUserService.findByRole(AuthorityConstants.ROLE_USER);
//        UserDTO user = modelMapper.map(registrationStudentDTO, UserDTO.class);
//        String password = UUID.randomUUID().toString();
//        user.setPassword(bCryptPasswordEncoder.encode(password));
//        user.setRoleUser((Set<RoleUserDTO>) roleUsers);
//        user = userService.save(user);
//
//        StudentDTO student = modelMapper.map(registrationStudentDTO, StudentDTO.class);
//        student.setUser(user);
//        student.setAddress(address);
//        student = save(student);
//
//        ResponseRegisterStudentDTO response = new ResponseRegisterStudentDTO();
//        response.setStudent(student);
//        response.setAddress(address);
//        return response;
    }

    @Override
    public StudentDTO update(StudentDTO studentDTO) {
        return null;
    }

    @Override
    public StudentDTO update(StudentDTO studentDTO, Long id) {
        return null;
    }

    @Override
    public Optional<StudentDTO> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<StudentDTO> findAll() {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<StudentDTO> findByLastNameOrGenderOrMatricule(String query, String gender) {
        return null;
    }

    @Override
    public StudentDTO partialUpdate(StudentDTO studentDTO, Long id) {
        return null;
    }

    @Override
    public ResponseRegisterStudentDTO registerStudent(RegistrationStudentDTO registrationStudentDTO) {
        return null;
    }
}
