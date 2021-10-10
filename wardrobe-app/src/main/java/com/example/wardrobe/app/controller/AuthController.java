package com.example.wardrobe.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class AuthController {

    @GetMapping(value = "/login-success")
    public String getLoginPage(HttpServletRequest request) {
        request.getSession().setAttribute("auth", true);
        request.getSession().setAttribute("isAdmin", request.isUserInRole("ADMIN"));
        return "redirect:/clothing";
    }

    @GetMapping(value = "/logout-success")
    public String getLogoutPage(Model model) {
        return "/index";
    }
}
