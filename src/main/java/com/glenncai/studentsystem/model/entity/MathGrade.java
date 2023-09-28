package com.glenncai.studentsystem.model.entity;

import javax.persistence.*;

/**
 * This class is for xxx.
 *
 * @author Glenn Cai
 * @version 1.0 28/9/2023
 */
@Entity
@Table(name = "math_grade")
public class MathGrade {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "student_id")
  private int studentId;

  @Column(name = "grade")
  private double grade;

  public MathGrade() {

  }

  public MathGrade(double grade) {
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
    return "MathGrade{" +
        "id=" + id +
        ", studentId=" + studentId +
        ", grade=" + grade +
        '}';
  }
}
