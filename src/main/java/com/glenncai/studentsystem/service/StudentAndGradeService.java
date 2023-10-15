package com.glenncai.studentsystem.service;

import com.glenncai.studentsystem.model.entity.CollegeStudent;

/**
 * Student and grade service
 *
 * @author Glenn Cai
 * @version 1.0 29/9/2023
 */
public interface StudentAndGradeService {

  /**
   * Create a student
   *
   * @param firstName    first name
   * @param lastName     last name
   * @param emailAddress email address
   */
  void createStudent(String firstName, String lastName, String emailAddress);

  /**
   * Check if student exists
   *
   * @param studentId student id
   * @return true if student exists
   */
  boolean checkIfStudentExists(int studentId);

  /**
   * Delete student
   *
   * @param studentId student id
   */
  void deleteStudent(int studentId);

  /**
   * Get gradebook
   *
   * @return gradebook iterable
   */
  Iterable<CollegeStudent> getGradebook();

  /**
   * Create grade
   *
   * @param grade     grade
   * @param studentId student id
   * @param gradeType grade type
   * @return true if grade is created
   */
  boolean createGrade(double grade, int studentId, String gradeType);
}
