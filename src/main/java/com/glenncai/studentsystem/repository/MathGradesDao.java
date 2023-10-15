package com.glenncai.studentsystem.repository;

import com.glenncai.studentsystem.model.entity.MathGrade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Math grades dao interface
 *
 * @author Glenn Cai
 * @version 1.0 15/10/2023
 */
@Repository
public interface MathGradesDao extends CrudRepository<MathGrade, Integer> {

  Iterable<MathGrade> findGradeByStudentId(int studentId);
}
