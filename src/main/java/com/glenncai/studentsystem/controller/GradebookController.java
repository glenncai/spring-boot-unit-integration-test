package com.glenncai.studentsystem.controller;

import com.glenncai.studentsystem.model.Gradebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @GetMapping
  public String getStudents(Model m) {
    return "index";
  }

  @GetMapping("/studentInformation/{id}")
  public String studentInformation(@PathVariable int id, Model m) {
    return "studentInformation";
  }
}
