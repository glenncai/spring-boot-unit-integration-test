package com.glenncai.studentsystem.service.impl;

import com.glenncai.studentsystem.model.entity.CollegeStudent;
import com.glenncai.studentsystem.model.entity.HistoryGrade;
import com.glenncai.studentsystem.model.entity.MathGrade;
import com.glenncai.studentsystem.model.entity.ScienceGrade;
import com.glenncai.studentsystem.repository.HistoryGradesDao;
import com.glenncai.studentsystem.repository.MathGradesDao;
import com.glenncai.studentsystem.repository.ScienceGradesDao;
import com.glenncai.studentsystem.repository.StudentDao;
import com.glenncai.studentsystem.service.StudentAndGradeService;
import org.springframework.beans.factory.annotation.Qualifier;
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

  @Resource
  @Qualifier("mathGrades")
  private MathGrade mathGrade;

  @Resource
  private MathGradesDao mathGradesDao;

  @Resource
  @Qualifier("scienceGrades")
  private ScienceGrade scienceGrade;

  @Resource
  private ScienceGradesDao scienceGradesDao;

  @Resource
  @Qualifier("historyGrades")
  private HistoryGrade historyGrade;

  @Resource
  private HistoryGradesDao historyGradesDao;

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

  /**
   * Create grade
   *
   * @param grade     grade
   * @param studentId student id
   * @param gradeType grade type
   * @return true if grade is created
   */
  @Override
  public boolean createGrade(double grade, int studentId, String gradeType) {
    if (!checkIfStudentExists(studentId)) {
      return false;
    }

    if (grade >= 0 && grade <= 100) {
      if (gradeType.equals("math")) {
        mathGrade.setStudentId(studentId);
        mathGrade.setGrade(grade);
        mathGradesDao.save(mathGrade);
        return true;
      }
      if (gradeType.equals("science")) {
        scienceGrade.setStudentId(studentId);
        scienceGrade.setGrade(grade);
        scienceGradesDao.save(scienceGrade);
        return true;
      }
      if (gradeType.equals("history")) {
        historyGrade.setStudentId(studentId);
        historyGrade.setGrade(grade);
        historyGradesDao.save(historyGrade);
        return true;
      }
    }

    return false;
  }
}
