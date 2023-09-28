package com.glenncai.studentsystem.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is for xxx.
 *
 * @author Glenn Cai
 * @version 1.0 28/9/2023
 */
@Component
public class Gradebook {

  private List<GradebookCollegeStudent> students = new ArrayList<>();

  public Gradebook() {
  }

  public Gradebook(List<GradebookCollegeStudent> students) {
    this.students = students;
  }

  public List<GradebookCollegeStudent> getStudents() {
    return students;
  }

  public void setStudents(
      List<GradebookCollegeStudent> students) {
    this.students = students;
  }
}
