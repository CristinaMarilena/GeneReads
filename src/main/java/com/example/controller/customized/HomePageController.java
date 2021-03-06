package com.example.controller.customized;

import com.example.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomePageController {

    @Autowired
    private SecurityService securityService;

    @RequestMapping("/")
    public String home() {
        System.out.println("LOGGED IN : " + securityService.findLoggedInUsername());
        return "/index.htm";
    }

    @RequestMapping("/partials")
    public String getPartials(@RequestParam(value = "name", defaultValue = "something") String name){
        return "partials/"+name+".html";
    }
}
