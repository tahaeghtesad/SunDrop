package ir.arcinc.sundrop.controller;

import ir.arcinc.sundrop.model.Directory;
import ir.arcinc.sundrop.model.User;
import ir.arcinc.sundrop.repository.UserRepository;
import ir.arcinc.sundrop.service.UserService;
import ir.arcinc.sundrop.viewmodel.ChangePasswordViewModel;
import ir.arcinc.sundrop.viewmodel.RegisterViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by tahae on 7/17/2017.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public User changePassword(Principal user, @RequestBody ChangePasswordViewModel vm) throws Exception {
        return userService.changePassword(user.getName(), vm.getPassword());
    }

    @RequestMapping(value = "/",method = RequestMethod.PUT)
    public User createUser(@RequestBody RegisterViewModel vm) throws Exception {
        return userService.createUser(vm.getUsername(), vm.getPassword());
    }

}
