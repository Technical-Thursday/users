package com.pracore.user.controllers;

import com.pracore.user.utils.CustomException;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    ArrayList<String> testSequence = new ArrayList<>();

    @GetMapping("/exception")
    public ArrayList<String> testingException() {
       this.test1();
       this.test2();
       return testSequence;
    }

    @GetMapping("/exception/{experience}")
    public Boolean testingException2(@PathVariable Integer experience) {
        return this.isEligible(experience);
    }

    public void test1() {
        try {
            testSequence.add("test-1 try");
            throw new RuntimeException();
        }catch(Exception e) {
            testSequence.add("test-1 catch");
        } finally {
            testSequence.add("test-1 finally");
        }
    }

    public void test2() {
        try {
            testSequence.add("test-2 try");
            throw new CustomException();
        }catch(CustomException e) {
            testSequence.add("test-2 caught custom exception");
        } finally {
            testSequence.add("test-2 finally");
        }
    }

    public Boolean isEligible(Integer experience) {
        if(experience > 5 ) {
            return true;
        }
        throw new CustomException("Not Eligible");
    }
}
