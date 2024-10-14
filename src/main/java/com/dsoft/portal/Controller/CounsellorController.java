package com.dsoft.portal.controller;

import com.dsoft.portal.dtos.CounsellorRequest;
import com.dsoft.portal.entities.Counsellor;
import com.dsoft.portal.service.CounsellorsService;
import com.dsoft.portal.service.EnquiriesService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CounsellorController {

    private static final String LOGIN_FORM = "loginForm";
    private static final String SIGN_UP_FORM = "signUpForm";

    private final CounsellorsService counsellorsService;
    private final EnquiriesService enquiriesService;

    @GetMapping(value = {"", "/", "/login"})
    public String loginForm(Model model) {
        log.info("Loading Login Form");
        CounsellorRequest request = new CounsellorRequest();
        model.addAttribute(LOGIN_FORM, request);
        return "login";
    }

    @PostMapping("/login")
    public String login(CounsellorRequest request, HttpServletRequest httpServletRequest, Model model) {
        try {
            log.debug("Attempting to login with credentials: {}", request);
            Counsellor counsellor = counsellorsService.loginCounsellor(request);

            if (!ObjectUtils.isEmpty(counsellor)) {
                HttpSession session = httpServletRequest.getSession(true);
                session.setAttribute("counsellorId", counsellor.getCounsellorId());
                log.info("Login successful for counsellor ID: {}", counsellor.getCounsellorId());
                return "dashboard";
            } else {
                String errorMessage = "Invalid Credentials Please Enter Valid Credentials";
                model.addAttribute("errorMessage", errorMessage);
                model.addAttribute(LOGIN_FORM, request);
                log.warn("Invalid login attempt: {}", request);
                return "login";
            }
        } catch (Exception e) {
            log.error("Error during login process", e);
            model.addAttribute("errorMessage", "An error occurred during login. Please try again.");
            return "error";
        }
    }

    @GetMapping(value = {"/sign-up"})
    public String registerForm(Model model) {
        log.info("Loading Sign Up Form");
        CounsellorRequest request = new CounsellorRequest();
        model.addAttribute(SIGN_UP_FORM, request);
        return "sign-up";
    }

    @PostMapping(value = {"/sign-up"})
    public String register(CounsellorRequest request, Model model) {
        try {
            log.debug("Processing sign up request: {}", request);
            Boolean isRegistered = counsellorsService.registerCounsellor(request);

            if (isRegistered) {
                String registrationSuccessMessage = "Registration Successful Please Login";
                model.addAttribute("registrationSuccessMessage", registrationSuccessMessage);
                model.addAttribute(LOGIN_FORM, request);
                log.info("Successful registration for: {}", request);
                return "login";
            } else {
                String errorMessage = "User Already Exists";
                model.addAttribute("errorMessage", errorMessage);
                model.addAttribute(SIGN_UP_FORM, request);
                log.warn("Duplicate user registration attempt: {}", request);
                return "sign-up";
            }
        } catch (Exception e) {
            log.error("Error during registration process", e);
            model.addAttribute("errorMessage", "An error occurred during registration. Please try again.");
            return "error";
        }
    }

}
