package com.lambdaschool.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Student;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.number.OrderingComparison.lessThan;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CourseConrtollerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void init() {

        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);

    }


    @Test
    public void testServerResponseTime() {

        given().when().get("/restaurants/restaurants").then().time(lessThan(5000L));

    }

    @Test
    public void testPostCourse() throws Exception {

//        ArrayList<Student> students = new ArrayList<>();

        Course course = new Course("Java2");

        course.getStudents().add(new Student("John"));

        ObjectMapper mapper = new ObjectMapper();

        String restStr = mapper.writeValueAsString(course);

        System.out.println(restStr);

        given().contentType("application/json").body(restStr).when().post("/courses/courses/course/add").then().statusCode(201);

    }
}
