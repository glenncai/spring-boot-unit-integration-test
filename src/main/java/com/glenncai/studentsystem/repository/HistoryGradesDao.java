package com.glenncai.studentsystem.repository;

import com.glenncai.studentsystem.model.entity.HistoryGrade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * History grades dao interface
 *
 * @author Glenn Cai
 * @version 1.0 15/10/2023
 */
@Repository
public interface HistoryGradesDao extends CrudRepository<HistoryGrade, Integer> {

  Iterable<HistoryGrade> findGradeByStudentId(int studentId);
}
