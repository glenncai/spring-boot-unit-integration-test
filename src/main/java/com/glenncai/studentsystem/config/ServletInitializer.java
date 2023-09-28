package com.glenncai.studentsystem.config;

import com.glenncai.studentsystem.StudentSystemApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * ServletInitializer
 *
 * @author Glenn Cai
 * @version 1.0 28/9/2023
 */
public class ServletInitializer extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(StudentSystemApplication.class);
  }
}
