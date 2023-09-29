package com.glenncai.studentsystem.controller;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.glenncai.studentsystem.model.GradebookCollegeStudent;
import com.glenncai.studentsystem.model.entity.CollegeStudent;
import com.glenncai.studentsystem.service.StudentAndGradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Resource;

/**
 * Grade book controller test
 *
 * @author Glenn Cai
 * @version 1.0 29/9/2023
 */
@TestPropertySource(value = "classpath:application.yml")
@AutoConfigureMockMvc
@SpringBootTest
class GradebookControllerTest {

  @Resource
  private JdbcTemplate jdbcTemplate;

  @Resource
  private MockMvc mockMvc;

  @Mock
  private StudentAndGradeService studentAndGradeService;

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
  @DisplayName("Get students")
  void testGetStudents() throws Exception {
    CollegeStudent studentOne = new GradebookCollegeStudent("Eric", "Roby", "eric_roby@gmail.com");
    CollegeStudent studentTwo = new GradebookCollegeStudent("Jack", "Wong", "jack_wong@gmail.com");
    List<CollegeStudent> collegeStudentList =
        new ArrayList<>(Arrays.asList(studentOne, studentTwo));

    // Mock the service response
    when(studentAndGradeService.getGradebook()).thenReturn(collegeStudentList);

    assertIterableEquals(collegeStudentList, studentAndGradeService.getGradebook());

    MvcResult mvcResult =
        mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(status().isOk()).andReturn();
    ModelAndView modelAndView = mvcResult.getModelAndView();
    if (modelAndView != null) {
      ModelAndViewAssert.assertViewName(modelAndView, "index");
    }
  }
}