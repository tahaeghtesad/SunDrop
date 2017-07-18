package ir.arcinc.sundrop.service;

import ir.arcinc.sundrop.model.Directory;
import ir.arcinc.sundrop.model.User;
import ir.arcinc.sundrop.repository.DirectoryRepository;
import ir.arcinc.sundrop.repository.FileRepository;
import ir.arcinc.sundrop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.SecondaryTable;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by tahae on 7/18/2017.
 */
@Service
public class FileService {
    @Value("${storage.root.path}")
    private static String storageRoot;

    @Autowired
    private DirectoryRepository directoryRepository;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private UserRepository userRepository;

    public List<ir.arcinc.sundrop.model.File> saveFile(String username, Long directory, String[] names, MultipartFile[] files) throws Exception {

        List<ir.arcinc.sundrop.model.File> uploadedFiles = new ArrayList<>();

        if (files.length != names.length)
            throw new Exception("names do not match files");

        User user = userRepository.findUserByUsername(username);
        if(user == null)
            throw new Exception("user not found");

        Directory parent = directoryRepository.findById(directory);
        if (parent == null)
            throw new Exception("parent directory not found");

        String message = "";
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            String name = names[i];
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                File dir = new File(storageRoot + File.separator + directory);
                if (!dir.exists())
                    dir.mkdirs();

                // Create the file on server
                String path = dir.getAbsolutePath()
                        + File.separator + name;
                File serverFile = new File(path);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                message = message + "You successfully uploaded file=" + name
                        + "\n";

                ir.arcinc.sundrop.model.File dbFile = new ir.arcinc.sundrop.model.File();
                dbFile.setName(name);
                dbFile.setPath(path);
                dbFile.setOwner(user);
                dbFile.setParent(parent);
                fileRepository.save(dbFile);
                uploadedFiles.add(dbFile);

            } catch (Exception e) {
                throw new Exception("You failed to upload " + name + " => " + e.getMessage());
            }
        }
        return uploadedFiles;
    }

    public String getPath(Long id) throws Exception {
        ir.arcinc.sundrop.model.File file = fileRepository.findById(id);
        if (file == null)
            throw new Exception("file not found");
        return file.getPath();
    }

    public void deleteFile(String username, Long id) throws Exception {
        ir.arcinc.sundrop.model.File file = fileRepository.findById(id);
        if (file == null)
            throw new Exception("file not found");
        if (!Objects.equals(file.getOwner().getUsername(), username))
            throw new Exception("not owned by you");
        fileRepository.deleteById(id);
    }

    public Long getParent(String username, Long id) throws Exception {
        ir.arcinc.sundrop.model.File file = fileRepository.findById(id);
        if (file == null)
            throw new Exception("directory not found");
        if (!Objects.equals(file.getOwner().getUsername(), username))
            throw new Exception("not owned by you");
        return file.getParent().getId();
    }
}
