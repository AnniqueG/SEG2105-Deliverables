package com.example.courseselectionapp;

import org.junit.Test;
import static org.junit.Assert.*;

import com.example.courseselectionapp.InstructorSuccessActivity;
import com.example.courseselectionapp.SignInAsActivity;

public class JUnitTests{

    //deliverable 2
    @Test
    public void testIsAlphaNumericReturnFalse(){
        assertFalse(InstructorSuccessActivity.isAlphaNumeric("6e_wpI!!"));

    }

    //deliverable 2
    @Test
    public void testIsAlphaNumericReturnTrue(){
        assertTrue(InstructorSuccessActivity.isAlphaNumeric("Annique55"));
    }

//    @Test
//    public void testLookupCourse(){
//        assertNull(InstructorSuccessActivity.lookupCourse("BlaBla"));
//
//    }

    //deliverable 2
    @Test
    public void testIsNumberFalse(){
        assertFalse(InstructorSuccessActivity.isNumber("-33"));
    }

    //deliverable 2
    @Test
    public void testIsNumberTrue(){
        assertTrue(InstructorSuccessActivity.isNumber("54"));
    }

    //deliverable 2
    @Test
    public void testGetRole(){
        assertNull(SignInAsActivity.getRole());
    }

    //deliverable 3

    @Test
    public void testLookupCourseCourseDB(){
        Course course = new Course("ENG",1100, 25,"2:00","wednesday","engineering","annique");
        StudentSuccessActivity success = new StudentSuccessActivity();
        assertSame(course, success.lookupCourseCourseDB(course.getCourseName()));
    }

    //deliverable 3
    @Test
    public void testLookupCourseCodeCourseDB(){
        Course course = new Course("FRA",2500, 25,"2:00","wednesday","french","annique");
        StudentSuccessActivity success = new StudentSuccessActivity();
        assertSame(course, success.lookupCourseCodeCourseDB(course.getCourseCode()));
    }

    //deliverable 3
    @Test
    public void testLookupCourseDayCourseDB(){
        Course course = new Course("SEG",2105,45,"2:00","thursday","software","annique");
        StudentSuccessActivity success = new StudentSuccessActivity();
        assertSame(course,success.lookupCourseDayCourseDB(course.getDays()));
    }

    //deliverable 3
    @Test
    public void testLookupCourseInstructor(){
        Course course = new Course("MAT",2748, 25,"2:00","wednesday","math","annique");
        StudentSuccessActivity success = new StudentSuccessActivity();
        assertSame(course,success.lookupCourseIntructorDB(course.getCourseName()));
    }

    //deliverable 3
    @Test
    public void testDeleteCourse() {
        Course course = new Course("CSI", 2110, 25, "2:00", "wednesday", "computer", "annique");
        MyDBHandlerStudent dbStudent = new MyDBHandlerStudent();
        assertTrue(dbStudent.deleteCourse(course.getCourseName()));


    }


}
