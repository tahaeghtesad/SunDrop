package ir.arcinc.sundrop.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadViewModel {
    private Long parentDir;
    private String[] names;
    private MultipartFile[] files;
}
