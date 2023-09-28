package com.glenncai.studentsystem.model.interfaces;

/**
 * Grade interface
 *
 * @author Glenn Cai
 * @version 1.0 28/9/2023
 */
public interface Grade {

  double getGrade();

  void setGrade(double grade);

  int getId();

  void setId(int id);

  int getStudentId();

  void setStudentId(int studentId);
}
