package com.glenncai.studentsystem;

import com.glenncai.studentsystem.model.entity.CollegeStudent;
import com.glenncai.studentsystem.model.entity.HistoryGrade;
import com.glenncai.studentsystem.model.entity.MathGrade;
import com.glenncai.studentsystem.model.entity.ScienceGrade;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class StudentSystemApplication {

  public static void main(String[] args) {
    SpringApplication.run(StudentSystemApplication.class, args);
  }

  @Bean
  @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
  CollegeStudent getCollegeStudent() {
    return new CollegeStudent();
  }

  @Bean
  @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
  @Qualifier("mathGrades")
  MathGrade getMathGrade() {
    return new MathGrade();
  }

  @Bean
  @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
  @Qualifier("scienceGrades")
  ScienceGrade getScienceGrade() {
    return new ScienceGrade();
  }

  @Bean
  @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
  @Qualifier("historyGrades")
  HistoryGrade getHistoryGrade() {
    return new HistoryGrade();
  }
}
