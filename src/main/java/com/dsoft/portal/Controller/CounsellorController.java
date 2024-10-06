package com.dsoft.portal.Controller;

import com.dsoft.portal.service.CounsellorsService;
import com.dsoft.portal.service.EnquiriesService;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class CounsellorController {

    private final CounsellorsService counsellorsService;

    private final EnquiriesService enquiriesService;

    @GetMapping("/")
    public String login(Model model){
        return "login";
    }


}
