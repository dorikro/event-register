package com.example.eventregistration.controller;

import com.example.eventregistration.model.Registration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/registration")
public class RegistrationController {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    private final RedisTemplate<String, Registration> redisTemplate;

    @Autowired
    public RegistrationController(RedisTemplate<String, Registration> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping
    public String showForm() {
        logger.info("Showing registration form");
        return "registration";
    }

    @PostMapping
    public String register(@ModelAttribute Registration registration) {
        try {
            logger.info("Registering user: {}", registration.getWorkEmail());
            logger.debug("Registration details: Full Name: {}, Work Email: {}, Phone Number: {}",
                    registration.getFullName(), registration.getWorkEmail(), registration.getPhoneNumber());
            redisTemplate.opsForValue().set(registration.getWorkEmail(), registration);
            return "redirect:/api/registration";
        } catch (Exception e) {
            logger.error("Error registering user: {}", e.getMessage(), e);
            return "error";
        }
    }

    @GetMapping("/{email}")
    @ResponseBody
    public Registration getRegistration(@PathVariable String email) {
        logger.info("Fetching registration for email: {}", email);
        return redisTemplate.opsForValue().get(email);
    }

    @GetMapping("/view")
    public String viewRegistrations(Model model) {
        logger.info("Viewing all registrations");
        List<Registration> registrations = redisTemplate.keys("*").stream()
                .map(key -> redisTemplate.opsForValue().get(key))
                .collect(Collectors.toList());
        model.addAttribute("registrations", registrations);
        return "view-registrations";
    }
}