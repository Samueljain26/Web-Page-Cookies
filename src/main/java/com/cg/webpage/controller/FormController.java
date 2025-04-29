package com.cg.webpage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
public class FormController {

    @GetMapping("/")
    public String showPage1() {
        return "page1";
    }

    @PostMapping("/page2")
    public String showPage2(@RequestParam String firstName,
                            @RequestParam String lastName,
                            HttpServletResponse response,
                            Model model) {

        response.addCookie(new Cookie("firstName", firstName));
        response.addCookie(new Cookie("lastName", lastName));

        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        return "page2";
    }

    @PostMapping("/page3")
    public String showPage3(@RequestParam String email,
                            @RequestParam String phoneNumber,
                            HttpServletRequest request,
                            HttpServletResponse response,
                            Model model) {

        response.addHeader("email", email);

        // Save to cookies
        response.addCookie(new Cookie("email", email));
        response.addCookie(new Cookie("phoneNumber", phoneNumber));

        // Read previous data from cookies
        String firstName = getCookieValue(request, "firstName");
        String lastName = getCookieValue(request, "lastName");

        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("email", email);
        model.addAttribute("phoneNumber", phoneNumber);

        return "page3";
    }

    @PostMapping("/page4")
    public String showFinalPage(@RequestParam String city,
                                @RequestParam String country,
                                HttpServletRequest request,
                                Model model) {

        // Read all data from cookies
        String firstName = getCookieValue(request, "firstName");
        String lastName = getCookieValue(request, "lastName");
        String email = getCookieValue(request, "email");
        String phoneNumber = getCookieValue(request, "phoneNumber");

        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("email", email);
        model.addAttribute("phoneNumber", phoneNumber);
        model.addAttribute("city", city);
        model.addAttribute("country", country);

        return "page4";
    }

    // Helper method to fetch cookie value
    private String getCookieValue(HttpServletRequest request, String name) {
        if (request.getCookies() != null) {
            for (Cookie c : request.getCookies()) {
                if (c.getName().equals(name)) {
                    return c.getValue();
                }
            }
        }
        return "";
    }
}
