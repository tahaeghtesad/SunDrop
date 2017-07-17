package ir.arcinc.sundrop.controller;

import ir.arcinc.sundrop.model.Directory;
import ir.arcinc.sundrop.service.DirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by tahae on 7/17/2017.
 */
@RestController
@RequestMapping("/api/directory")
public class DirectoryController {

    @Autowired
    private DirectoryService directoryService;

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public Directory createDirectory(Principal user, @RequestBody String name, @RequestBody Long parentId){
        return directoryService.createDirectory(user.getName(), name, parentId);
    }
}
