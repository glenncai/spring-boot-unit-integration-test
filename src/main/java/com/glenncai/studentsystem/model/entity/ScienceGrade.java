package com.glenncai.studentsystem.model.entity;

import javax.persistence.*;

/**
 * This class is for xxx.
 *
 * @author Glenn Cai
 * @version 1.0 28/9/2023
 */
@Entity
@Table(name = "science_grade")
public class ScienceGrade {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "student_id")
  private int studentId;

  @Column(name = "grade")
  private double grade;

  public ScienceGrade() {

  }

  public ScienceGrade(double grade) {
    this.grade = grade;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public int getStudentId() {
    return studentId;
  }

  public void setStudentId(int studentId) {
    this.studentId = studentId;
  }

  public double getGrade() {
    return grade;
  }

  public void setGrade(double grade) {
    this.grade = grade;
  }

  @Override
  public String toString() {
    return "ScienceGrade{" +
        "id=" + id +
        ", studentId=" + studentId +
        ", grade=" + grade +
        '}';
  }
}
