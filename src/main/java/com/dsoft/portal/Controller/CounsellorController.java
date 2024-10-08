package com.dsoft.portal.Controller;

import com.dsoft.portal.service.CounsellorsService;
import com.dsoft.portal.service.EnquiriesService;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CounsellorController {

    private final CounsellorsService counsellorsService;

    private final EnquiriesService enquiriesService;

    @GetMapping(value = {"", "/"})
    public String renderLoginPage() {
        log.info( "Rendering Login Page" );
        return "login";
    }


}
