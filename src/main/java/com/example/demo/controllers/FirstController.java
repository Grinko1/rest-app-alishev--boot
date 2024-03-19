package com.example.demo.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
    @GetMapping
    public String first() {
        return "hello from controller";
    }

    @GetMapping("/model")
    public String getModel(Model model) {
        model.addAttribute("name", "My name");
        return "some text";
    }

    @GetMapping("/getname")
    public String getName(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "id", required = false) String id) {
        return "Hello " + name + " , your id is " + id;
    }

    @GetMapping("/get")
    public String get(HttpServletRequest request) {
        String name = request.getParameter("name");
        String id = request.getParameter("id");
        System.out.println(request.getUserPrincipal());
        System.out.println(request.getServletPath());
        System.out.println(request.getSession());
        return "Hello " + name + " , your id is " + id;
    }

    @GetMapping("/calculate")
    public String calculate(HttpServletRequest request) {
        int a = Integer.parseInt(request.getParameter("a"));
        int b = Integer.parseInt(request.getParameter("b"));
        String action = request.getParameter("action");
        double answer = 0;

        switch (action) {
            case "multi":
                answer = a * b;
                break;
            case "divide":
                if (a == 0) {
                    return "Divide by zero is prohibition";
                } else {
                    answer = (double) a / b;
                    break;
                }
            case "add":
                answer = a + b;
                break;
            case "sub":
                answer = a - b;
                break;
            case "":
                return "No action";

        }
        return "Answer is " + answer;

    }
}
