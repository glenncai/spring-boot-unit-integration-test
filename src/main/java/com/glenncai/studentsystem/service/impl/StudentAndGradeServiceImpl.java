package com.glenncai.studentsystem.service.impl;

import com.glenncai.studentsystem.model.entity.CollegeStudent;
import com.glenncai.studentsystem.repository.StudentDao;
import com.glenncai.studentsystem.service.StudentAndGradeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import javax.annotation.Resource;

/**
 * Student and grade service implementation
 *
 * @author Glenn Cai
 * @version 1.0 29/9/2023
 */
@Transactional
@Service
public class StudentAndGradeServiceImpl implements StudentAndGradeService {

  @Resource
  StudentDao studentDao;

  /**
   * Create a student
   *
   * @param firstName    first name
   * @param lastName     last name
   * @param emailAddress email address
   */
  public void createStudent(String firstName, String lastName, String emailAddress) {
    CollegeStudent student = new CollegeStudent();
    student.setFirstName(firstName);
    student.setLastName(lastName);
    student.setEmailAddress(emailAddress);
    studentDao.save(student);
  }

  /**
   * Check if student exists
   *
   * @param studentId student id
   * @return true if student exists
   */
  public boolean checkIfStudentExists(int studentId) {
    Optional<CollegeStudent> collegeStudent = studentDao.findById(studentId);
    return collegeStudent.isPresent();
  }

  /**
   * Delete student
   *
   * @param studentId student id
   */
  public void deleteStudent(int studentId) {
    if (checkIfStudentExists(studentId)) {
      studentDao.deleteById(studentId);
    }
  }

  /**
   * Get gradebook
   *
   * @return gradebook iterable
   */
  public Iterable<CollegeStudent> getGradebook() {
    return studentDao.findAll();
  }
}
