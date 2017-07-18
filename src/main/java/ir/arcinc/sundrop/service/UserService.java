package ir.arcinc.sundrop.service;

import ir.arcinc.sundrop.model.Directory;
import ir.arcinc.sundrop.model.User;
import ir.arcinc.sundrop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by tahae on 7/16/2017.
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(s);
    }

    public User createUser(String username, String password) throws Exception {
        if (userRepository.findUserByUsername(username) != null)
            throw new Exception("Username already taken");
        User u = new User();
        u.setUsername(username);
//        u.setPassword(passwordEncoder.encode(password));
        u.setPassword(password);
        Directory rootDir = new Directory();
        rootDir.setName("root");
        rootDir.setOwner(u);
        u.setRootDir(rootDir);
        userRepository.save(u);
        return u;
    }

    public User changePassword(String username, String password) throws Exception {
        User u = userRepository.findUserByUsername(username);
        if (u == null)
            throw new Exception("User does not exist");
//        u.setPassword(passwordEncoder.encode(password));
        u.setPassword(password);
        userRepository.save(u);
        return u;
    }
}
