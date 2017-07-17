package ir.arcinc.sundrop.controller;

import ir.arcinc.sundrop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by tahae on 7/16/2017.
 */
@RestController
@RequestMapping(name = "/api")
public class IndexController {

    @Autowired
    private UserDetailsService userDetailsService;

    @RequestMapping("/api")
    public Principal index(Principal user){
        System.out.println(user);
        return user;
    }

    @RequestMapping("/api/user")
    public User user(Principal user){
        return (User) userDetailsService.loadUserByUsername(user.getName());
    }
}
