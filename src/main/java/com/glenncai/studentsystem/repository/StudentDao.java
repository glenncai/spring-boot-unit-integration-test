package com.glenncai.studentsystem.repository;

import com.glenncai.studentsystem.model.entity.CollegeStudent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Student dao interface
 *
 * @author Glenn Cai
 * @version 1.0 28/9/2023
 */
@Repository
public interface StudentDao extends CrudRepository<CollegeStudent, Integer> {

  CollegeStudent findByEmailAddress(String emailAddress);
}
