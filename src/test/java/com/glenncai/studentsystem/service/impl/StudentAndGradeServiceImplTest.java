package com.glenncai.studentsystem.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.glenncai.studentsystem.model.entity.CollegeStudent;
import com.glenncai.studentsystem.repository.StudentDao;
import com.glenncai.studentsystem.service.StudentAndGradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;

/**
 * Student and grade service implementation test
 *
 * @author Glenn Cai
 * @version 1.0 29/9/2023
 */
@TestPropertySource(value = "classpath:application.yml")
@SpringBootTest
class StudentAndGradeServiceImplTest {

  @Resource
  StudentAndGradeService studentAndGradeService;

  @Resource
  StudentDao studentDao;

  @Resource
  private JdbcTemplate jdbcTemplate;

  @BeforeEach
  void setUpDatabase() {
    jdbcTemplate.execute(
        "INSERT INTO student (first_name, last_name, email_address) VALUES ('John', 'Wong', " +
            "'john@gmail.com')");
  }

  @AfterEach
  void cleanUpDatabase() {
    jdbcTemplate.execute("DELETE FROM student");
  }

  @Test
  @DisplayName("Create student")
  void testCreateStudent() {
    String firstName = "Glenn";
    String lastName = "Cai";
    String emailAddress = "test@gmail.com";
    studentAndGradeService.createStudent(firstName, lastName, emailAddress);
    CollegeStudent collegeStudent = studentDao.findByEmailAddress(emailAddress);
    assertEquals(emailAddress, collegeStudent.getEmailAddress(), "Find by email address");
  }

  @Test
  @DisplayName("Check if student exists")
  void testCheckIfStudentExists() {
    assertTrue(studentAndGradeService.checkIfStudentExists(1));
    assertFalse(studentAndGradeService.checkIfStudentExists(0));
  }

  @Test
  @DisplayName("Delete student")
  void testDeleteStudent() {
    Optional<CollegeStudent> collegeStudent = studentDao.findById(1);
    assertTrue(collegeStudent.isPresent());
    studentAndGradeService.deleteStudent(1);
    assertFalse(studentAndGradeService.checkIfStudentExists(1));
  }

  @Test
  @Sql("classpath:insertData.sql") // Run insertData.sql after @BeforeEach
  @DisplayName("Get grade book")
  void testGetGradebook() {
    Iterable<CollegeStudent> collegeStudentIterable = studentAndGradeService.getGradebook();
    List<CollegeStudent> collegeStudents = new ArrayList<>();

    for (CollegeStudent collegeStudent : collegeStudentIterable) {
      collegeStudents.add(collegeStudent);
    }

    assertEquals(5, collegeStudents.size());
  }
}