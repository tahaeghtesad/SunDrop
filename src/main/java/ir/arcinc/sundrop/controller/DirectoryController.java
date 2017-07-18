package ir.arcinc.sundrop.controller;

import ir.arcinc.sundrop.model.Directory;
import ir.arcinc.sundrop.service.DirectoryService;
import ir.arcinc.sundrop.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.security.Principal;
import java.util.List;

/**
 * Created by tahae on 7/17/2017.
 */
@RestController
@RequestMapping("/api/directory")
public class DirectoryController {

    @Autowired
    private DirectoryService directoryService;

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public Directory createDirectory(Principal user, @RequestBody String name, @RequestBody Long parentId) throws Exception {
        return directoryService.createDirectory(user.getName(), name, parentId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteDirectory(Principal user, @PathVariable("id") Long id) {
        try{
            directoryService.deleteDirectory(user.getName(), id);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @RequestMapping(value = "/listdir/{id}", method = RequestMethod.GET)
    public List<Long> listDirectories(Principal user, @PathVariable("id") Long id) throws Exception {
        return directoryService.directoryList(user.getName(), id);
    }

    @RequestMapping(value = "/listfile/{id}", method = RequestMethod.GET)
    public List<Long> listFiles(Principal user, @PathVariable("id") Long id) throws Exception {
        return directoryService.fileList(user.getName(), id);
    }


    @RequestMapping(value = "/parent/{id}", method = RequestMethod.GET)
    public Long getParent(Principal user, @PathVariable("id") Long id) throws Exception {
        return directoryService.getParent(user.getName(), id);
    }

}
