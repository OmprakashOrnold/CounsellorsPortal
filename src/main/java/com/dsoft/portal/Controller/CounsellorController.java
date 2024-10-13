package com.dsoft.portal.Controller;

import com.dsoft.portal.dtos.CounsellorDTO;
import com.dsoft.portal.entities.Counsellor;
import com.dsoft.portal.service.CounsellorsService;
import com.dsoft.portal.service.EnquiriesService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Generated;
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

    private final CounsellorsService counsellorsService;

    private final EnquiriesService enquiriesService;

    @GetMapping(value = {"", "/"})
    public String renderLoginPage(Model model) {
        log.info( "::: Loading Login Page :::" );
        CounsellorDTO counsellorDTO =new CounsellorDTO();
        model.addAttribute("loginForm",counsellorDTO);
        return "login";
    }

    @PostMapping("/login")
    public String login(CounsellorDTO counsellorDTO , HttpServletRequest request, Model model) throws Exception {
        log.info( "::: Entered Login Page ::: ,{} ",counsellorDTO );
        Counsellor counsellor = counsellorsService.loginCounsellor(counsellorDTO);
        if(!ObjectUtils.isEmpty(counsellor)){
            // store counsellor id in session
            HttpSession session = request.getSession(true);
            session.setAttribute("counsellorId",counsellor.getCounsellorId());
            return "dashboard";
        }else{
            String errorMsg = "Invalid Credentials Please Enter Valid Credentials";
            model.addAttribute("errorMsg",errorMsg);
            model.addAttribute("loginForm",counsellorDTO);
            log.info( "::: Entered Invalid Credentials ::: ,{} ",counsellorDTO );
            return "login";
        }
    }
}
