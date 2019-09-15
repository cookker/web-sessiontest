package ms.me.demothymesession.file;

import com.sun.net.httpserver.Headers;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final ResourceLoader resourceLoader;



    @GetMapping("")
    public String fileHome(Model model){
        return "/files/file";
    }

    @PostMapping("")
    public String uploadFile(@RequestParam MultipartFile file,
                             RedirectAttributes attributes){
        final String filename = file.getOriginalFilename();
        attributes.addFlashAttribute("fileName", filename);

        return "redirect:/files";
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) throws IOException {
        final Resource resource = resourceLoader.getResource("classpath:" + fileName);
        File file = resource.getFile();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .header(HttpHeaders.CONTENT_TYPE, "image/png")
                .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()))
                .body(resource);
    }
}
