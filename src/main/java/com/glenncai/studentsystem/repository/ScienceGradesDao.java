package com.glenncai.studentsystem.repository;

import com.glenncai.studentsystem.model.entity.ScienceGrade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Science grades dao interface
 *
 * @author Glenn Cai
 * @version 1.0 15/10/2023
 */
@Repository
public interface ScienceGradesDao extends CrudRepository<ScienceGrade, Integer> {

  Iterable<ScienceGrade> findGradeByStudentId(int studentId);
}
