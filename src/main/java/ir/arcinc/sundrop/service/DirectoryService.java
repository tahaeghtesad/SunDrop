package ir.arcinc.sundrop.service;

import ir.arcinc.sundrop.model.Directory;
import ir.arcinc.sundrop.model.File;
import ir.arcinc.sundrop.model.User;
import ir.arcinc.sundrop.repository.DirectoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public void deleteDirectory(String username, Long id) throws Exception {
       Directory directory = directoryRepository.findById(id);
       if (directory == null)
           throw new Exception("directory not found");
       if (!Objects.equals(directory.getOwner().getUsername(), username))
           throw new Exception("not owned by you");
       directoryRepository.deleteById(id);
    }

    public List<Long> directoryList(String username, Long id) throws Exception {
        Directory directory = directoryRepository.findById(id);
        if (directory == null)
            throw new Exception("directory not found");
        if (!Objects.equals(directory.getOwner().getUsername(), username))
            throw new Exception("not owned by you");
        List<Long> ret = new ArrayList<>();
        for (Directory d : directory.getSubdirs())
            ret.add(d.getId());
        return ret;
    }

    public List<Long> fileList(String username, Long id) throws Exception {
        Directory directory = directoryRepository.findById(id);
        if (directory == null)
            throw new Exception("directory not found");
        if (!Objects.equals(directory.getOwner().getUsername(), username))
            throw new Exception("not owned by you");
        List<Long> ret = new ArrayList<>();
        for (File d : directory.getFiles())
            ret.add(d.getId());
        return ret;
    }

    public Long getParent(String username, Long id) throws Exception {
        Directory directory = directoryRepository.findById(id);
        if (directory == null)
            throw new Exception("directory not found");
        if (!Objects.equals(directory.getOwner().getUsername(), username))
            throw new Exception("not owned by you");
        return directory.getParent().getId();
    }
}
