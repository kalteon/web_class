package com.example.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/viewer")
    public String viewer() {
        return "viewer";
    }

    @GetMapping("/creator")
    public String creator() {
        return "creator";
    }

    @GetMapping("/editor")
    public String editor() {
        return "editor";
    }

    @GetMapping("/image")
    public String image() {
        return "image";
    }
}
