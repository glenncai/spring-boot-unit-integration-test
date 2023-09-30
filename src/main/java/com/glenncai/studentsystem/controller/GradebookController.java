package com.glenncai.studentsystem.controller;

import com.glenncai.studentsystem.model.Gradebook;
import com.glenncai.studentsystem.model.entity.CollegeStudent;
import com.glenncai.studentsystem.service.StudentAndGradeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Grade book controller
 *
 * @author Glenn Cai
 * @version 1.0 28/9/2023
 */
@Controller
@RequestMapping("/")
public class GradebookController {

  @Resource
  private Gradebook gradebook;

  @Resource
  private StudentAndGradeService studentAndGradeService;

  @GetMapping
  public String getStudents(Model m) {
    Iterable<CollegeStudent> collegeStudents = studentAndGradeService.getGradebook();
    m.addAttribute("students", collegeStudents);
    return "index";
  }

  @PostMapping
  public String createStudent(@ModelAttribute("student") CollegeStudent student, Model m) {
    studentAndGradeService.createStudent(student.getFirstName(), student.getLastName(),
                                         student.getEmailAddress());
    Iterable<CollegeStudent> collegeStudents = studentAndGradeService.getGradebook();
    m.addAttribute("students", collegeStudents);
    return "index";
  }

  @GetMapping("/delete/student/{id}")
  public String deleteStudent(@PathVariable int id, Model m) {
    studentAndGradeService.deleteStudent(id);
    Iterable<CollegeStudent> collegeStudents = studentAndGradeService.getGradebook();
    m.addAttribute("students", collegeStudents);
    return "index";
  }

  @GetMapping("/studentInformation/{id}")
  public String studentInformation(@PathVariable int id, Model m) {
    return "studentInformation";
  }
}
