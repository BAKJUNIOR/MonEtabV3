package ci.digitalacademy.monetabv03.monetabv03.controller;

import ci.digitalacademy.monetabv03.monetabv03.service.StudentService;
import ci.digitalacademy.monetabv03.monetabv03.service.TeacherService;
import ci.digitalacademy.monetabv03.monetabv03.service.UserService;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.StudentDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.TeacherDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.UserDTO;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Paragraph;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.itextpdf.layout.Document;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.itextpdf.layout.element.Table;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Date;


@Controller
@RequiredArgsConstructor
@RestController
@RequestMapping("/reports")
public class GenerReportController {


    private final StudentService studentService;
    private final TeacherService teacherService;
    private final UserService userService;



    @GetMapping("/generatePdf")
    public ResponseEntity<byte[]> generatePdf(@RequestParam String operation) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        HttpHeaders headers = new HttpHeaders();

        switch (operation) {
            case "students":
                List<StudentDTO> students = studentService.findAll();
                generateStudentsPdf(students, outputStream);
                headers.add("Content-Disposition", "attachment; filename=students_report.pdf");
                break;
            case "teachers":
                List<TeacherDTO> teachers = teacherService.findAll();
                generateTeachersPdf(teachers, outputStream);
                headers.add("Content-Disposition", "attachment; filename=teachers_report.pdf");
                break;
            case "users":
                List<UserDTO> users = userService.getAll();
                generateUsersPdf(users, outputStream);
                headers.add("Content-Disposition", "attachment; filename=users_report.pdf");
                break;
        }

        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }

    private void generateStudentsPdf(List<StudentDTO> students, ByteArrayOutputStream outputStream) throws IOException {
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Titre du document
        document.add(new Paragraph("Liste des Étudiants").setBold().setFontSize(20));

        // Créer une table avec 7 colonnes
        Table table = new Table(7);
        table.addHeaderCell("Matricule");
        table.addHeaderCell("Nom");
        table.addHeaderCell("Prénom");
        table.addHeaderCell("Numéro de téléphone du parent");
        table.addHeaderCell("Date de naissance");
        table.addHeaderCell("Genre");
        table.addHeaderCell("Ville");

        // Remplir la table avec les données
        for (StudentDTO student : students) {
            table.addCell(student.getMatricule());
            table.addCell(student.getLastName());
            table.addCell(student.getFirstName());
            table.addCell(student.getPhoneNumberParent());
            table.addCell(student.getDateOfBirth() != null ? student.getDateOfBirth().toString() : "");
            table.addCell(student.getGender() != null ? student.getGender().toString() : "");
            table.addCell(student.getAddress() != null ? student.getAddress().getCity() : "");
        }

        document.add(table);
        document.close();
    }

    private void generateTeachersPdf(List<TeacherDTO> teachers, ByteArrayOutputStream outputStream) throws IOException {
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Titre du document
        document.add(new Paragraph("Liste des Enseignants").setBold().setFontSize(20));


        Table table = new Table(7);
        table.addHeaderCell("Nom");
        table.addHeaderCell("Prénom");
        table.addHeaderCell("Numéro de téléphone");
        table.addHeaderCell("Spécialité");
        table.addHeaderCell("Date de naissance");
        table.addHeaderCell("Genre");
        table.addHeaderCell("Ville");

        // Remplir la table avec les données
        for (TeacherDTO teacher : teachers) {
            table.addCell(teacher.getLastName());
            table.addCell(teacher.getFirstName());
            table.addCell(teacher.getNumbers());
            table.addCell(teacher.getSpecialty());
            table.addCell(teacher.getDateOfBirth() != null ? teacher.getDateOfBirth().toString() :" ");
            table.addCell(teacher.getGender() != null ? teacher.getGender().toString() : "");
            table.addCell(teacher.getAddress() != null ? teacher.getAddress().getCity() : "");
        }

        document.add(table);
        document.close();
    }

    private void generateUsersPdf(List<UserDTO> users, ByteArrayOutputStream outputStream) throws IOException {
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Titre du document
        document.add(new Paragraph("Liste des Utilisateurs").setBold().setFontSize(20));

        // Créer une table avec 3 colonnes
        Table table = new Table(3);
        table.addHeaderCell("Pseudo");
        table.addHeaderCell("Rôle");
        table.addHeaderCell("Date de création");

        // Remplir la table avec les données
        for (UserDTO user : users) {
            table.addCell(user.getPseudo());
            table.addCell((com.itextpdf.layout.element.Cell) user.getRoleUser());
            table.addCell(String.valueOf(user.getCreatedDate()));
        }

        document.add(table);
        document.close();
    }

    @GetMapping("/generateExcel")
    public ResponseEntity<byte[]> generateExcel(@RequestParam String operation) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        HttpHeaders headers = new HttpHeaders();

        switch (operation) {
            case "students":
                List<StudentDTO> students = studentService.findAll();
                generateStudentsExcel(students, outputStream);
                headers.add("Content-Disposition", "attachment; filename=students_report.xlsx");
                break;
            case "teachers":
                List<TeacherDTO> teachers = teacherService.findAll();
                generateTeachersExcel(teachers, outputStream);
                headers.add("Content-Disposition", "attachment; filename=teachers_report.xlsx");
                break;
            case "users":
                List<UserDTO> users = userService.getAll();
                generateUsersExcel(users, outputStream);
                headers.add("Content-Disposition", "attachment; filename=users_report.xlsx");
                break;
        }

        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }

    private void generateStudentsExcel(List<StudentDTO> students, ByteArrayOutputStream outputStream) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Students");

        // Créer l'en-tête
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Matricule");
        header.createCell(1).setCellValue("Nom");
        header.createCell(2).setCellValue("Prénom");
        header.createCell(3).setCellValue("Numéro de téléphone du parent");
        header.createCell(4).setCellValue("Date de naissance");
        header.createCell(5).setCellValue("Genre");
        header.createCell(6).setCellValue("Ville");

        // Remplir les données
        int rowNum = 1;
        for (StudentDTO student : students) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(student.getMatricule());
            row.createCell(1).setCellValue(student.getLastName());
            row.createCell(2).setCellValue(student.getFirstName());
            row.createCell(3).setCellValue(student.getPhoneNumberParent());
            row.createCell(4).setCellValue(student.getDateOfBirth() != null ? student.getDateOfBirth().toString() : "");
            row.createCell(5).setCellValue(student.getGender() != null ? student.getGender().toString() : "");
            row.createCell(6).setCellValue(student.getAddress() != null ? student.getAddress().getCity() : "");
        }

        workbook.write(outputStream);
        workbook.close();
    }

    private void generateTeachersExcel(List<TeacherDTO> teachers, ByteArrayOutputStream outputStream) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Teachers");

        // Créer l'en-tête
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Nom");
        header.createCell(1).setCellValue("Prénom");
        header.createCell(2).setCellValue("Spécialité");
        header.createCell(3).setCellValue("Numéro de téléphone");
        header.createCell(4).setCellValue("Date de naissance");
        header.createCell(5).setCellValue("Genre");
        header.createCell(6).setCellValue("Ville");

        // Remplir les données
        int rowNum = 1;
        for (TeacherDTO teacher : teachers) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(teacher.getLastName());
            row.createCell(1).setCellValue(teacher.getFirstName());
            row.createCell(2).setCellValue(teacher.getSpecialty());
            row.createCell(3).setCellValue(teacher.getNumbers());
            row.createCell(3).setCellValue(teacher.getDateOfBirth());
            row.createCell(5).setCellValue(teacher.getGender() != null ? teacher.getGender().toString() : "");
            row.createCell(6).setCellValue(teacher.getAddress() != null ? teacher.getAddress().getCity() : "");
        }

        workbook.write(outputStream);
        workbook.close();
    }

    private void generateUsersExcel(List<UserDTO> users, ByteArrayOutputStream outputStream) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Users");

        // Créer l'en-tête
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Pseudo");
        header.createCell(1).setCellValue("Rôle");
        header.createCell(2).setCellValue("Date de création");

        // Remplir les données
        int rowNum = 1;
        for (UserDTO user : users) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(user.getPseudo());
            row.createCell(1).setCellValue((Date) user.getRoleUser());
            row.createCell(2).setCellValue(user.getCreatedDate() != null ? user.getCreatedDate().toString() : "");
        }

        workbook.write(outputStream);
        workbook.close();
    }



}