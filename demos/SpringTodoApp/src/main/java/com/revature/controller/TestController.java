package com.revature.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody String testRequest(){
        return "Hello from the TestController";
    }
}