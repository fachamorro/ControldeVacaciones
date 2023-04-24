package gob.distrito05d03salud.controlvacaciones.controlador;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/fileupload")

public class FileUploadController {
 
    /**
     * Location to save uploaded files on server
     */
    private static String UPLOAD_PATH = "C://uploads//";
 
    /*
     * single file upload in a request
     */
    @PostMapping("/api/fileupload")
    public void uploadFile(@RequestParam("multipartFile") MultipartFile uploadfile) {
 
        if (uploadfile.isEmpty()) {
            System.out.println("please select a file!");
        }
 
        try {
            byte[] bytes = uploadfile.getBytes();
            Path path = Paths.get(UPLOAD_PATH + uploadfile.getOriginalFilename());
            Files.write(path, bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}