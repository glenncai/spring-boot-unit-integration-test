package com.glenncai.studentsystem.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.glenncai.studentsystem.model.GradebookCollegeStudent;
import com.glenncai.studentsystem.model.entity.CollegeStudent;
import com.glenncai.studentsystem.repository.StudentDao;
import com.glenncai.studentsystem.service.StudentAndGradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
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

  private static MockHttpServletRequest request;

  @Resource
  private JdbcTemplate jdbcTemplate;

  @Resource
  private MockMvc mockMvc;

  @Resource
  private StudentDao studentDao;

  @Mock
  private StudentAndGradeService studentAndGradeService;

  @BeforeAll
  public static void setUpRequest() {
    request = new MockHttpServletRequest();
    request.setParameter("firstName", "Chad");
    request.setParameter("lastName", "Darby");
    request.setParameter("emailAddress", "chad_darby@gmail.com");
  }

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

  @Test
  @DisplayName("Create student")
  void testCreateStudent() throws Exception {
    CollegeStudent studentOne = new GradebookCollegeStudent("Eric", "Roby", "eric_roby@gmail.com");
    List<CollegeStudent> collegeStudentList = new ArrayList<>(List.of(studentOne));
    when(studentAndGradeService.getGradebook()).thenReturn(collegeStudentList);
    assertIterableEquals(collegeStudentList, studentAndGradeService.getGradebook());

    MvcResult mvcResult = mockMvc.perform(post("/")
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .param("firstName",
                                                     request.getParameterValues("firstName"))
                                              .param("lastName",
                                                     request.getParameterValues("lastName"))
                                              .param("emailAddress",
                                                     request.getParameterValues("emailAddress")))
                                 .andExpect(status().isOk()).andReturn();
    ModelAndView modelAndView = mvcResult.getModelAndView();
    if (modelAndView != null) {
      ModelAndViewAssert.assertViewName(modelAndView, "index");
    }

    CollegeStudent verifyStudent = studentDao.findByEmailAddress("chad_darby@gmail.com");
    assertNotNull(verifyStudent, "Student should not be null");
  }

  @Test
  @DisplayName("Delete student")
  void testDeleteStudent() throws Exception {
    assertTrue(studentDao.findById(1).isPresent(), "Student should be present");

    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/delete/student/{id}", 1))
                                 .andExpect(status().isOk()).andReturn();
    ModelAndView modelAndView = mvcResult.getModelAndView();
    if (modelAndView != null) {
      ModelAndViewAssert.assertViewName(modelAndView, "index");
    }

    assertFalse(studentDao.findById(1).isPresent(), "Student should not be present");
  }

  @Test
  @DisplayName("Error page")
  void testErrorPage() throws Exception {
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/delete/student/{id}", 0))
                                 .andExpect(status().isOk()).andReturn();
    ModelAndView modelAndView = mvcResult.getModelAndView();
    if (modelAndView != null) {
      ModelAndViewAssert.assertViewName(modelAndView, "error");
    }
  }
}