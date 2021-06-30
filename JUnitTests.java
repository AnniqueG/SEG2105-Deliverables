package com.example.courseselectionapp;

import org.junit.Test;
import static org.junit.Assert.*;

import com.example.courseselectionapp.InstructorSuccessActivity;
import com.example.courseselectionapp.SignInAsActivity;

public class JUnitTests {

    @Test
    public void testIsAlphaNumericReturnFalse(){
        assertFalse(InstructorSuccessActivity.isAlphaNumeric("6e_wpI!!"));

    }

    @Test
    public void testIsAlphaNumericReturnTrue(){
        assertTrue(InstructorSuccessActivity.isAlphaNumeric("Annique55"));
    }

//    @Test
//    public void testLookupCourse(){
//        assertNull(InstructorSuccessActivity.lookupCourse("BlaBla"));
//
//    }

    @Test
    public void testIsNumberFalse(){
        assertFalse(InstructorSuccessActivity.isNumber("-33"));
    }

    @Test
    public void testIsNumberTrue(){
        assertTrue(InstructorSuccessActivity.isNumber("54"));
    }

    @Test
    public void testGetRole(){
        assertNull(SignInAsActivity.getRole());
    }
}
