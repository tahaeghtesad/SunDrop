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
@RequestMapping("/api/")
public class IndexController {

    @Autowired
    private UserDetailsService userDetailsService;

    @RequestMapping("/")
    public Principal index(Principal user){
        return user;
    }

    @RequestMapping("/me")
    public User user(Principal user){
        return (User) userDetailsService.loadUserByUsername(user.getName());
    }
}
