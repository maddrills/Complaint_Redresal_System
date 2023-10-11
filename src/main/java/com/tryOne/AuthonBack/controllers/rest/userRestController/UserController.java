package com.tryOne.AuthonBack.controllers.rest.userRestController;

import com.sun.tools.jconsole.JConsoleContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//auth 1
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/")
    public String HelloUserController(){

        System.out.println(SecurityContextHolder.getContext().getAuthentication());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("UserName: " + auth.getName());
        System.out.println("UserCredentials: " + auth.getCredentials().toString());
        System.out.println("UserAuthorities: " + auth.getAuthorities());
        System.out.println("UserDetails: " + auth.getDetails().toString());

        return "Hello user :)";
    }
}
