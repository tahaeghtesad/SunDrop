package ir.arcinc.sundrop.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by tahae on 7/16/2017.
 */
@RestController
@RequestMapping(name = "/api")
public class IndexController {

    @RequestMapping("/api")
    public Principal index(Principal user){
        System.out.println(user);
        return user;
    }
}
