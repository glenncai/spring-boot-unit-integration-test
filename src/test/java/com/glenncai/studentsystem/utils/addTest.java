package com.glenncai.studentsystem.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * This class is for xxx.
 *
 * @author Glenn Cai
 * @version 1.0 6/10/2023
 */
@SpringBootTest
class AddTest {

  @Test
  void addStatic() {
    int result = Add.addStatic(1, 2);
    assertEquals(3, result);
  }

  @Test
  void multiplyPrivate() throws NoSuchMethodException, InvocationTargetException,
      IllegalAccessException {
    Method add = Add.class.getDeclaredMethod("multiplyPrivate", int.class, int.class);
    add.setAccessible(true);
    int result = (int) add.invoke(add.getClass(), 1, 2);
    assertEquals(2, result);
  }
}