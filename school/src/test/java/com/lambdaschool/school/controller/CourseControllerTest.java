package com.lambdaschool.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Student;
import com.lambdaschool.school.service.CourseService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(value = CourseController.class, secure = false)
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    private ArrayList<Course> courseList = new ArrayList<>();


    @BeforeEach
    void setUp() {



    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void listAllCourses() throws Exception {

        String url = "/courses/courses";

        Mockito.when(courseService.findAll()).thenReturn(courseList);

        RequestBuilder rb = MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON);

        MvcResult r = mockMvc.perform(rb).andReturn();

        String stringResult = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String expectedResult = mapper.writeValueAsString(courseList);

        assertEquals(expectedResult, stringResult);
    }

    @Test
    void addNewRestaurant() throws Exception {

        String url = "/courses/courses/course/add";

//      ArrayList<Student> students = new ArrayList<>();

        Course course = new Course("Java2");

//        course.getStudents().add(new Student("John"));

        Mockito.when(courseService.save(any(Course.class))).thenReturn(course);

        course.setCourseid(236);

        ObjectMapper mapper = new ObjectMapper();
        String restaurantStr = mapper.writeValueAsString(course);

        RequestBuilder rb = MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON).content(restaurantStr).accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(rb).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());

    }
}