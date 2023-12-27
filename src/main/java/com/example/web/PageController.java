package com.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PageController {

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/page")
    public String page(@RequestParam String page){
        return switch (page) {
            case "about" -> "about";
            case "services" -> "services";
            case "products" -> "products";
            case "contact" -> "contact";
            case "come" -> "come";
            case "signin" -> "signin";
            case "signup" -> "signup";
            case "chat" -> "chat/chatindex.html";
            default -> "error"; // 잘못된 요청에 대한 기본 페이지




        };
    }

}
