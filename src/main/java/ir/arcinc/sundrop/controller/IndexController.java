package ir.arcinc.sundrop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tahae on 7/16/2017.
 */
@RestController
@RequestMapping(name = "/api")
public class IndexController {
    @RequestMapping("/api")
    public String index(){
        return "Hello World";
    }
}
