package ir.arcinc.sundrop.service;

import ir.arcinc.sundrop.model.Directory;
import ir.arcinc.sundrop.model.User;
import ir.arcinc.sundrop.repository.DirectoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tahae on 7/17/2017.
 */
@Service
public class DirectoryService {
    @Autowired
    private DirectoryRepository directoryRepository;

    @Autowired
    private UserService userService;

    public Directory createDirectory(String userName, String name, Long parentId) throws Exception {
        User u = (User) userService.loadUserByUsername(userName);
        Directory parent = directoryRepository.findById(parentId);

        if (parent == null)
            throw new Exception("Parent does not exist");

        Directory current = new Directory();
        current.setName(name);
        current.setOwner(u);
        current.setParent(parent);

        directoryRepository.save(current);

        return current;
    }
}
