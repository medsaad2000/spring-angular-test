package lu.atozdigital.api.util;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Component
public class ArticleUtil {
    public static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/uploads/";

    //Method to Upload One File
    public String uploadFile( @RequestParam("image") MultipartFile multipartFiles) throws IOException {

            String filename = StringUtils.cleanPath(multipartFiles.getOriginalFilename());
            Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
            copy(multipartFiles.getInputStream(), fileStorage, REPLACE_EXISTING);
            return String.valueOf(fileStorage);
        }

    // Method to Upload Multiple Files
    public List<String> uploadFiles( @RequestParam("image") List<MultipartFile>  multipartFiles) throws IOException {
        List<String> filenames = new ArrayList<>();
       for(MultipartFile file : multipartFiles) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
        copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
            filenames.add(filename);
         }
        return filenames;
    }
    }


