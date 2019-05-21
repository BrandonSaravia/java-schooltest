package com.lambdaschool.school.service;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.lambdaschool.school.SchoolApplication;
import com.lambdaschool.school.model.Course;
import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import java.awt.*;
import java.util.ArrayList;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolApplication.class)
class CourseServiceImplTest {

    @Autowired
    private CourseService courseService;


    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void deleteFound() {

        courseService.delete(1);
        assertEquals(5, courseService.findAll().size());

    }

    @Test
    void deleteNotFound() {

        courseService.delete(100);
        assertEquals(6, courseService.findAll().size());

    }

    @Test
    void findCourseById() {

        assertEquals("Data Science", courseService.findCourseById(1).getCoursename());

    }

    @Test
    void save() {

//        ArrayList<Students> students = new ArrayList<>();

        Course course = new Course("Java2");

        Course savedCourse = courseService.save(course);

        assertNotNull(savedCourse);

        Course foundCourse = courseService.findCourseById(savedCourse.getCourseid());

        TestCase.assertEquals(course.getCoursename(), foundCourse.getCoursename());

    }
}